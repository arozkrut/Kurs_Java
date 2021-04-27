package struktury;

/**
 * Klasa implementuje zbior par na tablicy dynamicznej.
 * @author Aleksandra Rozkrut
 */

public class ZbiorNaTablicyDynamicznej extends ZbiorNaTablicy
{
    /**
     * Konstruktor tworzy pusta tablice o rozmiarze 2.
     */
    public ZbiorNaTablicyDynamicznej()
    {
        super();
    }


    /**
     * Metoda ma wstawiac do zbioru nowa pare. Jesli w zbiorze nie ma już miejsca
     * metoda zwieksza rozmiar tablicy 2 razy.
     * @param p nowa para
     * @throws Exception jesli para o kluczu pary p już istnieje,
     * metoda zwraca wyjatek
     */
    public void wstaw (Para p) throws Exception
    {
        try
        {
            czytaj(p.klucz);
            throw new Exception("Para już istnieje");
        }
        catch (Exception e)
        {
            if(ile == pary.length)
            {
                Para[] nowePary = new Para[2*ile];
                System.arraycopy(pary, 0, nowePary, 0, ile);
                pary = nowePary;
            }
            pary[ile] = p;
            ile++;
        }

    }


    /**
     * Metoda ma wstawic do zbioru nowa albo zaktualizowac pare.
     * Jesli w zbiorze nie ma już miejsca
     * metoda zwieksza rozmiar tablicy 2 razy.
     * @param p nowa para
     */
    public void ustaw (Para p)
    {
        for (int i = 0; i < ile; i++)
        {
            if(pary[i].klucz.equals(p.klucz))
            {
                pary[i] = p;
                return;
            }
        }

        if(ile == pary.length)
        {
            Para[] nowePary = new Para[2*ile];
            System.arraycopy(pary, 0, nowePary, 0, ile);
            pary = nowePary;
        }

        pary[ile] = p;
        ile++;
    }
}
