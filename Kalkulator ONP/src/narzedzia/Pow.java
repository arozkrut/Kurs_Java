package narzedzia;

import java.util.Objects;

/**
 * Funkcja a^b
 */

public class Pow extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloczyn argumentów
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        return Math.pow(arg1, arg2);
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        return arg1 + "^" + arg2;
    }

    /**
     * Obiekty klasy Dodawanie są równe, gdy ich podstawy i wykładniki są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pow potegowanie = (Pow) o;
        return Objects.equals(arg1, potegowanie.arg1) && Objects.equals(arg2, potegowanie.arg2);
    }
}
