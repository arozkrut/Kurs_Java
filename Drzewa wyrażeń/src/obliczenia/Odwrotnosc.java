package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję 1/x, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Odwrotnosc extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 2.
     * @param arg argument funkcji 1/x
     * @throws IllegalArgumentException Jeśli wartość argumentu jest równa 0, konstruktor wyrzuca wyjątek.
     */
    public Odwrotnosc(Wyrazenie arg)throws IllegalArgumentException
    {
        super(2, arg);
        if(!(arg.oblicz() != 0))throw new IllegalArgumentException("Dzielenie przez 0!");
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość 1/(arg)
     */
    @Override
    public double oblicz() {
        return 1/arg.oblicz();
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s = "1/";
        if(arg.priorytet < 3)s += "(";
        s += arg.toString();
        if(arg.priorytet < 3)s += ")";
        return s;
    }

    /**
     * Obiekty klasy Odwrotnosc są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Odwrotnosc odwrotnosc = (Odwrotnosc) o;
        return Objects.equals(arg, odwrotnosc.arg);
    }
}
