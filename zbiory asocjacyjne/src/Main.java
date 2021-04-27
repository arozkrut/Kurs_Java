import struktury.Para;
import struktury.ZbiorNaTablicy;

public class Main
{
    public static void main(String args[])
    {
        try
        {
            Para para = new Para("nata", 1.0);
            System.out.println(para);

            ZbiorNaTablicy zbiorNaTablicy = new ZbiorNaTablicy(6);
            zbiorNaTablicy.wstaw(new Para("na", 0.9));
            zbiorNaTablicy.czysc();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
