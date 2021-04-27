package obliczenia;

/**
 * Abstrakcyjna klasa wyrażeń na drzewie wyrażeń arytmetycznych.
 */

public abstract class Wyrazenie implements Obliczalny
{
    final int priorytet;

    Wyrazenie(int priorytet)
    {
        this.priorytet = priorytet;
    }

    /**
     * metoda sumująca wyrażenia
     * @param wyr tablica wyrażeń
     * @return suma wyrażeń podanych na wejściu
     */
    public static double suma (Wyrazenie... wyr) 
    {
        double suma = 0;
        for (Wyrazenie wyrazenie: wyr)suma += wyrazenie.oblicz();
        return suma;
    }

    /**
     * metoda mnożąca wyrażenia
     * @param wyr tablica wyrażeń
     * @return iloczyn wyrażeń podanych na wejściu
     */
    public static double iloczyn (Wyrazenie... wyr)
    {
        double iloczyn = 1.0;
        for (Wyrazenie wyrazenie: wyr)iloczyn *= wyrazenie.oblicz();
        return iloczyn;
    }
}
