package narzedzia;

/**
 * Abstrakcyjna klasa implementująca funkcje zero-argumentowe
 */

public abstract class Funkcja0Arg extends Funkcja
{
    /**
     * @return zwraca arność funkcji
     */
    @Override
    public int arność()
    {
        return 0;
    }

    /**
     * @return zwraca liczbę brakujących argumentów
     */
    @Override
    public int brakująceArgumenty()
    {
        return 0;
    }

    /**
     * Dodaje argument
     * @param arg argument
     * @throws ONP_BłędneWyrażenie jeśli funkcja posiada już wszystkie potrzebna argumenty, metoda zwraca wyjątek
     */
    @Override
    public void dodajArgument(double arg)throws ONP_BłędneWyrażenie
    {
        throw new ONP_BłędneWyrażenie();
    }
}
