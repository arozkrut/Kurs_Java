package kalendarz;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanelMiesiąca extends JPanel
{
    private int rok;
    private int miesiąc;

    private JPanel[] miesiące;
    private MiesiącwKalendarzu[] listy;
    private JLabel[] nazwyMiesięcy;

    private static final Font font = new Font("LobsterTwo", Font.BOLD, 16);

    public PanelMiesiąca()
    {
        super(new GridLayout(1,3));
        miesiące = new JPanel[3];
        listy = new MiesiącwKalendarzu[3];
        nazwyMiesięcy = new JLabel[3];

        GregorianCalendar kalendarz = new GregorianCalendar();

        wypełnij(kalendarz.get(Calendar.YEAR), kalendarz.get(Calendar.MONTH));
        for(int i = 0; i < 3; ++i)add(miesiące[i]);

        setVisible(true);
    }

    void wypełnij(int rok, int miesiąc)
    {
        if(this.rok == rok && this.miesiąc == miesiąc)return;

        this.rok = rok;
        this.miesiąc = miesiąc;

        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiąc, 1);
        kalendarz.add(Calendar.MONTH, -1);

        for(int i = 0; i < 3 ; ++i)
        {
            listy[i] = new MiesiącwKalendarzu(kalendarz);
            miesiące[i] = stwórzPanel(i, kalendarz.get(Calendar.MONTH));
            kalendarz.add(Calendar.MONTH, 1);
        }
    }

    JPanel stwórzPanel(int index, int numerMiesiąca)
    {
        JPanel panel = new JPanel(new BorderLayout());

        JList lista = listy[index].zwróćListę();
        nazwyMiesięcy[index] = new JLabel(MiesiącwKalendarzu.nazwaMiesiąca(numerMiesiąca), SwingConstants.CENTER);
        nazwyMiesięcy[index].setFont(font);

        panel.add(nazwyMiesięcy[index], BorderLayout.PAGE_START);
        panel.add(lista, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    void zmiana(int rok, int miesiąc)
    {
        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiąc, 1);
        kalendarz.add(Calendar.MONTH, -1);

        for(int i = 0; i < 3 ; ++i)
        {
            listy[i].zmiana(kalendarz.get(Calendar.YEAR), kalendarz.get(Calendar.MONTH));
            nazwyMiesięcy[i].setText(MiesiącwKalendarzu.nazwaMiesiąca(kalendarz.get(Calendar.MONTH)));
            kalendarz.add(Calendar.MONTH, 1);
        }
    }
}
