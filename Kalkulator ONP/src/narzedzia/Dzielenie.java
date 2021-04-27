package narzedzia;

/**
 * Operator dzielenia
 */

public class Dzielenie extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloraz argumentów
     */
    @Override
    public double oblicz()
    {
        return arg1 / arg2;
    }

    @Override
    public String toString() {
        return arg1 + "/" + arg2;
    }
}
