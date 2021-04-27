package narzedzia;

import java.util.Objects;

/**
 * Operator dodawania
 */

public class Dodawanie extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona suma składników
     */
    @Override
    public double oblicz()
    {
        return arg1 + arg2;
    }

    @Override
    public String toString()
    {
        return arg1 + "+" + arg2;
    }

    /**
     * Obiekty klasy Dodawanie są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dodawanie dodawanie = (Dodawanie) o;
        return Objects.equals(arg1, dodawanie.arg1) && Objects.equals(arg2, dodawanie.arg2);
    }
}
