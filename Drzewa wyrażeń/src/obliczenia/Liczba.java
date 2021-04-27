package obliczenia;

/**
 * Klasa implementuje liczby jako liść drzew wyrażeń arytmetycznych.
 */

public class Liczba extends Wyrazenie
{
    private final double wart;

    /**
     * Konstruktor. Ustawia priorytet klasy na 4.
     * @param wart
     */
    public Liczba(double wart)
    {
        super(4);
        this.wart = wart;
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby
     */
    @Override
    public double oblicz() { return wart; }

    @Override
    public String toString() {
        return String.valueOf(wart);
    }

    o/**
     * Obiekty klasy Liczba są równe, gdy ich wartości są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liczba liczba = (Liczba) o;
        return Double.compare(liczba.wart, wart) == 0;
    }

}
