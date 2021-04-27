import java.io.*;
import java.util.ArrayList;

public class Engine implements Serializable
{
    private static final int N = 20, M = 20;
    private int[][] tablicaRozmieszczeń;
    private Plansza plansza;
    private static final int mikołaj = 0, prezent = 13, puste = -1;
    private Współrzędna współrzędnaMikołaja;
    private Dziecko[] dzieckos;
    private boolean mikołajZostawiłPrezent;
    private boolean nowaGra;

    public Engine(Plansza plansza)
    {
        this.plansza = plansza;
        tablicaRozmieszczeń = new int[N][M];
        dzieckos = new Dziecko[12];
        for(int i = 0; i < N; ++i)for(int j = 0; j < M; ++j)tablicaRozmieszczeń[i][j] = puste;
        mikołajZostawiłPrezent = false;
        nowaGra = false;

        początkoweRozmieszczenie();

        System.err.println("mikołaj: " + współrzędnaMikołaja.wiersz + " " + współrzędnaMikołaja.kolumna);
        for(int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                System.err.print(tablicaRozmieszczeń[i][j] + " ");
            }
            System.err.print("\n");
        }
        System.err.print("\n");

        for(int i = 0; i < 12; ++i)dzieckos[i].zaczynamy();
    }

    public synchronized void nowaGra()
    {
        if(nowaGra)return;
        synchronized (this)
        {
            nowaGra = true;
            plansza.timer.stop();
            for (int i = 0; i < 12; ++i) dzieckos[i].kończymy();
            //poczekaj();
            for (int i = 0; i < N; ++i) for (int j = 0; j < M; ++j) tablicaRozmieszczeń[i][j] = puste;

            mikołajZostawiłPrezent = false;
            początkoweRozmieszczenie();

            nowaGra = false;
            for (int i = 0; i < 12; ++i) dzieckos[i].zaczynamy();
            plansza.timer.start();
        }
    }

    private void początkoweRozmieszczenie()
    {
        synchronized (this)
        {
            //mokołaj

            współrzędnaMikołaja = wylosujPozycję();
            tablicaRozmieszczeń[współrzędnaMikołaja.wiersz][współrzędnaMikołaja.kolumna] = mikołaj;

            //dzieciaki

            Współrzędna współrzędna;
            for (int i = 1; i < 13; ++i) {
                współrzędna = wylosujPozycję();
                tablicaRozmieszczeń[współrzędna.wiersz][współrzędna.kolumna] = i;

                dzieckos[i - 1] = new Dziecko(współrzędna, this);
            }
        }
    }

    private Współrzędna wylosujPozycję()
    {
        int wiersz, kolumna;
        do
        {
            wiersz = (int)(Math.random() * N);
            kolumna = (int)(Math.random() * M);
        }while (tablicaRozmieszczeń[wiersz][kolumna] != -1 && kołoMikołaja(new Współrzędna(wiersz, kolumna)));
        return new Współrzędna(wiersz, kolumna);
    }

    public boolean kołoMikołaja(Współrzędna współrzędna)
    {
        Współrzędna nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);

        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, 1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == mikołaj)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, -1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == mikołaj)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, 1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == mikołaj)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, -1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == mikołaj)return true;

        return false;
    }

    public boolean kołoDziecka(Współrzędna współrzędna)
    {
        Współrzędna nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);

        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, 1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] > 0 &&
                tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] < 13 &&
                plansza.stanDzieci[tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] - 1] == 0)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, -1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] > 0 &&
                tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] < 13 &&
                plansza.stanDzieci[tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] - 1] == 0)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, 1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] > 0 &&
                tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] < 13 &&
                plansza.stanDzieci[tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] - 1] == 0)return true;

        nowaWspółrzędna = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, -1);
        if(tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] > 0 &&
                tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] < 13 &&
                plansza.stanDzieci[tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] - 1] == 0)return true;

        return false;
    }

    public Współrzędna sąsiaduje(Współrzędna współrzędnaSzukającego, int czegoSzukamy)throws WyjątekGry
    {
        if(nowaGra)return współrzędnaSzukającego;
        Współrzędna sprawdzana = new Współrzędna(współrzędnaSzukającego.wiersz, współrzędnaSzukającego.kolumna);

        synchronized (this)
        {
            sprawdzana.wiersz -= 1;
            if (sprawdzana.wiersz < 0) sprawdzana.wiersz = N - 1;
            if (tablicaRozmieszczeń[sprawdzana.wiersz][sprawdzana.kolumna] == czegoSzukamy) {
                przesuń(współrzędnaSzukającego, sprawdzana);
                return sprawdzana;
            }

            sprawdzana = new Współrzędna(współrzędnaSzukającego.wiersz, współrzędnaSzukającego.kolumna);
            sprawdzana.kolumna += 1;
            if (sprawdzana.kolumna > M - 1) sprawdzana.kolumna = 0;
            if (tablicaRozmieszczeń[sprawdzana.wiersz][sprawdzana.kolumna] == czegoSzukamy) {
                przesuń(współrzędnaSzukającego, sprawdzana);
                return sprawdzana;
            }

            sprawdzana = new Współrzędna(współrzędnaSzukającego.wiersz, współrzędnaSzukającego.kolumna);
            sprawdzana.wiersz += 1;
            if (sprawdzana.wiersz > N - 1) sprawdzana.wiersz = 0;
            if (tablicaRozmieszczeń[sprawdzana.wiersz][sprawdzana.kolumna] == czegoSzukamy) {
                przesuń(współrzędnaSzukającego, sprawdzana);
                return sprawdzana;
            }

            sprawdzana = new Współrzędna(współrzędnaSzukającego.wiersz, współrzędnaSzukającego.kolumna);

            sprawdzana.kolumna -= 1;
            if (sprawdzana.kolumna < 0) sprawdzana.kolumna = M - 1;
            if (tablicaRozmieszczeń[sprawdzana.wiersz][sprawdzana.kolumna] == czegoSzukamy) {
                przesuń(współrzędnaSzukającego, sprawdzana);
                return sprawdzana;
            }
        }

        throw new WyjątekGry();
    }

    private void przesuń(Współrzędna skąd, Współrzędna dokąd)
    {
        tablicaRozmieszczeń[dokąd.wiersz][dokąd.kolumna] = tablicaRozmieszczeń[skąd.wiersz][skąd.kolumna];
        tablicaRozmieszczeń[skąd.wiersz][skąd.kolumna] = puste;
    }
    
    private Współrzędna zmieńWspółrzędna(Współrzędna współrzędna, int kierunek, int ile)
    {
        Współrzędna nowa = new Współrzędna(współrzędna.wiersz, współrzędna.kolumna);
        if(kierunek == 0)
        {
            nowa.wiersz += ile;
            if(nowa.wiersz < 0)nowa.wiersz += N;
            else if(nowa.wiersz > N - 1)nowa.wiersz -= N;
        }
        else
        {
            nowa.kolumna += ile;
            if(nowa.kolumna < 0)nowa.kolumna += M;
            else if(nowa.kolumna > M - 1)nowa.kolumna -= M;
        }
        
        return nowa;
    }
    
    public void ruchMikołaja(int kierunek)
    {
        if(nowaGra)return;
        Współrzędna nowaWspółrzędna = new Współrzędna(współrzędnaMikołaja.wiersz, współrzędnaMikołaja.kolumna);
        switch (kierunek)
        {
            case 0:
                nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, -1);
                break;
            case 1:
                nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, 1);
                break;
            case 2:
                nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 0, 1);
                break;
            case 3:
                nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, 1, -1);
                break;
        }

        synchronized (this)
        {
            if (tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == puste)
            {
                przesuń(współrzędnaMikołaja, nowaWspółrzędna);
                if(mikołajZostawiłPrezent)
                {
                    tablicaRozmieszczeń[współrzędnaMikołaja.wiersz][współrzędnaMikołaja.kolumna] = prezent;
                    mikołajZostawiłPrezent = false;
                }
                współrzędnaMikołaja = nowaWspółrzędna;
            }

            if (kołoDziecka(współrzędnaMikołaja)) nowaGra();
        }
    }

    public void zostawPrezent()
    {
        if(nowaGra)return;
        synchronized (this) {
            mikołajZostawiłPrezent = true;
        }
    }

    public synchronized Współrzędna ruchDziecka(Współrzędna współrzędnaDziecka)
    {
        if(nowaGra)return współrzędnaDziecka;
        Współrzędna nowaWspółrzędna = new Współrzędna(współrzędnaDziecka.wiersz, współrzędnaDziecka.kolumna);
        int[] różnice = new int[2];
        int  kierunek;
        int ile;
        Współrzędna[] współrzędnas = new Współrzędna[9];
        synchronized (this)
        {
            współrzędnas[5] = new Współrzędna(współrzędnaMikołaja.wiersz - N, współrzędnaMikołaja.kolumna - M);
            współrzędnas[1] = new Współrzędna(współrzędnaMikołaja.wiersz - N, współrzędnaMikołaja.kolumna);
            współrzędnas[6] = new Współrzędna(współrzędnaMikołaja.wiersz - N, współrzędnaMikołaja.kolumna + M);
            współrzędnas[2] = new Współrzędna(współrzędnaMikołaja.wiersz, współrzędnaMikołaja.kolumna - M);
            współrzędnas[0] = new Współrzędna(współrzędnaMikołaja.wiersz, współrzędnaMikołaja.kolumna);
            współrzędnas[3] = new Współrzędna(współrzędnaMikołaja.wiersz, współrzędnaMikołaja.kolumna + M);
            współrzędnas[7] = new Współrzędna(współrzędnaMikołaja.wiersz + N, współrzędnaMikołaja.kolumna - M);
            współrzędnas[4] = new Współrzędna(współrzędnaMikołaja.wiersz + N, współrzędnaMikołaja.kolumna);
            współrzędnas[8] = new Współrzędna(współrzędnaMikołaja.wiersz + N, współrzędnaMikołaja.kolumna + M);

            for (int i = 0; i < 9; ++i) {
                if (Math.abs(współrzędnaDziecka.wiersz - współrzędnas[i].wiersz) < 5 &&
                        Math.abs(współrzędnaDziecka.kolumna - współrzędnas[i].kolumna) < 5) {
                    różnice[0] = współrzędnas[i].wiersz - współrzędnaDziecka.wiersz;
                    różnice[1] = współrzędnas[i].kolumna - współrzędnaDziecka.kolumna;

                    kierunek = (int) (Math.random() * 2);
                    ile = (różnice[kierunek] > 0) ? 1 : -1;

                    if (różnice[kierunek] != 0) {
                        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, kierunek, ile);
                        if (tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == puste) {
                            przesuń(współrzędnaDziecka, nowaWspółrzędna);
                            return nowaWspółrzędna;
                        }
                    }

                    nowaWspółrzędna = new Współrzędna(współrzędnaDziecka.wiersz, współrzędnaDziecka.kolumna);
                    kierunek = 1 - kierunek;
                    ile = (różnice[kierunek] > 0) ? 1 : -1;

                    if (różnice[kierunek] != 0) {
                        nowaWspółrzędna = zmieńWspółrzędna(nowaWspółrzędna, kierunek, ile);
                        if (tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == puste) {
                            przesuń(współrzędnaDziecka, nowaWspółrzędna);
                        }
                    }

                    return nowaWspółrzędna;
                }
            }
        }

        ArrayList<Współrzędna> list = new ArrayList<Współrzędna>();
        list.add(zmieńWspółrzędna(współrzędnaDziecka, 0, -1));
        list.add(zmieńWspółrzędna(współrzędnaDziecka, 0, 1));
        list.add(zmieńWspółrzędna(współrzędnaDziecka, 1, -1));
        list.add(zmieńWspółrzędna(współrzędnaDziecka, 1, 1));
        int liczbaMożliwości = 4;
        int opcja;
        synchronized (this)
        {
            for (int i = 0; i < 4; ++i) {
                opcja = (int) (Math.random() * liczbaMożliwości);
                nowaWspółrzędna = list.get(opcja);
                if (tablicaRozmieszczeń[nowaWspółrzędna.wiersz][nowaWspółrzędna.kolumna] == puste) {
                    przesuń(współrzędnaDziecka, nowaWspółrzędna);
                    return nowaWspółrzędna;
                } else {
                    list.remove(opcja);
                    liczbaMożliwości -= 1;
                }
            }
        }

        return współrzędnaDziecka;
    }

    public void zmieńStanDziecka(Współrzędna współrzędna, int stan)
    {
        if(nowaGra)return;
        synchronized (this)
        {
            plansza.zmieńStanDziecka(tablicaRozmieszczeń[współrzędna.wiersz][współrzędna.kolumna] - 1, stan);
        }
    }

    public int[][] getTablicaRozmieszczeń()
    {
        //synchronized (this)
        //{
            return tablicaRozmieszczeń;
        //}
    }

    public void poczekaj() {
        for(int i = 0; i < 12; ++i)
        {
            try
            {
                dzieckos[i].getWątek().join();
            }
            catch (InterruptedException err)
            {
                System.err.println(err.getMessage());
            }
        }
    }

    public void zakończGrę()
    {
        nowaGra = true;
        for(int i = 0; i < 12; ++i)dzieckos[i].kończymy();
        poczekaj();
        nowaGra = false;
    }

    public void wznówGrę()
    {
        for(int i = 0; i < 12; ++i)dzieckos[i].zaczynamy();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        /*private static final int N = 20, M = 20;
    private int[][] tablicaRozmieszczeń;
    private Plansza plansza;
    private static final int mikołaj = 0, prezent = 13, puste = -1;
    private Współrzędna współrzędnaMikołaja;
    private Dziecko[] dzieckos;
    private boolean mikołajZostawiłPrezent;
    private boolean nowaGra;*/
        for(int i = 0; i < N; ++i)
        {
            for(int j = 0; j < M; ++j)out.writeInt(tablicaRozmieszczeń[i][j]);
        }
        out.writeObject(współrzędnaMikołaja);
        for(int i = 0; i < 12; ++i)out.writeObject(dzieckos[i]);
        out.writeBoolean(mikołajZostawiłPrezent);
        out.writeBoolean(nowaGra);
    }


    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        for(int i = 0; i < N; ++i)
        {
            for(int j = 0; j < M; ++j)tablicaRozmieszczeń[i][j] = in.readInt();
        }
        współrzędnaMikołaja = (Współrzędna) in.readObject();
        for(int i = 0; i < 12; ++i)dzieckos[i] = (Dziecko)in.readObject();
        mikołajZostawiłPrezent = in.readBoolean();
        nowaGra = in.readBoolean();
    }

    private void readObjectNoData() throws ObjectStreamException
    {
        throw new InvalidObjectException("Stream data required");
    }
}
