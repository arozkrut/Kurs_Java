import konwerter.Konwerter;

/**
 * @author Aleksandra Rozkrut
 */



public class LiczbySłownie
{
    public static void main(String[] args)
    {
        Konwerter konwerter = new Konwerter();

        for(int i = 0; i < args.length; ++i)
        {
            try
            {
                int x = new Integer(args[i]);

                System.out.println(konwerter.konwertuj(x));
            }
            catch(NumberFormatException e)
            {
                System.err.println("Podany napis nie jest liczbą z zakresu typu int");
            }
        }

    }
}
