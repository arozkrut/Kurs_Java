package narzedzia;

import java.util.Objects;

/**
 * Funkcja ln(x)
 */

public class Ln extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość ln(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        if(!(arg > 0))throw new ONP_BłędneWyrażenie();
        return Math.log(arg);
    }

    @Override
    public String toString()
    {
        return "ln("+arg+")";
    }

    /**
     * Obiekty klasy Ln są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ln ln = (Ln) o;
        return Objects.equals(arg, ln.arg);
    }
}
