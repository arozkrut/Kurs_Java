package narzedzia;

/**
 * Stała e
 */

public class E extends Funkcja0Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby e
     */
    @Override
    public double oblicz() { return Math.E; }

    @Override
    public String toString() { return "e"; }

    /**
     * Obiekty klasy E są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }
}
