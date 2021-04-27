package narzedzia;

import java.util.ArrayDeque;

/**
 * Stos wartości pośrednich Double
 */

public class Stos
{
    private ArrayDeque<Double> stos;

    public Stos()
    {
        stos = new ArrayDeque<>();
    }

    /**
     * Metoda dodaje argument do stosu
     * @param wart argument
     */
    public void dodaj(Double wart)
    {
        stos.add(wart);
    }

    /**
     * Metoda wzraca element ze szczytu stosu i zdejmuje go z niego
     * @return element ze szczytu stosu
     * @throws ONP_PustyStos jeśli stos jest pusty metoda wyrzuca wyjątek
     */
    public double zwroc()throws ONP_PustyStos
    {
        if(stos.isEmpty())throw new ONP_PustyStos();
        Double d = stos.getLast();
        stos.removeLast();
        return d;
    }
    public boolean isEmpty()
    {
        return stos.isEmpty();
    }
}
