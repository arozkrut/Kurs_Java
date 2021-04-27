import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PrzetwarzaczKolekcji
{

    public static void main(String[] args)
    {
        try
        {
            PrzetwarzaczLiczb przetwarzacz = new PrzetwarzaczLiczb("dane1.txt");
            przetwarzacz.wypiszPosortowaneMalejąco();
            przetwarzacz.wypiszLiczbyPierwsze();
            przetwarzacz.wypiszSumęLiczbMniejszychNiżTysiąc();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

        try
        {
            PrzetwarzaczTrójkątów przetwarzaczTrójkątów = new PrzetwarzaczTrójkątów("dane2.txt");
            przetwarzaczTrójkątów.wypiszPosortowaneObwody();
            przetwarzaczTrójkątów.wypiszTrójkątyProstokątne();
            przetwarzaczTrójkątów.wypiszMaksimumiMinimum();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
