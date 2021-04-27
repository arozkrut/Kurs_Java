package narzedzia;

/**
 * Operator mnożenia
 */

public class Mnozenie extends Funkcja2Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczony iloczyn czynników
     */
    @Override
    public double oblicz()
    {
        return arg1 * arg2;
    }

    @Override
    public String toString() {
        return arg1 + "*" + arg2;
    }
}
