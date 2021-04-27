package narzedzia;

/**
 * Wyjątek ONP zgłaszany, gdy funkcja chce dzielić przez 0
 */

public class ONP_DzieleniePrzez0 extends WyjątekONP
{
    public ONP_DzieleniePrzez0()
    {
        super("Dzielenie przez 0");
    }
}
