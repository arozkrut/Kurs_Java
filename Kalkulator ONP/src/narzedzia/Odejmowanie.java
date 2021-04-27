package narzedzia;

import java.util.Objects;

/**
 * Operator odejmowania
 */

public class Odejmowanie extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloraz argumentów
     */
    @Override
    public double oblicz()
    {
        return arg1 - arg2;
    }

    @Override
    public String toString() {
        return arg1 + "-" + arg2;
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
        Odejmowanie dzielenie = (Odejmowanie) o;
        return Objects.equals(arg1, dzielenie.arg1) && Objects.equals(arg2, dzielenie.arg2);
    }
}
