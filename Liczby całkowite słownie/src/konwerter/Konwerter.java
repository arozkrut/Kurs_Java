package konwerter;

public class Konwerter
{
    private static final String[] jedności = {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
    private static final String[] nastki = {null, "jede", null, null, "czter", "pięt", "szes", null, null, "dziewięt"};

    public String konwertuj(int liczba)
    {
        String słownie = new String();
        boolean ujemna = false;
        int[] cyfry = new int[3];
        int trójka = -1;

        if(liczba == 0)return "zero";

        if(liczba == -2147483648)return "minus dwa miliardy sto czterdzieści siedem milionów czterysta osiemdziesiąt " +
                "trzy tysiące sześćset czterdzieści osiem";

        if(liczba < 0)
        {
            ujemna = true;
            liczba = -liczba;
        }

        while(liczba > 0)
        {
            cyfry[2] = liczba % 10;
            liczba /= 10;
            cyfry[1] = liczba % 10;
            liczba /= 10;
            cyfry[0] = liczba % 10;
            liczba /= 10;

            ++trójka;

            // wyjatkowy przypadek: tysiac, milion, miliard
            if(cyfry[0] == 0 && cyfry[1] == 0 && cyfry[2] == 1 && trójka != 0)
            {
                if(trójka == 1)słownie = "tysiąc " + słownie;
                else if(trójka == 2)słownie = "milion " + słownie;
                else słownie = "miliard " + słownie;
                continue;
            }

            //końcówka
            if(trójka != 0 && (cyfry[0] != 0 || cyfry[1] != 0 || cyfry[2] != 0))
            {
                //tysiace
                if(trójka == 1)
                {
                    if(cyfry[2] > 1 && cyfry[2] < 5 && cyfry[1]!=1)słownie = "tysiące " + słownie;
                    else słownie = "tysięcy " + słownie;
                }
                //miliony
                else if(trójka == 2)
                {
                    if(cyfry[2] > 1 && cyfry[2] < 5 && cyfry[1]!=1)słownie = "miliony " + słownie;
                    else słownie = "milionów " + słownie;
                }
                //miliardy
                else słownie = "miliardy " + słownie;
            }

            // 10
            if( cyfry[1] == 1 && cyfry[2] == 0)słownie = "dziesięć " + słownie;
            // kilkanaście
            else if(cyfry[1] == 1)
            {
                słownie = "naście " + słownie;
                if(cyfry[2] == 1 || cyfry[2] == 4 || cyfry[2] == 5 || cyfry[2] == 6 || cyfry[2] == 9)słownie = nastki[cyfry[2]] + słownie;
                else słownie = jedności[cyfry[2]] + słownie;
            }
            else
            {
                słownie = jedności[cyfry[2]] + " " + słownie;

                if(cyfry[1] != 0)
                {
                    if(cyfry[1] == 2)słownie = "dwadzieścia" + " " + słownie;
                    else if(cyfry[1] == 3)słownie = "trzydzieści" + " " + słownie;
                    else if(cyfry[1] == 4)słownie = "czterdzieści" + " " + słownie;
                    else słownie = jedności[cyfry[1]] + "dziesiąt" + " " + słownie;
                }
            }

            //setki
            if(cyfry[0] != 0)
            {
                if(cyfry[0] == 1)słownie = "sto " + słownie;
                else if(cyfry[0] == 2)słownie = "dwieście " + słownie;
                else if(cyfry[0] == 3 || cyfry[0] == 4)słownie = jedności[cyfry[0]] + "sta " + słownie;
                else słownie = jedności[cyfry[0]] + "set " + słownie;
            }

        }

        if(ujemna)słownie = "minus " + słownie;

        return słownie;
    }
}
