package narzedzia;

/**
 * Abstrakcyjna klasa wszystkich operand, funkcji i operatorów
 */

public abstract class Symbol implements Obliczalny
{
    @Override
    public String toString() {
        return Symbol.class.getName();
    }
}
