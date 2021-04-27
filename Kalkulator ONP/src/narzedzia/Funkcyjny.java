package narzedzia;

/**
 * Interfejs funkcji zawiera metody zwracające arność, brakujące argumenty oraz metodę dodającą argumenty.
 */

public interface Funkcyjny extends Obliczalny
{
    int arność ();
    int brakująceArgumenty ();
    void dodajArgument (double arg)throws ONP_BłędneWyrażenie;
}
