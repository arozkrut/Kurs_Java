package narzedzia;

/**
 * Klasa stałej pi
 */

public class Pi extends Funkcja0Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość liczby pi
     */
    @Override
    public double oblicz() { return Math.PI; }

    @Override
    public String toString() { return "π"; }

    /**
     * Obiekty klasy Pi są równe.
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
