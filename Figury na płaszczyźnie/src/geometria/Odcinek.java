package geometria;

public class Odcinek
{
    private Punkt a, b;

    public Odcinek(Punkt a, Punkt b)throws IllegalArgumentException
    {
        if(a.getX() == b.getX() && a.getY() == b.getY())throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
    }

    public void przesuń(Wektor v)
    {
        a.przesuń(v);
        b.przesuń(v);
    }

    public void obróć(Punkt p, double kąt)
    {
        a.obróć(p, kąt);
        b.obróć(p, kąt);
    }

    public void odbij(Prosta p)
    {
        a.odbij(p);
        b.odbij(p);
    }
}
