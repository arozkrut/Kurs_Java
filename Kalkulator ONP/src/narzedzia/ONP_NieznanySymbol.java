package narzedzia;

/**
 * Wyjątek ONP zgłaszany, gdy podany zostanie nieznany symbol
 */

public class ONP_NieznanySymbol extends WyjątekONP
{
    public ONP_NieznanySymbol()
    {
        super("Nieznany symbol");
    }
}
