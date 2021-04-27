package narzedzia;

/**
 * Wyjątek ONP zgłaszany, gdy kolejka, stos lub lista są puste, a chcemy zdejmować z nich element
 */

public class ONP_PustyStos extends WyjątekONP
{
    public ONP_PustyStos()
    {
        super("Stos jest pusty");
    }
}
