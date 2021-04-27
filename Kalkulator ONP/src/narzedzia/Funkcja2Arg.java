package narzedzia;

/**
 * Abstrakcyjna klasa implementująca funkcje dwu-argumentowe
 */

public abstract class Funkcja2Arg extends Funkcja
{
    double arg1, arg2;
    boolean def1=false, def2=false;
    /**
     * @return zwraca arność funkcji
     */
    @Override
    public int arność()
    {
        return 2;
    }

    /**
     * @return zwraca liczbę brakujących argumentów
     */
    @Override
    public int brakująceArgumenty()
    {
        if(def1 && def2)return 0;
        if(def1 || def2)return 1;
        return 2;
    }

    /**
     * Dodaje argument
     * @param arg argument
     * @throws ONP_BłędneWyrażenie jeśli funkcja posiada już wszystkie potrzebna argumenty, metoda zwraca wyjątek
     */
    @Override
    public void dodajArgument(double arg)throws ONP_BłędneWyrażenie
    {
        if(!def2)
        {
            arg2 = arg;
            def2 = true;
        }
        else if(!def1)
        {
            arg1 = arg;
            def1 = true;
        }
        else throw new ONP_BłędneWyrażenie();
    }
}
