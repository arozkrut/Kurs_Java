package narzedzia;

import java.util.Objects;

/**
 * Funkcja sin(x)
 */

public class Sin extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Sinus(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.sin(arg);
    }

    @Override
    public String toString()
    {
        return "sin("+arg+")";
    }

    /**
     * Obiekty klasy Sinus są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sin sinus = (Sin) o;
        return Objects.equals(arg, sinus.arg);
    }
}
