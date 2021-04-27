package struktury;

import java.util.Objects;

/**
 * Klasa implementuje pary [ klucz, wartosc ].
 * @author Aleksandra Rozkrut
 */

public class Para
{
    public final String klucz;
    private double wartosc;

    /**
     * Konstruktor publiczny przyjmuje jako argumenty klucz i wartosc
     * @param klucz Pierwszy element pary.
     * @param wartosc Drugi element pary.
     * @throws IllegalArgumentException Jesli pierwszy argument jest pustym napisem
     * lub ma wartosc null konstruktor zwraca wyjatek.
     */
    public Para(String klucz, double wartosc)throws IllegalArgumentException
    {
        if(klucz != null && !klucz.isEmpty())
        {
            this.klucz = klucz;
            this.wartosc = wartosc;
        }
        else
        {
            throw new IllegalArgumentException("Zły klucz");
        }
    }

    /**
     * Metoda zwraca drugi element pary.
     * @return Drugi element pary.
     */
    public double getWartosc()
    {
        return wartosc;
    }

    /**
     * Metoda przyjmuje jako argument nowa wartosc pary i nastepnie ja zmienia.
     * @param wartosc Nowa wartosc pary.
     */
    public void setWartosc(double wartosc)
    {
        this.wartosc = wartosc;
    }

    @Override
    public String toString()
    {
        return "< " + klucz + ", " + String.valueOf(wartosc) + " >";
    }


    /**
     * Obiekty klasy Para sa rowne, gdy maja identyczny klucz.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Para para = (Para) o;
        return Objects.equals(klucz, para.klucz);
    }


}
