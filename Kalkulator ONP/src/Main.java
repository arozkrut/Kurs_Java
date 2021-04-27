import narzedzia.*;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)throws WyjątekONP, AssertionError
    {
        boolean exit = false;
        String polecenie = "";
        String zmienne;
        Scanner scanner = new Scanner(System.in);
        Pattern nazwaZmiennej = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        Matcher matcher;
        Zbiór zbiór = new Zbiór();
        double wynik;
        Wyrazenie wyrazenie;
        ArrayDeque<String> nazwyZmiennych;


        while(!exit)
        {
            try
            {
                polecenie = scanner.nextLine();

                assert ((polecenie.length() > 5 && polecenie.substring(0, 5).equals("calc ")) || (polecenie.length() > 4 && polecenie.substring(0, 5).equals("clear")) || (polecenie.length() > 3 && polecenie.substring(0, 4).equals("exit"))): "Nieporawidłowe polecenie";

                if(polecenie.substring(0, 4).equals("calc"))
                {
                    nazwyZmiennych = new ArrayDeque<>();
                    polecenie = polecenie.substring(5);

                    if(polecenie.matches(".+=.*"))
                    {
                        int pierwszePrzypisanie = -1;
                        int pierwszaZmienna = -1;
                        for(int i = polecenie.length() - 1; i>=3; --i)
                        {
                            if(polecenie.charAt(i) == '=')pierwszePrzypisanie = i;
                        }

                        if(polecenie.charAt(pierwszePrzypisanie - 1) == ' ')pierwszePrzypisanie -= 1;

                        for(int i = pierwszePrzypisanie - 1; i >= 1; --i)
                        {
                            if(polecenie.charAt(i) == ' ')
                            {
                                pierwszaZmienna = i + 1;
                                break;
                            }
                        }

                        if(pierwszaZmienna == -1)throw new ONP_BłędneWyrażenie();
                        zmienne = polecenie.substring(pierwszaZmienna);
                        polecenie = polecenie.substring(0, pierwszaZmienna -1);

                        matcher = nazwaZmiennej.matcher(zmienne);
                        while (matcher.find())
                        {
                            if(zmienne.length() < matcher.end() || !(zmienne.charAt(matcher.end()) == '=' || (zmienne.length() < matcher.end()+1 && zmienne.charAt(matcher.end() + 1) == '=')))throw new ONP_BłędneWyrażenie();
                            nazwyZmiennych.add(zmienne.substring(matcher.start(), matcher.end()));
                        }
                    }

                    wyrazenie = new Wyrazenie(polecenie, new Lista(), zbiór);
                    wynik = wyrazenie.obliczONP();

                    for(String s: nazwyZmiennych)
                    {
                        zbiór.dodaj(s, wynik);
                    }

                    System.out.println(wynik);
                }
                else if(polecenie.substring(0, 4).equals("exit"))
                {
                    exit = true;
                    scanner.close();
                }
                else if(polecenie.substring(0, 5).equals("clear"))
                {
                    if(!polecenie.equals("clear"))
                    {
                        polecenie = polecenie.substring(6);
                        matcher = nazwaZmiennej.matcher(polecenie);

                        while(true)
                        {
                            if(!matcher.find())break;
                            zbiór.clear(polecenie.substring(matcher.start(), matcher.end()));
                        }

                    }
                    else
                    {
                        zbiór.clearAll();
                    }
                }

            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
        }

    }
}
