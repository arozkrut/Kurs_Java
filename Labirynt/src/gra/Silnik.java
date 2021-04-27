package gra;

import java.util.Arrays;
import java.util.BitSet;

public class Silnik
{
    private Plansza plansza;
    private int rozmiar;
    private Komnata[][] labirynt;
    private BitSet odwiedzone;
    private int początek;
    private int koniec;
    private int najdłuższaDroga;
    private int eksploatorX, eksploatorY;

    private static final short góra = 0, prawo = 1, dół = 2, lewo = 3;

    public Silnik()
    {
        rozmiar = 23;
        początek = rozmiar * (rozmiar - 1);
        plansza = new Plansza(rozmiar, this);
        nowaGra();//TODO:więcej opcji?
    }

    public void setRozmiar(int rozmiar)
    {
        this.rozmiar = rozmiar;
        początek = rozmiar * (rozmiar - 1);
        koniec = rozmiar - 1;
    }

    boolean ruch(int kierunek)
    {
        switch (kierunek)
        {
            case góra:
                if(labirynt[eksploatorY][eksploatorX].wGórę)
                {
                    eksploatorY -=1;
                    return true;
                }
                break;
            case prawo:
                if(labirynt[eksploatorY][eksploatorX].wPrawo)
                {
                    eksploatorX +=1;
                    return true;
                }
                break;
            case dół:
                if(labirynt[eksploatorY][eksploatorX].wDół)
                {
                    eksploatorY +=1;
                    return true;
                }
                break;
            case lewo:
                if(labirynt[eksploatorY][eksploatorX].wLewo)
                {
                    eksploatorX -=1;
                    return true;
                }
                break;
        }
        return false;
    }

    void nowaGra()
    {
        labirynt = new Komnata[rozmiar][rozmiar];
        odwiedzone = new BitSet(rozmiar * rozmiar);
        najdłuższaDroga = 0;
        eksploatorX = 0;
        eksploatorY = rozmiar - 1;
        for(int i = 0; i<rozmiar; ++i)
        {
            for(int j = 0; j < rozmiar; ++j)
            {
                labirynt[i][j] = new Komnata();
            }
        }

        wygenerujPlanszę(początek, 0);
        plansza.nowaGra(labirynt, koniec / rozmiar, koniec % rozmiar);
    }

    private void wygenerujPlanszę(int komnata, int potomek)
    {
        short[] kierunki = new short[4];
        boolean[] wypełnione = new boolean[4];
        int pozycja;

        odwiedzone.set(komnata);
        if(potomek > najdłuższaDroga)
        {
            najdłuższaDroga = potomek;
            koniec = komnata;
        }

        for(short k = 0; k < 4; ++k)
        {
            pozycja = (int)(Math.random() * (4 - k));
            for(int i = 0; i < 4; ++i)
            {
                if(wypełnione[i])
                {
                    ++pozycja;
                    continue;
                }
                if(i == pozycja)
                {
                    kierunki[i] = k;
                    wypełnione[i] = true;
                    break;
                }
            }
        }

        for(int k = 0; k < 4; ++k)
        {
            switch (kierunki[k])
            {
                case góra:
                    if(komnata < rozmiar)break;
                    if(!odwiedzone.get(komnata - rozmiar))
                    {
                        //System.out.println(komnata + "->" + (komnata - rozmiar));
                        labirynt[komnata / rozmiar][komnata % rozmiar].wGórę = true;
                        labirynt[(komnata - rozmiar) / rozmiar][(komnata - rozmiar) % rozmiar].wDół = true;
                        wygenerujPlanszę(komnata - rozmiar, potomek + 1);
                    }
                    break;
                case prawo:
                    if((komnata + 1)%rozmiar == 0)break;
                    if(!odwiedzone.get(komnata + 1))
                    {
                        //System.out.println(komnata + "->" + (komnata +1));
                        labirynt[komnata / rozmiar][komnata % rozmiar].wPrawo = true;
                        labirynt[(komnata + 1) / rozmiar][(komnata + 1) % rozmiar].wLewo = true;
                        wygenerujPlanszę(komnata + 1, potomek + 1);
                    }
                    break;
                case dół:
                    if(komnata > rozmiar * (rozmiar - 1) - 1)break;
                    if(!odwiedzone.get(komnata + rozmiar))
                    {
                        //System.out.println(komnata + "->" + (komnata + rozmiar));
                        labirynt[komnata / rozmiar][komnata % rozmiar].wDół = true;
                        labirynt[(komnata + rozmiar) / rozmiar][(komnata + rozmiar) % rozmiar].wGórę = true;
                        wygenerujPlanszę(komnata + rozmiar, potomek + 1);
                    }
                    break;
                case lewo:
                    if(komnata % rozmiar == 0)break;
                    if(!odwiedzone.get(komnata - 1))
                    {
                        //System.out.println(komnata + "->" + (komnata - 1));
                        labirynt[komnata / rozmiar][komnata % rozmiar].wLewo = true;
                        labirynt[(komnata - 1) / rozmiar][(komnata - 1) % rozmiar].wPrawo = true;
                        wygenerujPlanszę(komnata - 1, potomek + 1);
                    }
                    break;
            }
        }
    }

}
