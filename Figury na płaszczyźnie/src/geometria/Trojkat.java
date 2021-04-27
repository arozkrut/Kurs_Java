package geometria;

public class Trojkat
{
    private Punkt a, b, c;

    public Trojkat(Punkt a, Punkt b, Punkt c)throws IllegalArgumentException
    {
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();
        double cx = c.getX();
        double cy = c.getY();
        
        if(ax == bx && ay == by)throw new IllegalArgumentException();
        if(ax == bx && bx == cx)throw new IllegalArgumentException();
        if(ax != bx)
        {
            // wzór y = k*x + l
            double k = (by - ay)/(bx - ax);
            double l = ay - k*ax;
            if(cy == k*cx + l)throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void przesuń(Wektor v)
    {
        a.przesuń(v);
        b.przesuń(v);
        c.przesuń(v);
    }

    public void obróć(Punkt p, double kąt)
    {
        a.obróć(p, kąt);
        b.obróć(p, kąt);
        c.obróć(p, kąt);
    }

    public void odbij(Prosta p)
    {
        a.odbij(p);
        b.odbij(p);
        c.odbij(p);
    }
}
