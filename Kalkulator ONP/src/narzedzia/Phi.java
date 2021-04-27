package narzedzia;

/**
 * Klasa stałej phi
 */

public class Phi extends Funkcja0Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby phi
     */
    @Override
    public double oblicz() { return 1.618033988749895; }

    @Override
    public String toString() { return "φ"; }

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
