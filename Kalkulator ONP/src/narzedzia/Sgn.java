package narzedzia;

import java.util.Objects;

/**
 * Funkcja sgn(x) = ( -1: x<0; 0: x=0; 1: x>0)
 */

public class Sgn extends Funkcja1Arg
{
    /**
     * Klasa implementuje interfejs Obliczalny.
     * @return obliczona wartość sgn(arg)
     */
    @Override
    public double oblicz()throws ONP_BłędneWyrażenie
    {
        if(brakująceArgumenty() > 0)throw new ONP_BłędneWyrażenie();
        if(arg < 0)return -1.0;
        if(arg > 0)return 1.0;
        return arg;
    }

    @Override
    public String toString()
    {
        return "sgn(" + arg + ")";
    }

    /**
     * Obiekty klasy obj są równe, gdy argumenty są równe.
     * @param o Porownywany obiekt
     * @return true, jesli obiekty sa rowne, false wpp.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sgn obj = (Sgn) o;
        return Objects.equals(arg, obj.arg);
    }
}
