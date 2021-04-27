package obliczenia;
import java.util.Objects;

/**
 * Klasa implementuje funkcję -x, jako węzeł drzew wyrażeń arytmetycznych.
 */

public class Przeciwienstwo extends Operator1Arg
{
    /**
     * Konstruktor. Ustawia priorytet klasy na 1.
     * @param arg argument funkcji -x
     */
    public Przeciwienstwo(Wyrazenie arg)
    {
        super(1, arg);
    }

    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość -arg
     */
    @Override
    public double oblicz() {
        return -arg.oblicz();
    }

    /**
     * Metoda, jeśli należy, nawiasuje wyrażenie.
     */
    @Override
    public String toString()
    {
        String s="-";
        if(arg.priorytet < 2)s += "(";
        s += arg.toString();
        if(arg.priorytet < 2)s += ")";
        return s;
    }

    /**
     * Obiekty klasy Przeciwienstwo są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Przeciwienstwo przeciwienstwo = (Przeciwienstwo) o;
        return Objects.equals(arg, przeciwienstwo.arg);
    }
}
