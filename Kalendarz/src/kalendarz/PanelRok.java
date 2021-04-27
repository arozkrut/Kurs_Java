package kalendarz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanelRok extends JPanel implements MouseListener
{
    private JPanel[] miesiące;
    private int rok;
    private static final Font font = new Font("LobsterTwo", Font.BOLD, 16);
    private GłównyPanel głównyPanel;
    private JLabel[] nazwyMiesięcy;

    public PanelRok(GłównyPanel głównyPanel)
    {
        super(new GridLayout(2,6));
        miesiące = new JPanel[12];
        nazwyMiesięcy = new JLabel[12];
        this.głównyPanel = głównyPanel;

        ustawNazwy();

        wypełnij(new GregorianCalendar().get(Calendar.YEAR));
        for(int i = 0; i < 12; ++i)add(miesiące[i]);

        setVisible(true);
    }

    void ustawNazwy()
    {
        for(int i = 0; i<12; ++i)
        {
            JLabel label = new JLabel("", SwingConstants.CENTER);

            label.setFont(font);
            switch (i)
            {
                case Calendar.JANUARY:
                    label.setText("Styczeń");
                    break;
                case Calendar.FEBRUARY:
                    label.setText("Luty");
                    break;
                case Calendar.MARCH:
                    label.setText("Marzec");
                    break;
                case Calendar.APRIL:
                    label.setText("Kwiecień");
                    break;
                case Calendar.MAY:
                    label.setText("Maj");
                    break;
                case Calendar.JUNE:
                    label.setText("Czerwiec");
                    break;
                case Calendar.JULY:
                    label.setText("Lipiec");
                    break;
                case Calendar.AUGUST:
                    label.setText("Sierpień");
                    break;
                case Calendar.SEPTEMBER:
                    label.setText("Wrzesień");
                    break;
                case Calendar.OCTOBER:
                    label.setText("Październik");
                    break;
                case Calendar.NOVEMBER:
                    label.setText("Listopad");
                    break;
                case Calendar.DECEMBER:
                    label.setText("Grudzień");
                    break;
            }

            nazwyMiesięcy[i] = label;
            nazwyMiesięcy[i].addMouseListener(this);
        }
    }

    void wypełnij(int rok)
    {
        if(this.rok == rok)return;

        this.rok = rok;

        if(miesiące[0] != null)
        {
            for(int i = 0; i < 12; ++i)remove(miesiące[i]);
        }

        JPanel panel;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(rok, Calendar.JANUARY, 1);
        JList<String> lista;
        int ostatniDzieńMiesiąca;
        int długośćTablicy;
        int dodawaneNaPocząku;

        for(int i = 0; i < 12 ; ++i)
        {
            panel = new JPanel(new BorderLayout());
            ostatniDzieńMiesiąca = gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            if(rok != 1582 || i != Calendar.OCTOBER)długośćTablicy = ostatniDzieńMiesiąca;
            else długośćTablicy = ostatniDzieńMiesiąca - 10;

            dodawaneNaPocząku = gregorianCalendar.get(Calendar.DAY_OF_WEEK) - 2;
            if(dodawaneNaPocząku < 0)dodawaneNaPocząku = 6;

            String[] numery = new String[długośćTablicy + dodawaneNaPocząku];
            for(int d = 0; d < dodawaneNaPocząku; ++d)numery[d] = "  ";

            for(int day = 0; gregorianCalendar.get(Calendar.MONTH) == i; ++day)
            {
                numery[day + dodawaneNaPocząku] = String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
                gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            lista = new JList(numery);

            lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            lista.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            lista.setVisibleRowCount(-1);
            lista.setBounds(0, 0, 175, 100);

            lista.setCellRenderer(new Kreślarz(22));

            panel.add(nazwyMiesięcy[i], BorderLayout.PAGE_START);
            panel.add(lista, BorderLayout.CENTER);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            miesiące[i] = null;
            miesiące[i] = panel;
            add(miesiące[i]);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();

        for(int i = 0; i< 12; ++i)
        {
            if(o.equals(nazwyMiesięcy[i]))
            {
                głównyPanel.zmieńDatę(rok, i, true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
