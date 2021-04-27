package struktury;

/**
 * Klasa abstrakcyjna za pomoca ktorej implementujemy zbiory na tablicach.
 * @author Aleksandra Rozkrut
 */

public abstract class Zbior
{
    protected Para[] pary;
    protected int ile;

    /** metoda ma szukac pary z okreslonym kluczem */
    public abstract Para szukaj (String k) throws Exception;
    /** metoda ma wstawiac do zbioru nowa pare */
    public abstract void wstaw (Para p) throws Exception;
    /** metoda ma odszukac pare i zwrocic wartosc zwiazana z kluczem */
    public abstract double czytaj (String k) throws Exception;
    /** metoda ma wstawic do zbioru nowa albo zaktualizowac pare */
    public abstract void ustaw (Para p) throws Exception;
    /** metoda ma usunac wszystkie pary ze zbioru */
    public abstract void czysc ();
    /** metoda ma podac ile par jest przechowywanych w zbiorze */
    public abstract int ile ();
}
