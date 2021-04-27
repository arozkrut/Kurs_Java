package narzedzia;

import java.util.Objects;

/**
 * Funkcja frac(x)
 */

public class Frac extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość frac(arg)
     */
    @Override
    public double oblicz() throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return arg - Math.floor(arg);
    }

    @Override
    public String toString()
    {
        return "{" + arg + "}";
    }

    /**
     * Obiekty klasy obj są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frac obj = (Frac) o;
        return Objects.equals(arg, obj.arg);
    }
}
