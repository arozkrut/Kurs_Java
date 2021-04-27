package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje funkcję f(a, b) = loga(b), jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Logarytmowanie extends Operator2Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 3.
     * @param podstawa podstawa logarytmu
     * @param liczbaLog liczba logarytmowana
     * @throws IllegalArgumentException Jeśli wartość podstawy lub liczby logarytmowanej jest niezgodna z ich dzidziną,
     * konstruktor wyrzuca wyjątek.
     */
    public Logarytmowanie(Wyrazenie podstawa, Wyrazenie liczbaLog)throws IllegalArgumentException
    {
        super(3, podstawa, liczbaLog);

        if(podstawa.oblicz() < 0.0 || !(podstawa.oblicz() != 1) || liczbaLog.oblicz() < 0.0 || !(podstawa.oblicz() != 0.0) || !(liczbaLog.oblicz() != 0.0))throw new IllegalArgumentException();
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony logarytm
     */
    @Override
    public double oblicz()
    {
        return Math.log(arg2.oblicz())/Math.log(arg.oblicz());
    }

    @Override
    public String toString()
    {
        return "log"+arg.toString()+"("+arg2.toString()+")";
    }

    /**
     * Obiekty klasy Logarytmowanie są równe, gdy ich podstawy i liczby logarytmowane są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logarytmowanie logarytmowanie = (Logarytmowanie) o;
        return Objects.equals(arg, logarytmowanie.arg) && Objects.equals(arg2, logarytmowanie.arg2);
    }
}
