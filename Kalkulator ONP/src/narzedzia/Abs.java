package narzedzia;

import java.util.Objects;

/**
 * Funkcja |x|
 */

public class Abs extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość |arg|
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.abs(arg);
    }

    @Override
    public String toString()
    {
        return "|" + arg + "|";
    }

    /**
     * Obiekty klasy WartBezwzgl są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abs wartBezwzgl = (Abs) o;
        return Objects.equals(arg, wartBezwzgl.arg);
    }
}
