package narzedzia;

/**
 * Klasa implementująca Liczbę.
 */

public class Liczba extends Operand
{
    Liczba(double wartosc)
    {
        this.wartosc = wartosc;
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby
     */
    @Override
    public double oblicz() { return wartosc; }

    @Override
    public String toString() {
        return String.valueOf(wartosc);
    }

    /**
     * Obiekty klasy Liczba są równe, gdy ich wartości są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liczba liczba = (Liczba) o;
        return Double.compare(liczba.wartosc, wartosc) == 0;
    }
}
