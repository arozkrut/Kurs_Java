package narzedzia;

/**
 * Wyjątek ONP zgłaszany, gdy podane wyrażenie lub symbol jest niepoprawne
 */

public class ONP_BłędneWyrażenie extends WyjątekONP
{
    public ONP_BłędneWyrażenie()
    {
        super("Błędne wyrażenie");
    }
}
