package obliczenia;

import struktury.Para;
import struktury.ZbiorNaTablicyDynamicznej;

import java.util.Objects;

/**
 * Klasa implementuje zmienną wyrażoną symbolem i wartością jako liść drzew wyrażeń arytmetycznych.
 * Wszystkie wartości zmiennych i ich symbole są przechowywane w zbiorze.
 */

public class Zmienna extends Wyrazenie
{
    /**
     * Zbiór przechowywujący wartości zmiennych i ich symbole.
     */
    private static final ZbiorNaTablicyDynamicznej zbior;
    static
    {
        zbior = new ZbiorNaTablicyDynamicznej();
        try
        {
            zbior.wstaw(new Para("x", 2.0));
            zbior.wstaw(new Para("y", 16.0));
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    private final double wart;
    private final String nazwa;

    /**
     * Konstruktor odczytuje wartość zmiennej ze zbioru na postawie podanej nazwy. Ustawia priorytet klasy na 4.
     * @param nazwa symbol zmiennej
     * @throws IllegalArgumentException Jeśli zmienna o podanej nazwie nie istnieje w zbiorze, zwracany jest wyjątek.
     */
    public Zmienna(String nazwa)throws IllegalArgumentException
    {
        super(4);

        try
        {
            wart = zbior.czytaj(nazwa);
            this.nazwa = nazwa;
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Nie zadeklarowano zmiennej o podanej nazwie");
        }
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość zmiennej
     */
    @Override
    public double oblicz()
    {
        return wart;
    }

    @Override
    public String toString()
    {
        return nazwa;
    }

    /**
     * Obiekty klasy Zmienna są równe, gdy ich nazwy są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zmienna zmienna = (Zmienna) o;
        return Objects.equals(nazwa, zmienna.nazwa);
    }

    public static void dodajZmiennaDoZbioru(String nazwa, double wart)
    {
        try
        {
            zbior.wstaw(new Para(nazwa, wart));
        }
        catch (Exception e) { throw new IllegalArgumentException(e.getMessage()); }
    }
}
