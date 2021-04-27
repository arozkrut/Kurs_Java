package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję sinus(x), jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Sinus extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg argument sinusa
     */
    public Sinus(Wyrazenie arg)
    {
        super(3, arg);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Sinus(arg)
     */
    @Override
    public double oblicz()
    {
        return Math.sin(arg.oblicz());
    }

    @Override
    public String toString()
    {
        return "sin("+arg.toString()+")";
    }

    /**
     * Obiekty klasy Sinus są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sinus sinus = (Sinus) o;
        return Objects.equals(arg, sinus.arg);
    }
}
