package narzedzia;

import java.util.Objects;

/**
 * Funkcja cos(x)
 */

public class Cos extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Cosinus(arg)
     */
    @Override
    public double oblicz() throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.cos(arg);
    }

    @Override
    public String toString()
    {
        return "cos("+arg+")";
    }

    /**
     * Obiekty klasy Cosinus są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cos cosinus = (Cos) o;
        return Objects.equals(arg, cosinus.arg);
    }
}
