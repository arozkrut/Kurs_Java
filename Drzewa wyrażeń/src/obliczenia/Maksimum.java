package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję f(a, b) = max{a, b}, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Maksimum extends  Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg1 argument pierwszy
     * @param arg2 argument drugi
     */
    public Maksimum(Wyrazenie arg1, Wyrazenie arg2)
    {
        super(3, arg1, arg2);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczone maksimum
     */
    @Override
    public double oblicz()
    {
        return Math.max(arg.oblicz(), arg2.oblicz());
    }

    @Override
    public String toString()
    {
        return "max("+arg.toString()+","+arg2.toString()+")";
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
        Maksimum maksimum = (Maksimum) o;
        return Objects.equals(arg, maksimum.arg) && Objects.equals(arg2, maksimum.arg2);
    }
}
