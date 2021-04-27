package narzedzia;

import java.util.Objects;

/**
 * Funkcja floor(x)
 */

public class Floor extends Funkcja1Arg 
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość floor(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.floor(arg);
    }

    @Override
    public String toString()
    {
        return "floor(" + arg + ")";
    }

    /**
     * Obiekty klasy fl są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor fl = (Floor) o;
        return Objects.equals(arg, fl.arg);
    }
}
