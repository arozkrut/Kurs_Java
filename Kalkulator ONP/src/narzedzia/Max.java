package narzedzia;

import java.util.Objects;

/**
 * Funkcja max(x, y)
 */

public class Max extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczone maksimum
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.max(arg1, arg2);
    }

    @Override
    public String toString()
    {
        return "max("+arg1+","+arg2+")";
    }

    /**
     * Obiekty klasy Maksimum są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Max maksimum = (Max) o;
        return Objects.equals(arg1, maksimum.arg1) && Objects.equals(arg2, maksimum.arg2);
    }
}
