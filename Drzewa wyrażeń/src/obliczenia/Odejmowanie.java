package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje operację odejmowania, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Odejmowanie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 1.
     * @param arg1 odjemna
     * @param arg2 odjemnik
     */
    public Odejmowanie(Wyrazenie arg1, Wyrazenie arg2)
    {
        super(1, arg1, arg2);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona różnica argumentów
     */
    @Override
    public double oblicz()
    {
        return arg.oblicz() - arg2.oblicz();
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s = arg.toString();
        s += "-";
        if(arg2.priorytet < 2)s += "(";
        s += arg2.toString();
        if(arg2.priorytet < 2)s += ")";
        return s;
    }

    /**
     * Obiekty klasy Odejmowanie są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odejmowanie odejmowanie = (Odejmowanie) o;
        return Objects.equals(arg, odejmowanie.arg) && Objects.equals(arg2, odejmowanie.arg2);
    }
}
