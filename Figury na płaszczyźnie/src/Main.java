import geometria.Prosta;
import geometria.Punkt;
import geometria.Trojkat;
import geometria.Wektor;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Punkt p = new Punkt(3.15, -6.3);
            Punkt p2 = new Punkt(1.0, 0.0);
            Punkt początek = new Punkt(0.0, 0.0);

            p.przesuń(new Wektor(2.0, -7.1));
            System.out.println(p);

            p2.obróć(początek, Math.PI);
            System.out.println(p2);

            System.out.println(Wektor.złóż(new Wektor(1.0, 1.0), new Wektor(-1.0, -1.0)));

            Wektor v = new Wektor(1.0, 1.0);
            Prosta c1 = new Prosta(0.0, 1.0, 0.0);
            Prosta c2 = new Prosta(1.0, 0.0, 0.0);
            Prosta c3 = new Prosta(-1.0, 1.0, 0.0);
            Prosta c4 = new Prosta(-1.0, 1.0, -1.0);
            System.out.println(Prosta.przesuń(c1, v));
            System.out.println(Prosta.przesuń(c2, v));
            System.out.println(Prosta.przesuń(c3, v));

            System.out.println(String.valueOf(Prosta.prosteRównoległe(c3, c4)));
            System.out.println(String.valueOf(Prosta.prosteRównoległe(c3, c1)));
            System.out.println(String.valueOf(Prosta.prosteProstopadłe(c1, c2)));
            System.out.println(String.valueOf(Prosta.prosteRównoległe(c1, c3)));

            System.out.println(Prosta.punktPrzecięcia(c1, c2));
            System.out.println(Prosta.punktPrzecięcia(c1, c4));

            Punkt p3 = new Punkt(1.0, 1.0);
            p3.odbij(c1);
            System.out.println(p3);
            p3.odbij(c3);
            System.out.println(p3);

            Trojkat t1 = new Trojkat(new Punkt(1.0, 1.0), new Punkt(1.0, 1.0), new Punkt(1.0, 1.0));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
}
