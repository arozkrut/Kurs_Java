package narzedzia;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kolejka instancji klasy Symbol
 */

public class Kolejka
{
    private ArrayDeque<Symbol> kolejka;
    private Zbiór zbiór;
    private static String[] nazwyFunkcji = {"+", "-", "*", "/", "Min","Max","Log","Pow", "Abs", "Sgn", "Floor", "Ceil", "Frac", "Sin", "Cos", "Atan", "Acot", "Ln", "Exp", "E", "Pi","Phi"};
    private static Pattern wzorNaZmienna;
    static
    {
        wzorNaZmienna = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
    }

    /**
     * Konstruktor.
     * @param zbiór zbiór zmiennych
     */
    public Kolejka(Zbiór zbiór)
    {
        kolejka = new ArrayDeque<>();
        this.zbiór = zbiór;
    }

    /**
     * Metoda dodaje argument do kolejki.
     * @param nazwa argument
     * @throws ONP_NieznanySymbol jeśli nazwa jest niepoprawna, metoda zwraca wyjątek
     */
    public void dodaj(String nazwa)throws ONP_NieznanySymbol
    {
        Symbol symbol;
        boolean funkcja = false;
        Matcher matcher;
        double pomocnicze;

        for (String s: nazwyFunkcji)
        {
            if(nazwa.equals(s))funkcja = true;
        }

        if(funkcja)
        {
            switch (nazwa)
            {
                case "+":
                    symbol = new Dodawanie();
                    kolejka.add(symbol);
                    break;
                case "-":
                    symbol = new Odejmowanie();
                    kolejka.add(symbol);
                    break;
                case "*":
                    symbol = new Mnozenie();
                    kolejka.add(symbol);
                    break;
                case "/":
                    symbol = new Dzielenie();
                    kolejka.add(symbol);
                    break;
                case "Min":
                    symbol = new Min();
                    kolejka.add(symbol);
                    break;
                case "Max":
                    symbol = new Max();
                    kolejka.add(symbol);
                    break;
                case "Log":
                    symbol = new Log();
                    kolejka.add(symbol);
                    break;
                case "Pow":
                    symbol = new Pow();
                    kolejka.add(symbol);
                    break;
                case "Abs":
                    symbol = new Abs();
                    kolejka.add(symbol);
                    break;
                case "Sgn":
                    symbol = new Sgn();
                    kolejka.add(symbol);
                    break;
                case "Floor":
                    symbol = new Floor();
                    kolejka.add(symbol);
                    break;
                case "Ceil":
                    symbol = new Ceil();
                    kolejka.add(symbol);
                    break;
                case "Frac":
                    symbol = new Frac();
                    kolejka.add(symbol);
                    break;
                case "Sin":
                    symbol = new Sin();
                    kolejka.add(symbol);
                    break;
                case "Cos":
                    symbol = new Cos();
                    kolejka.add(symbol);
                    break;
                case "Atan":
                    symbol = new Atan();
                    kolejka.add(symbol);
                    break;
                case "Acot":
                    symbol = new Acot();
                    kolejka.add(symbol);
                    break;
                case "Ln":
                    symbol = new Ln();
                    kolejka.add(symbol);
                    break;
                case "Exp":
                    symbol = new Exp();
                    kolejka.add(symbol);
                    break;
                case "E":
                    symbol = new E();
                    kolejka.add(symbol);
                    break;
                case "Pi":
                    symbol = new Pi();
                    kolejka.add(symbol);
                    break;
                case "Phi":
                    symbol = new Phi();
                    kolejka.add(symbol);
                    break;
            }
            return;
        }

        try
        {
            pomocnicze = Double.parseDouble(nazwa);
            symbol = new Liczba(Double.parseDouble(nazwa));
            kolejka.add(symbol);
            return;

        }
        catch(Exception e)
        {
            matcher = wzorNaZmienna.matcher(nazwa);
            if(matcher.find() && matcher.start() == 0 && matcher.end() == nazwa.length())
            {
                try
                {
                    symbol = new Zmienna(nazwa, zbiór.zwroc(nazwa));
                    kolejka.add(symbol);
                    return;
                }
                catch (WyjątekONP ex)
                {
                    throw new ONP_NieznanySymbol();
                }
            }
        }

    }

    /**
     * @return pierwszy element w kolejce i ściąga go z kolejki.
     * @throws ONP_PustyStos jeśli kolejka jest pusta, metoda zwraca wyjątek
     */
    public Symbol zwroc()throws ONP_PustyStos
    {
        if(kolejka.isEmpty())throw new ONP_PustyStos();
        Symbol s = kolejka.getFirst();
        kolejka.removeFirst();
        return  s;
    }

    /**
     * @return stan kolejki
     */
    public boolean isEmpty()
    {
        return kolejka.isEmpty();
    }
}
