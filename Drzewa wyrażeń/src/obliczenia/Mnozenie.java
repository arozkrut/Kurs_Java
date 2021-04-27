package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje operację mnożenia, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Mnozenie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param arg1 czynnik pierwszy
     * @param arg2 czynnik drugi
     */
    public Mnozenie(Wyrazenie arg1, Wyrazenie arg2)
    {
        super(2, arg1, arg2);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloczyn czynników
     */
    @Override
    public double oblicz()
    {
        return arg.oblicz() * arg2.oblicz();
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s="";

        if(arg.priorytet < 2)s += "(";
        s += arg.toString();
        if(arg.priorytet < 2)s += ")";

        s += "*";

        if(arg2.priorytet < 2)s += "(";
        s+=arg2.toString();
        if(arg2.priorytet < 2)s += ")";
        return s;
    }

    /**
     * Obiekty klasy Mnozenie są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mnozenie mnozenie = (Mnozenie) o;
        return Objects.equals(arg, mnozenie.arg) && Objects.equals(arg2, mnozenie.arg2);
    }
}
