package struktury;

/**
 * Klasa implementuje zbiór par na tablicy.
 * @author Aleksandra Rozkrut
 */

public class ZbiorNaTablicy extends Zbior
{
    /**
     * Konstruktor klasy.
     * @param rozmiar rozmiar zbioru
     * @throws Exception Jesli podany rozmiar jest mniejszy, niz
     * 2 metoda zwraca wyjatek.
     */
    public ZbiorNaTablicy(int rozmiar)throws Exception
    {
        if(rozmiar < 2) throw new Exception("Niepoprawny rozmiar");
        pary = new Para[rozmiar];
        ile = 0;
    }

    /**
     * Konstruktor uzywany tylko przez klase {@link ZbiorNaTablicyDynamicznej}
     */
    protected ZbiorNaTablicy()
    {
        pary = new Para[2];
        ile = 0;
    }


    /**
     * metoda ma szukac pary z okreslonym kluczem
     * @param k klucz
     * @return para o kluczu k
     * @throws Exception jesli para o kluczu k nie istnieje metoda wyrzuca wyjatek
     */
    public Para szukaj (String k) throws Exception
    {
        if(ile == 0)throw new Exception("Zbiór jest pusty");
        for (int i = 0; i < ile; i++)
        {
            if(pary[i].klucz.equals(k))return pary[i];
        }

        throw new Exception("Para o podanym kluczu nie istnieje");
    }


    /**
     * metoda ma odszukac pare i zwrócic wartosc zwiazana z kluczem
     * @param k klucz
     * @return wartosc pary o kluczu k
     * @throws Exception jesli para o kluczu k nie istnieje metoda wyrzuca wyjatek
     */
    public double czytaj (String k) throws Exception
    {
        if(ile == 0)throw new Exception("Zbiór jest pusty");
        for (int i = 0; i < ile; i++)
        {
            if(pary[i].klucz.equals(k))return pary[i].getWartosc();
        }

        throw new Exception("Para o podanym kluczu nie istnieje");
    }


    /**
     * metoda ma wstawiac do zbioru nowa pare
     * @param p nowa para
     * @throws Exception jesli w zbiorze nie ma juz miejsca lub para o kluczu pary p juz istnieje,
     * metoda zwraca wyjatek
     */
    public void wstaw (Para p) throws Exception
    {
        if(ile == pary.length)throw new Exception("Brak miejsca w zbiorze");

        try
        {
            czytaj(p.klucz);
            throw new Exception("Para juz istnieje");
        }
        catch (Exception e)
        {
            pary[ile] = p;
            ile++;
        }

    }



    /**
     * metoda ma wstawic do zbioru nowa albo zaktualizowac pare
     * @param p nowa para
     * @throws Exception jesli w zbiorze nie ma juz miejsca metoda zwraca wyjatek
     */
    public void ustaw (Para p) throws Exception
    {
        for (int i = 0; i < ile; i++)
        {
            if(pary[i].klucz.equals(p.klucz))
            {
                pary[i] = p;
                return;
            }
        }

        if(ile == pary.length)throw new Exception("Brak miejsca w zbiorze");

        pary[ile] = p;
        ile++;
    }


    /** metoda ma usunac wszystkie pary ze zbioru */
    public void czysc () { ile = 0; }


    /** metoda ma podac ile par jest przechowywanych w zbiorze
     * @return liczba par w zbiorze
     */
    public int ile () {return ile;}
}
