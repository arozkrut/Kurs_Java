package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje operację dodawania, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Dodawanie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 1.
     * @param arg1 składnik pierwszy
     * @param arg2 składnik drugi
     */
    public Dodawanie(Wyrazenie arg1, Wyrazenie arg2)
    {
        super(1, arg1, arg2);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona suma składników
     */
    @Override
    public double oblicz()
    {
        return arg.oblicz() + arg2.oblicz();
    }

    @Override
    public String toString()
    {
        return arg.toString() + "+" + arg2.toString();
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
        return Objects.equals(arg, dodawanie.arg) && Objects.equals(arg2, dodawanie.arg2);
    }
}
