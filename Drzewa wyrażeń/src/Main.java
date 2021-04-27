import obliczenia.*;

public class Main
{
    public static void main(String args[])
    {
        Stala pi = new Stala("pi");
        System.out.println(pi + "=" + pi.oblicz());
        Stala e = new Stala("e");
        System.out.println(e + "=" + e.oblicz());
        Stala fi = new Stala("fi");
        System.out.println(fi + "=" + fi.oblicz());

        Zmienna x = new Zmienna("x");
        System.out.println(x + "=" + x.oblicz());

        Wyrazenie w = new Dodawanie(
                new Liczba(7),
                new Mnozenie(
                        new Zmienna("x"),
                        new Liczba(5)
                )
        );
        System.out.println(w + "=" + w.oblicz());

        w = new Dodawanie(new Liczba(3), new Liczba(5));
        System.out.println(w + "=" + w.oblicz());

        w = new Dzielenie(
                new Odejmowanie(
                        new Mnozenie(
                                new Liczba(3),
                                new Liczba(11)),
                        new Liczba(1)),
                new Dodawanie(
                        new Liczba(7),
                        new Liczba(5)
                ));
        System.out.println(w + "=" + w.oblicz());

        Wyrazenie w1 = new Arctan(
                new Dzielenie(
                        new Mnozenie(
                                new Dodawanie(
                                        new Zmienna("x"),
                                        new Liczba(13)
                                ),
                                new Zmienna("x")
                        ),
                        new Liczba(2)
                )
        );
        System.out.println(w1 + "=" + w1.oblicz());

        /*3+5
        2+x*7
        (3*11-1)/(7+5)
        arctan(((x+13)*x)/2)
        pow(2,5)+x*log(2,y)*/

        Wyrazenie w2 = new Dodawanie(
                new Potegowanie(
                        new Liczba(2),
                        new Liczba(5)
                ),
                new Mnozenie(
                        new Zmienna("x"),
                        new Logarytmowanie(
                                new Liczba(2),
                                new Zmienna("y")
                        )
                )
        );
        System.out.println(w2 + "=" + w2.oblicz());

        System.out.println(Wyrazenie.suma(w1, w2, w));
        System.out.println(Wyrazenie.iloczyn(w1, w2, w));

        System.out.println(new Minimum(new Liczba(6.0), new Liczba(434.4)).oblicz());
        System.out.println(new Przeciwienstwo(new WartBezwzgl(new Liczba(-15.0))));
        System.out.println(new Odwrotnosc(new Dodawanie(new Liczba(1.0), new Liczba(7.0))).oblicz());
    }
}
