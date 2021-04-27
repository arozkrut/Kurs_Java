package narzedzia;

import java.util.Objects;

/**
 * Funkcja ceil(x)
 */

public class Ceil extends Funkcja1Arg 
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość ceil(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.ceil(arg);
    }

    @Override
    public String toString()
    {
        return "ceil(" + arg + ")";
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
        Ceil obj = (Ceil) o;
        return Objects.equals(arg, obj.arg);
    }
}
