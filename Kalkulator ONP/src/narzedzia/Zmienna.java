package narzedzia;

import java.util.Objects;
import java.util.regex.Pattern;

public class Zmienna extends Operand
{


    private final String nazwa;

    /**
     * Konstruktor odczytuje wartość zmiennej ze zbioru na postawie podanej nazwy. Ustawia priorytet klasy na 4.
     * @param nazwa symbol zmiennej
     * @throws IllegalArgumentException Jeśli zmienna o podanej nazwie nie istnieje w zbiorze, zwracany jest wyjątek.
     */
    public Zmienna(String nazwa, Double wart)throws IllegalArgumentException
    {
        this.nazwa = nazwa;
        this.wartosc = wart;
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return wartość zmiennej
     */
    @Override
    public double oblicz()
    {
        return wartosc;
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

}
