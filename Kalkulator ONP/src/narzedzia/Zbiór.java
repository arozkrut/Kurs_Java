package narzedzia;

import java.util.TreeMap;

/**
 * Zbiór zmiennych
 */

public class Zbiór
{
    private TreeMap<String, Double> zbior;

    public Zbiór()
    {
        zbior = new TreeMap<>();
    }

    /**
     * Metoda dodaje nową parę klucz, wartość do zbioru
     * @param nazwa klucz
     * @param wart wartość
     */
    public void dodaj(String nazwa, Double wart)
    {
        zbior.put(nazwa, wart);
    }

    /**
     * Metoda zwraca wartość zmiennej dla podanej nazwy
     * @param nazwa nazwa zmiennej
     * @return wartość zmiennej
     * @throws ONP_NieznanySymbol jeśli zbiór nie posiada zmiennej o podanej nazwie, metoda zwraca wyjątek
     */
    public double zwroc(String nazwa)throws ONP_NieznanySymbol
    {
        if(!zbior.containsKey(nazwa))throw new ONP_NieznanySymbol();
        return zbior.get(nazwa);
    }

    /**
     * Metoda usuwa parę o podanym kluczu ze zbioru
     * @param nazwa klucz - nazwa zmiennej
     */
    public void clear(String nazwa)
    {
        zbior.remove(nazwa);
    }

    /**
     * Metoda usuwa wszytskie zmienne ze zbioru
     */
    public void clearAll()
    {
        zbior.clear();
    }
}
