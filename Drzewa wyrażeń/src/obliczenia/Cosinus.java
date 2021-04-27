package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję cosinus(x), jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Cosinus extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg argument cosinusa
     */
    public Cosinus(Wyrazenie arg)
    {
        super(3, arg);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Cosinus(arg)
     */
    @Override
    public double oblicz()
    {
        return Math.cos(arg.oblicz());
    }

    @Override
    public String toString()
    {
        return "cos("+arg.toString()+")";
    }

    /**
     * Obiekty klasy Cosinus są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cosinus cosinus = (Cosinus) o;
        return Objects.equals(arg, cosinus.arg);
    }
}
