package narzedzia;

import java.util.Objects;

/**
 * Funkcja min(x,y)
 */

public class Min extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczone minimum
     */
    @Override
    public double oblicz() throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.min(arg1, arg2);
    }

    @Override
    public String toString()
    {
        return "min("+arg1+","+arg2+")";
    }

    /**
     * Obiekty klasy Minimum są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Min minimum = (Min) o;
        return Objects.equals(arg1, minimum.arg1) && Objects.equals(arg2, minimum.arg2);
    }
}
