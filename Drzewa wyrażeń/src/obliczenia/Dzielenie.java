package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje operację dzielenia, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Dzielenie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 2.
     * @param arg1 dzielna
     * @param arg2 dzielnik
     * @throws IllegalArgumentException Jeśli wartość dzielnika wynosi 0, konstruktor wyrzuca wyjątek.
     */
    public Dzielenie(Wyrazenie arg1, Wyrazenie arg2)throws IllegalArgumentException
    {
        super(2, arg1, arg2);
        if(!(arg2.oblicz() != 0))throw new IllegalArgumentException("Dzielenie przez 0!");
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloraz argumentów
     */
    @Override
    public double oblicz()
    {
        return arg.oblicz() / arg2.oblicz();
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s = "";

        if(arg.priorytet < 2)s += "(";
        s += arg.toString();
        if(arg.priorytet < 2)s += ")";

        s += "/";

        if(arg2.priorytet < 2)s += "(";
        s += arg2.toString();
        if(arg2.priorytet < 2)s += ")";
        return s;
    }

    /**
     * Obiekty klasy Dzielenie są równe, gdy ich pierwsze i drugie argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dzielenie dzielenie = (Dzielenie) o;
        return Objects.equals(arg, dzielenie.arg) && Objects.equals(arg2, dzielenie.arg2);
    }
}
