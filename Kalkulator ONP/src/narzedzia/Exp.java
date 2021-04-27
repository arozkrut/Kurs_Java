package narzedzia;

import java.util.Objects;

/**
 * Funkcja e^x
 */

public class Exp extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość e^arg
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.pow(Math.E, arg);
    }

    @Override
    public String toString()
    {
        return "e^("+arg+")";
    }

    /**
     * Obiekty klasy Exp są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exp exp = (Exp) o;
        return Objects.equals(arg, exp.arg);
    }
}
