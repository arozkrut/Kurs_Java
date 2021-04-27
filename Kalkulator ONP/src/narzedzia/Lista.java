package narzedzia;

import java.util.LinkedList;

/**
 * Lista napisów.
 */

public class Lista
{
    private LinkedList<String> lista;

    public Lista()
    {
        lista = new LinkedList<>();
    }

    /**
     * Dodaje napis do listy.
     * @param s argument - napis
     */
    public void dodaj(String s)
    {
        lista.add(s);
    }

    /**
     * Zwraca element w liście.
     * @param index indeks elementu
     * @return element o indeksie index
     * @throws ONP_PustyStos jeśli lista jest pusta, zwracany jest wyjątek
     */
    public String zwroc(int index)throws ONP_PustyStos
    {
        if(lista.isEmpty())throw new ONP_PustyStos();
        return lista.get(index);
    }

    /**
     * @return stan listy
     */
    public boolean isEmpty()
    {
        return lista.isEmpty();
    }
}
