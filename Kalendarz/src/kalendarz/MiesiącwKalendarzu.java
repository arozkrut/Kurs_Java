package kalendarz;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MiesiącwKalendarzu extends AbstractListModel
{
    private int rok;
    private int miesiąc;
    private static final Kreślarz kreślarz = new Kreślarz(16);

    MiesiącwKalendarzu(GregorianCalendar kalendarz)
    {
        super();

        rok = kalendarz.get(GregorianCalendar.YEAR);
        miesiąc = kalendarz.get(GregorianCalendar.MONTH);
    }

    @Override
    public int getSize() {
        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiąc, 1);
        if(rok != 1582 || miesiąc != Calendar.OCTOBER)return kalendarz.getActualMaximum(Calendar.DAY_OF_MONTH);
        return kalendarz.getActualMaximum(Calendar.DAY_OF_MONTH) - 10;
    }

    @Override
    public Object getElementAt(int index) {
        String string = "";
        GregorianCalendar kalendarz = new GregorianCalendar(rok, miesiąc, 1);
        for(int i = 0; i < index; ++i)kalendarz.add(Calendar.DAY_OF_MONTH, 1);

        switch (kalendarz.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY:
                string = "poniedziałek ";
                break;
            case Calendar.TUESDAY:
                string = "wtorek ";
                break;
            case Calendar.WEDNESDAY:
                string = "środa ";
                break;
            case Calendar.THURSDAY:
                string = "czwartek ";
                break;
            case Calendar.FRIDAY:
                string = "piątek ";
                break;
            case Calendar.SATURDAY:
                string = "sobota ";
                break;
            case Calendar.SUNDAY:
                string = "niedziela ";
                break;
        }

        string += String.valueOf(kalendarz.get(Calendar.DAY_OF_MONTH));

        string += nazwaMiesiąca(kalendarz.get(Calendar.MONTH));

        return string;
    }

    void zmiana(int rok, int miesiąc)
    {
        this.rok = rok;
        this.miesiąc = miesiąc;

        fireContentsChanged(this , 0, getSize());
    }

    JList zwróćListę()
    {
        JList lista = new JList();

        lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista.setLayoutOrientation(JList.VERTICAL_WRAP);
        lista.setVisibleRowCount(-1);
        lista.setBounds(0, 0, 200, 330);
        lista.setModel(this);
        lista.setCellRenderer(kreślarz);

        return lista;
    }
    
    static String nazwaMiesiąca(int miesiąc)
    {
        String nazwa = "";
        
        switch (miesiąc)
        {
            case Calendar.JANUARY:
                nazwa = " Styczeń";
                break;
            case Calendar.FEBRUARY:
                nazwa = " Luty";
                break;
            case Calendar.MARCH:
                nazwa = " Marzec";
                break;
            case Calendar.APRIL:
                nazwa = " Kwiecień";
                break;
            case Calendar.MAY:
                nazwa = " Maj";
                break;
            case Calendar.JUNE:
                nazwa = " Czerwiec";
                break;
            case Calendar.JULY:
                nazwa = " Lipiec";
                break;
            case Calendar.AUGUST:
                nazwa = " Sierpień";
                break;
            case Calendar.SEPTEMBER:
                nazwa = " Wrzesień";
                break;
            case Calendar.OCTOBER:
                nazwa = " Październik";
                break;
            case Calendar.NOVEMBER:
                nazwa = " Listopad";
                break;
            case Calendar.DECEMBER:
                nazwa = " Grudzień";
                break;
        }
        
        return nazwa;
    }
}
