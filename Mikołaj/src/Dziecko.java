import java.io.*;

public class Dziecko implements Runnable, Serializable
{
    private Współrzędna współrzędnaDziecka;
    private Thread wątek;
    private Engine engine;
    public boolean asleep;
    private long czasSnu;
    private long czasDoZmęczenia;
    private static final long maxCzas = 7000;
    private static final long minCzas = 3000, czasZabawy = 1000;
    private static final int mikołaj = 0, prezent = 13;
    private static final int biega = 0, śpi = 1, maPrezent = 2;
    private boolean stop;

    public Dziecko(Współrzędna współrzędna, Engine engine)
    {
        współrzędnaDziecka = współrzędna;
        stop = false;
        this.engine = engine;
        wątek = new Thread(this);
        asleep = ((int)(Math.random() * 2) == 1);
        engine.zmieńStanDziecka(współrzędnaDziecka, (asleep) ? śpi : biega);
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();

        boolean właśnieSięObudziło = true;

        try
        {
            Thread.sleep(czasZabawy);
        }
        catch (Exception e)
        { }

        while (!stop)
        {
            if(asleep)
            {
                if(czasSnu == 0)czasSnu = (int)(Math.random() * maxCzas);
                engine.zmieńStanDziecka(współrzędnaDziecka, śpi);
                startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(czasSnu);
                    asleep = false;
                    czasSnu = 0;
                    właśnieSięObudziło = true;
                }
                catch (Exception e)
                {
                    czasSnu -= System.currentTimeMillis() - startTime;
                }
            }
            else
            {
                if(właśnieSięObudziło)
                {
                    try
                    {
                        współrzędnaDziecka = engine.sąsiaduje(współrzędnaDziecka, prezent);
                        engine.zmieńStanDziecka(współrzędnaDziecka, maPrezent);
                        return;
                    }
                    catch (WyjątekGry wyjątekGry) {}
                    engine.zmieńStanDziecka(współrzędnaDziecka, biega);
                    właśnieSięObudziło = false;
                    czasDoZmęczenia = minCzas + (long)(Math.random() * maxCzas);
                    startTime = System.currentTimeMillis();
                }

                if(System.currentTimeMillis() >= startTime + czasDoZmęczenia)
                {
                    asleep = true;
                    continue;
                }

                try
                {
                    współrzędnaDziecka = engine.sąsiaduje(współrzędnaDziecka, mikołaj);
                    if(!stop)engine.nowaGra();
                    break;
                }
                catch (WyjątekGry wyjątekGry) {}

                współrzędnaDziecka = engine.ruchDziecka(współrzędnaDziecka);

                try
                {
                    Thread.sleep(czasZabawy);
                }
                catch (Exception e)
                { }
            }
        }


    }

    public void zaczynamy()
    {
        stop = false;
        wątek.start();
    }
    public void kończymy()
    {
        stop = true;
    }

    public Thread getWątek()
    {
        return wątek;
    }


    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        /*private Thread wątek;
        private Engine engine;
        public boolean asleep;
        private long czasSnu;
        private long czasDoZmęczenia;
        private static final long maxCzas = 7000;
        private static final long minCzas = 3000, czasZabawy = 1000;
        private static final int mikołaj = 0, prezent = 13;
        private static final int biega = 0, śpi = 1, maPrezent = 2;
        private boolean stop;*/
        out.writeObject(współrzędnaDziecka);
        out.writeBoolean(asleep);
        out.writeLong(czasSnu);
        out.writeLong(czasDoZmęczenia);
        out.writeBoolean(stop);
    }


    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        współrzędnaDziecka = (Współrzędna) in.readObject();
        asleep = in.readBoolean();
        czasSnu = in.readLong();
        czasDoZmęczenia = in.readLong();
        stop = in.readBoolean();
    }

    private void readObjectNoData() throws ObjectStreamException
    {
        throw new InvalidObjectException("Stream data required");
    }
}
