package narzedzia;

/**
 * Abstrakcyjna klasa dla wyjątków kalkulatora ONP
 */

public class WyjątekONP extends Exception
{
    WyjątekONP(String message)
    {
        super("Wyjątek ONP: " + message);
    }
}
