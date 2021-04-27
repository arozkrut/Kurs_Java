package kalendarz;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GłównyPanel extends JPanel implements ActionListener, ChangeListener, AdjustmentListener
{
    private GregorianCalendar kalendarz;
    private JButton poprzedni, następny;
    private JSpinner spinnerM, spinnerR;
    private JScrollBar scrollBarM, scrollBarR;
    private JTabbedPane tabbedPane;
    private static final String[] monthStrings = {"Styczeń", "Luty", "Marzec", "Kwieceń", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
    private PanelRok panelRok;
    private PanelMiesiąca panelMiesiąca;
    private static final Font font = new Font("LobsterTwo", Font.BOLD, 16);

    public GłównyPanel() {
        super(new BorderLayout());

        kalendarz = new GregorianCalendar();

        przygotujGłównyPanel();
        przygotujPasekNarzędzi();
    }

    void zmieńDatę(int rok, int miesiąc, boolean pokażMiesiąc)
    {
        tabbedPane.setTitleAt(0, String.valueOf(rok));
        tabbedPane.setTitleAt(1, nazwaMiesiąca(miesiąc));

        panelMiesiąca.zmiana(rok, miesiąc);
        panelRok.wypełnij(rok);

        spinnerM.setValue(nazwaMiesiąca(miesiąc));
        scrollBarM.setValue(miesiąc * 5);
        spinnerR.setValue(rok);
        scrollBarR.setValue(rok * 5);

        if(pokażMiesiąc)tabbedPane.setSelectedComponent(panelMiesiąca);
        else tabbedPane.setSelectedComponent(panelRok);
        kalendarz.set(Calendar.MONTH, miesiąc);
        kalendarz.set(Calendar.YEAR, rok);
    }

    private void przygotujGłównyPanel()
    {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        panelMiesiąca = new PanelMiesiąca();
        panelRok = new PanelRok(this);
        tabbedPane.addTab(String.valueOf(kalendarz.get(Calendar.YEAR)), null, panelRok, "Rok");
        tabbedPane.addTab(nazwaMiesiąca(kalendarz.get(Calendar.MONTH)), null, panelMiesiąca, "Miesiąc");

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void przygotujPasekNarzędzi()
    {
        JToolBar toolBar = new JToolBar();

        poprzedni = new JButton("Poprzedni");
        następny = new JButton("Następny");
        poprzedni.addActionListener(this);
        następny.addActionListener(this);
        poprzedni.setFont(font);
        następny.setFont(font);

        toolBar.add(poprzedni);
        toolBar.add(następny);

        SpinnerListModel monthModel;
        monthModel = new SpinnerListModel(monthStrings);
        JLabel label1 = new JLabel("Miesiąc");
        label1.setFont(font);
        toolBar.add(label1);
        spinnerM = new JSpinner(monthModel);
        spinnerM.setValue(nazwaMiesiąca(kalendarz.get(Calendar.MONTH)));
        label1.setLabelFor(spinnerM);
        toolBar.add(spinnerM);
        spinnerM.setFont(font);

        scrollBarM = new JScrollBar(JScrollBar.HORIZONTAL, kalendarz.get(Calendar.MONTH) * 5, 5, 0, 60);
        toolBar.add(scrollBarM);

        SpinnerModel yearModel = new SpinnerNumberModel(kalendarz.get(Calendar.YEAR), 0, 2029, 1);
        JLabel label2 = new JLabel("Rok");
        label2.setFont(font);
        toolBar.add(label2);
        spinnerR = new JSpinner(yearModel);
        label2.setLabelFor(spinnerR);
        toolBar.add(spinnerR);
        spinnerR.setFont(font);

        scrollBarR = new JScrollBar(JScrollBar.HORIZONTAL, kalendarz.get(Calendar.YEAR) * 5, 5, 0, 10150);
        toolBar.add(scrollBarR);
        
        spinnerR.addChangeListener(this);
        spinnerM.addChangeListener(this);
        scrollBarM.addAdjustmentListener(this);
        scrollBarR.addAdjustmentListener(this);

        add(toolBar, BorderLayout.PAGE_END);
    }

    private String nazwaMiesiąca(int miesiąc)
    {
        String nazwa = "  ";

        switch (miesiąc)
        {
            case Calendar.JANUARY:
                nazwa = "Styczeń";
                break;
            case Calendar.FEBRUARY:
                nazwa = "Luty";
                break;
            case Calendar.MARCH:
                nazwa = "Marzec";
                break;
            case Calendar.APRIL:
                nazwa = "Kwiecień";
                break;
            case Calendar.MAY:
                nazwa = "Maj";
                break;
            case Calendar.JUNE:
                nazwa = "Czerwiec";
                break;
            case Calendar.JULY:
                nazwa = "Lipiec";
                break;
            case Calendar.AUGUST:
                nazwa = "Sierpień";
                break;
            case Calendar.SEPTEMBER:
                nazwa = "Wrzesień";
                break;
            case Calendar.OCTOBER:
                nazwa = "Październik";
                break;
            case Calendar.NOVEMBER:
                nazwa = "Listopad";
                break;
            case Calendar.DECEMBER:
                nazwa = "Grudzień";
                break;
        }

        return nazwa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        int indeks = tabbedPane.getSelectedIndex();

        GregorianCalendar k = kalendarz;

        if(o.equals(następny))
        {
            if(indeks == 0)
            {
                k.add(Calendar.YEAR, 1);
                zmieńDatę(k.get(Calendar.YEAR), k.get(Calendar.MONTH), false);
            }
            else
            {
                k.add(Calendar.MONTH, 1);
                zmieńDatę(k.get(Calendar.YEAR), k.get(Calendar.MONTH), true);
            }
        }
        else if(o.equals(poprzedni))
        {
            if(indeks == 0)
            {
                k.add(Calendar.YEAR, -1);
                zmieńDatę(k.get(Calendar.YEAR), k.get(Calendar.MONTH), false);
            }
            else
            {
                k.add(Calendar.MONTH, -1);
                zmieńDatę(k.get(Calendar.YEAR), k.get(Calendar.MONTH), true);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();
        
        if(o.equals(spinnerR))
        {
            int rok = (int) spinnerR.getValue();
            zmieńDatę(rok, kalendarz.get(Calendar.MONTH), false);
        }
        else if(o.equals(spinnerM))
        {
            String nazwa = (String) spinnerM.getValue();
            int miesiac = 0;
            switch (nazwa)
            {
                case "Styczeń":
                    miesiac = Calendar.JANUARY;
                    break;
                case "Luty":
                    miesiac = Calendar.FEBRUARY;
                    break;
                case "Marzec":
                    miesiac = Calendar.MARCH;
                    break;
                case "Kwiecień":
                    miesiac = Calendar.APRIL;
                    break;
                case "Maj":
                    miesiac = Calendar.MAY;
                    break;
                case "Czerwiec":
                    miesiac = Calendar.JUNE;
                    break;
                case "Lipiec":
                    miesiac = Calendar.JULY;
                    break;
                case "Sierpień":
                    miesiac = Calendar.AUGUST;
                    break;
                case "Wrzesień":
                    miesiac = Calendar.SEPTEMBER;
                    break;
                case "Październik":
                    miesiac = Calendar.OCTOBER;
                    break;
                case "Listopad":
                    miesiac = Calendar.NOVEMBER;
                    break;
                case "Grudzień":
                    miesiac = Calendar.DECEMBER;
            }

            zmieńDatę(kalendarz.get(Calendar.YEAR), miesiac, true);
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Object o = e.getSource();

        if(o.equals(scrollBarM))
        {
            int miesiac = scrollBarM.getValue() / 5;
            zmieńDatę(kalendarz.get(Calendar.YEAR), miesiac, true);
        }
        else if(o.equals(scrollBarR))
        {
            int rok = scrollBarR.getValue()/5;
            zmieńDatę(rok, kalendarz.get(Calendar.MONTH), false);
        }
    }
}
