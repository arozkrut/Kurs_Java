package narzedzia;

import java.util.Objects;

/**
 * Funkcja arcctg(x)
 */

public class Acot extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Acot(arg)
     */
    @Override
    public double oblicz() throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        if(arg < 0)return Math.atan(1/arg) + Math.PI;
        if(arg > 0)return Math.atan(1/arg);
        return Math.PI/2;
    }

    @Override
    public String toString()
    {
        return "arcctg("+arg+")";
    }

    /**
     * Obiekty klasy Acot są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acot acot = (Acot) o;
        return Objects.equals(arg, acot.arg);
    }
}
