package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję |x|, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class WartBezwzgl extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 4.
     * @param arg argument funkcji |x|
     */
    public WartBezwzgl(Wyrazenie arg)
    {
        super(4, arg);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość |arg|
     */
    @Override
    public double oblicz()
    {
        return Math.abs(arg.oblicz());
    }

    @Override
    public String toString()
    {
        return "|" + arg.toString() + "|";
    }

    /**
     * Obiekty klasy WartBezwzgl są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WartBezwzgl wartBezwzgl = (WartBezwzgl) o;
        return Objects.equals(arg, wartBezwzgl.arg);
    }
}
