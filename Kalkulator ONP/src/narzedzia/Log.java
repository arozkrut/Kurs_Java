package narzedzia;

import java.util.Objects;

/**
 * Funkcja log a(b)
 */

public class Log extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony logarytm
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        if(arg1 < 0.0 || !(arg1 != 1) || arg2 < 0.0 || !(arg1 != 0.0) || !(arg2 != 0.0))throw new ONP_BłędneWyrażenie();
        return Math.log(arg2)/Math.log(arg1);
    }



    @Override
    public String toString()
    {
        return "log"+arg1+"("+arg2+")";
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
        Log logarytmowanie = (Log) o;
        return Objects.equals(arg1, logarytmowanie.arg1) && Objects.equals(arg2, logarytmowanie.arg2);
    }
}
