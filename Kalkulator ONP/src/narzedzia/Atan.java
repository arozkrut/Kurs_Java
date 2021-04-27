package narzedzia;

import java.util.Objects;

/**
 * Funkcja arctg(x)
 */

public class Atan extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Atan(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.atan(arg);
    }

    @Override
    public String toString()
    {
        return "arctg("+arg+")";
    }

    /**
     * Obiekty klasy Atan są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atan atan = (Atan) o;
        return Objects.equals(arg, atan.arg);
    }
}
