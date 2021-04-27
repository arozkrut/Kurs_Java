package narzedzia;

/**
 * Abstrakcyjna klasa implementująca funkcje jedno-argumentowe
 */

public abstract class Funkcja1Arg extends Funkcja
{
    double arg;
    boolean def=false;
    /**
     * @return zwraca arność funkcji
     */
    @Override
    public int arność()
    {
        return 1;
    }

    /**
     * @return zwraca liczbę brakujących argumentów
     */
    @Override
    public int brakująceArgumenty()
    {
        if(def)return 0;
        return 1;
    }

    /**
     * Dodaje argument
     * @param arg argument
     * @throws ONP_BłędneWyrażenie jeśli funkcja posiada już wszystkie potrzebna argumenty, metoda zwraca wyjątek
     */
    @Override
    public void dodajArgument(double arg)throws ONP_BłędneWyrażenie
    {
        if(!def)
        {
            this.arg = arg;
            def = true;
        }
        else throw new ONP_BłędneWyrażenie();
    }
}
