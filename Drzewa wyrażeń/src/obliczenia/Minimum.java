package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję f(a, b) = min{a, b}, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Minimum extends Operator2Arg 
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg1 argument pierwszy
     * @param arg2 argument drugi
     */
    public Minimum(Wyrazenie arg1, Wyrazenie arg2)
    {
        super(3, arg1, arg2);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczone minimum
     */
    @Override
    public double oblicz()
    {
        return Math.min(arg.oblicz(), arg2.oblicz());
    }

    @Override
    public String toString()
    {
        return "min("+arg.toString()+","+arg2.toString()+")";
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
        Minimum minimum = (Minimum) o;
        return Objects.equals(arg, minimum.arg) && Objects.equals(arg2, minimum.arg2);
    }
}
