import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PrzetwarzaczTrójkątów
{
    private String nazwaPilku;
    private LinkedList<Trojkat> kolekcja;


    public PrzetwarzaczTrójkątów(String nazwaPilku)throws Exception
    {
        this.nazwaPilku = nazwaPilku;
        kolekcja = new LinkedList<>();


        wczytajPlik();
    }

    /*
      - w jednej linii może być zapisany co najwyżej jeden trójkąt, czyli trzy liczby rzeczywiste
        oddzielone białymi znakami;
      - liczby są zapisana w systemie dziesiętnym z opcjonalną częścią ułamkową po kropce dziesiętnej;
      - linia może być pusta;
      - białe znaki (space, tabulacje, itp) na początku linii, na końcu linii oraz w linii pustej na-
        leży zignorować,
      - na końcu linii może się znajdować komentarz rozpoczynający się od sekwencji
        “//”(linia pusta także może zawierać komentarz).
     */
    private void wczytajPlik()throws Exception
    {
        String liczby, komentarz, pustyWiersz, liczba;
        Pattern patternLiczb, patternKomentarza, patternLiczby;
        Matcher matcher;

        liczby = "\\s*\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s*";
        liczba = "\\d+(\\.\\d+)?";
        komentarz = ".*//.*";
        pustyWiersz = "\\s*";
        patternKomentarza = Pattern.compile("//");
        patternLiczby = Pattern.compile(liczba);

        int linia = 0;
        Trojkat nowyTrojkat;
        Double[] długości = new Double[3];
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPilku))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {

                linia++;

                // usuwamy komentarz
                if(ln.matches(komentarz))
                {
                    System.out.println("usuwa komentarz linia: " + linia);
                    matcher = patternKomentarza.matcher(ln);
                    matcher.find();
                    if(matcher.start() == 0)ln = " ";
                    else ln = ln.substring(0, matcher.start() - 1);
                }

                // pusty wiersz
                if(ln.matches(pustyWiersz))continue;

                //jedna liczba
                if(ln.matches(liczby))
                {
                    System.out.println("weszło do liczb linia: " + linia);
                    matcher = patternLiczby.matcher(ln);
                    try
                    {
                        for (int i = 0; i < 3; ++i)
                        {
                            matcher.find();
                            długości[i] = Double.valueOf(ln.substring(matcher.start(), matcher.end()));
                        }

                        nowyTrojkat = new Trojkat(długości[0], długości[1], długości[2]);
                        kolekcja.add(nowyTrojkat);

                        continue;
                    }
                    catch (Exception ex)
                    {
                        throw  new NiepoprawneWejścieException(linia, 2);
                    }
                }

                System.out.println("wyszło z liczb: " + linia);
                throw new NiepoprawneWejścieException(linia, 2);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public void wypiszPosortowaneObwody()
    {
        System.out.println("Posortowane po obwodach:");
        kolekcja
                .stream()
                .sorted((s1, s2)->{
                    return ((Double)(s1.getA() + s1.getB() + s1.getC())).compareTo(((Double)(s2.getA() + s2.getB() + s2.getC())));
                })
                .forEach(i -> System.out.print(i + " "));

        System.out.print("\n");
    }

    public void wypiszTrójkątyProstokątne()
    {
        System.out.println("Trójkąty prostokątne:");
        kolekcja
                .stream()
                .filter(i -> (Math.abs(i.getA() * i.getA() + i.getB()*i.getB() - i.getC()*i.getC()) < 0.001 || Math.abs(i.getA() * i.getA() + i.getC()*i.getC() - i.getB()*i.getB()) < 0.001 || Math.abs(i.getC() * i.getC() + i.getB()*i.getB() - i.getA()*i.getA()) < 0.001))
                .forEach(i -> System.out.print(i + " "));
        System.out.print("\n");
    }

    public void wypiszMaksimumiMinimum()
    {
        System.out.println("Trójkąty o maksymalnym i minimalnym polu:");
        Comparator<Trojkat> komparator = (s1, s2) -> {
            return Double.compare((Math.sqrt((s1.getA() + s1.getB() + s1.getC()) *
                    (s1.getA() + s1.getB() - s1.getC()) * (s1.getA() - s1.getB() + s1.getC()) *
                    (-s1.getA() + s1.getB() + s1.getC()))/4),
                    (Math.sqrt((s2.getA() + s2.getB() + s2.getC()) *
                            (s2.getA() + s2.getB() - s2.getC()) * (s2.getA() - s2.getB() + s2.getC()) *
                            (-s2.getA() + s2.getB() + s2.getC()))/4));
        };

        System.out.println(kolekcja
                .stream()
                .max(komparator)
                .orElse(null));
        System.out.println(kolekcja
                .stream()
                .min(komparator)
                .orElse(null));
    }
}
