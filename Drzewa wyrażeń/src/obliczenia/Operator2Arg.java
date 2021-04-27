package obliczenia;

/**
 * Abstrakcyjna klasa wyrażeń dwuargumentowych na drzewie wyrażeń arytmetycznych.
 */

public abstract class Operator2Arg extends Operator1Arg
{
    final Wyrazenie arg2;

    Operator2Arg(int priorytet, Wyrazenie arg, Wyrazenie arg2)
    {
        super(priorytet, arg);
        this.arg2 = arg2;
    }
}
