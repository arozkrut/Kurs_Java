package narzedzia;

import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa oblicza wyrażenie ONP
 */

public class Wyrazenie
{
    private Kolejka kolejka; // kolejka symboli wyrażenia ONP (elementy typu Symbol)
    private Stos stos; // stos z wynikami pośrednimi obliczeń (elementy typu Double)
    private Zbiór zmienne; // lista zmiennych czyli pary klucz-wartość (String-Double)
    private Pattern wzor;
    private Matcher matcher;
    private static Logger logger = Logger.getLogger(Wyrazenie.class.getName());

    /**
     * Konstruktor przerabia całe wyrażenia na kolejkę symboli
     * @param onp wyrażenie onp
     * @param zm lista
     * @param zbiór zbiór zmiennych
     */
    public Wyrazenie (String onp, Lista zm, Zbiór zbiór)throws WyjątekONP
    {
        kolejka = new Kolejka(zbiór);
        stos = new Stos();
        zmienne = zbiór;
        wzor = Pattern.compile("[\\d\\w\\*\\/\\+\\-]+");
        matcher = wzor.matcher(onp);

        if(logger.getHandlers().length == 0)
        {
            try
            {
                Handler handler = new FileHandler("calc.log", true);
                handler.setFormatter(new SimpleFormatter());
                logger.addHandler(handler);
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
        }

        try
        {
            while(matcher.find())
            {
                kolejka.dodaj(onp.substring(matcher.start(), matcher.end()));
            }
        }
        catch (WyjątekONP e)
        {
            throw e;
        }
    }

    /**
     * Metoda oblicza wartość wyrażenia ONP
     * @return wartość wyrażenia ONP
     * @throws WyjątekONP metoda wyrzuca wyjątki, które sama otrzymała
     */
    public double obliczONP()throws WyjątekONP
    {
        Symbol symbol;
        double wartość;

        try
        {
            while(!kolejka.isEmpty())
            {
                symbol = kolejka.zwroc();
                logger.log(Level.INFO, "Pobrano z kolejki symbol " + symbol.toString());

                if(symbol instanceof Liczba || symbol instanceof Zmienna)
                {
                    wartość = symbol.oblicz();
                    stos.dodaj(wartość);
                    logger.log(Level.INFO, "Dodano na stos wartość " + wartość);
                }
                else
                {
                    Funkcja f = (Funkcja) symbol;
                    while (f.brakująceArgumenty() > 0)
                    {
                        wartość = stos.zwroc();
                        f.dodajArgument(wartość);
                        logger.log(Level.INFO, "Pobrano wartość " + wartość + " ze stosu");
                    }

                    wartość = symbol.oblicz();
                    stos.dodaj(wartość);
                    logger.log(Level.INFO, "Dodano na stos wartość " + wartość);
                }
            }

            wartość = stos.zwroc();
            if(!stos.isEmpty())throw new ONP_BłędneWyrażenie();
            logger.log(Level.INFO, "Obliczono wartość wyrażenia ONP: " + wartość);
            return wartość;
        }
        catch (WyjątekONP e)
        {
            logger.log(Level.SEVERE, "Wyrzucono wyjątek: " + e.getMessage());
            throw e;
        }
    }
}
