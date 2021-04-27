package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję arctg(x), jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Arctan extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg argument arctg
     */
    public Arctan(Wyrazenie arg)
    {
        super(3, arg);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość Arctan(arg)
     */
    @Override
    public double oblicz()
    {
        return Math.atan(arg.oblicz());
    }

    @Override
    public String toString()
    {
        return "arctg("+arg.toString()+")";
    }

    /**
     * Obiekty klasy Arctan są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arctan arctan = (Arctan) o;
        return Objects.equals(arg, arctan.arg);
    }
}
