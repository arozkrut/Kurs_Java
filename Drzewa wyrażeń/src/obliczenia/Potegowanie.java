package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję f(a, b) = a^b, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Potegowanie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param podstawa podstawa potęgowania
     * @param wykladnik wykładnik potęgowania
     */
    public Potegowanie(Wyrazenie podstawa, Wyrazenie wykladnik)
    {
        super(3, podstawa, wykladnik);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloczyn argumentów
     */
    @Override
    public double oblicz()
    {
        return Math.pow(arg.oblicz(), arg2.oblicz());
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s="";

        if(arg.priorytet < 4)s += "(";
        s += arg.toString();
        if(arg.priorytet < 4)s += ")";

        s += "^";

        if(arg2.priorytet < 3)s += "(";
        s += arg2.toString();
        if(arg2.priorytet < 3)s += ")";

        return s;
    }

    /**
     * Obiekty klasy Dodawanie są równe, gdy ich podstawy i wykładniki są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Potegowanie potegowanie = (Potegowanie) o;
        return Objects.equals(arg, potegowanie.arg) && Objects.equals(arg2, potegowanie.arg2);
    }
}
