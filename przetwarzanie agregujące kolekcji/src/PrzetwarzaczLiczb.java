import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PrzetwarzaczLiczb
{
    private String nazwaPilku;
    private ArrayList<Integer> kolekcja;


    public PrzetwarzaczLiczb(String nazwaPilku)throws Exception
    {
        this.nazwaPilku = nazwaPilku;
        kolekcja = new ArrayList<>();


        wczytajPlik();
    }

    /*
     - zakresu od 1 do 10^9
     - w jednej linii może być zapisana co najwyżej jedna liczba;
     - liczba jest zapisana w systemie dziesiętnym bez zer wiodących;
     - linia może być pusta;
     - białe znaki (space, tabulacje, itp) przed liczbą, za liczbą oraz w linii pustej należy zignorować,
     - na końcu linii może się znajdować komentarz rozpoczynający się od sekwencji “//”
       (linia pusta także może zawierać komentarz). */
    private void wczytajPlik()throws Exception
    {
        String liczba, komentarz, pustyWiersz;
        Pattern patternLiczby, patternKomentarza;
        Matcher matcher;

        liczba = "\\s*\\d+\\s*";
        komentarz = ".*//.*";
        pustyWiersz = "\\s*";
        patternLiczby = Pattern.compile("\\d+");
        patternKomentarza = Pattern.compile("//");

        int linia = 0;
        int nowaLiczba;

        int min = 1;
        int maks = 1000000000;

        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPilku))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {

                linia++;

                // usuwamy komentarz
                if(ln.matches(komentarz))
                {
                    matcher = patternKomentarza.matcher(ln);
                    matcher.find();
                    if(matcher.start() == 0)ln = " ";
                    else ln = ln.substring(0, matcher.start() - 1);
                }

                // pusty wiersz
                if(ln.matches(pustyWiersz)) continue;

                //jedna liczba
                if(ln.matches(liczba))
                {
                    matcher = patternLiczby.matcher(ln);
                    matcher.find();
                    ln = ln.substring(matcher.start(), matcher.end());

                    // zera wiodące
                    if(ln.length() > 1 && ln.charAt(0) == '0')throw new NiepoprawneWejścieException(linia, 1);

                    // odpowiedni zakres
                    try
                    {
                        nowaLiczba = Integer.valueOf(ln);
                        if(nowaLiczba < min || nowaLiczba > maks)throw new NiepoprawneWejścieException(linia, 1);
                        kolekcja.add(nowaLiczba);

                        continue;
                    }
                    catch (Exception ex)
                    {
                        throw new NiepoprawneWejścieException(linia, 1);
                    }

                }
                throw new NiepoprawneWejścieException(linia, 1);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public void wypiszPosortowaneMalejąco()
    {
        System.out.println("Posortowane liczby:");
        kolekcja
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(i -> System.out.print(i + " "));

        System.out.print("\n");
    }

    public void wypiszLiczbyPierwsze()
    {
        System.out.println("Liczby pierwsze:");
        kolekcja
                .stream()
                .filter(i -> i > 1 && IntStream.rangeClosed(2, (int) Math.sqrt((double) i)).noneMatch(n -> i%n == 0))
                .forEach(i -> System.out.print(i + " "));
        System.out.print("\n");
    }

    public void wypiszSumęLiczbMniejszychNiżTysiąc()
    {
        System.out.println("Suma liczb mniejszych niż 1000:");
        System.out.println(kolekcja
                .stream()

                .filter(i -> i < 1000)
                .reduce(
                        0,
                        (a, b) -> a + b));
    }
}
