package obliczenia;

import java.util.Objects;

/**
 * Klasa implementuje stałe takie jak π, e i φ jako liść drzew wyrażeń arytmetycznych.
 */

public class Stala extends Wyrazenie
{
    private final double wart;
    private final String symbol;

    /**
     * Konstruktor. Ustawia priorytet klasy na 4.
     * @param nazwa symbol stałej
     * @throws IllegalArgumentException Jeśli nazwa jets niepoprawna zwracany jest wyjątek.
     */
    public Stala(String nazwa) throws IllegalArgumentException
    {
        super(4);

        if (nazwa.equals("pi"))
        {
            wart = Math.PI;
            symbol = "π";
        }
        else if (nazwa.equals("e"))
        {
            wart = Math.E;
            symbol = "e";
        }
        else if (nazwa.equals("fi"))
        {
            wart = 1.618033988749895;
            symbol = "φ";
        }
        else throw new IllegalArgumentException("Brak stałej o podanej nazwie");
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby
     */
    @Override
    public double oblicz() { return wart; }

    @Override
    public String toString() { return symbol; }

    /**
     * Obiekty klasy Stala są równe, gdy ich symbole są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stala stala = (Stala) o;
        return Objects.equals(symbol, stala.symbol);
    }
}