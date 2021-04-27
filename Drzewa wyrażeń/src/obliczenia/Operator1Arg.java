package obliczenia;

/**
 * Abstrakcyjna klasa wyrażeń jednoargumentowych na drzewie wyrażeń arytmetycznych.
 */

public abstract class Operator1Arg extends Wyrazenie
{
    final Wyrazenie arg;

    Operator1Arg(int priorytet, Wyrazenie arg)
    {
        super(priorytet);
        this.arg = arg;
    }
}
