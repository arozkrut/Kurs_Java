package geometria;

public class Punkt
{
    private double x, y;

    public Punkt(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void przesuń(Wektor v)
    {
        x += v.getX();
        y += v.getY();
    }

    public void obróć(Punkt p, double kąt)
    {
        double px = p.getX();
        double py = p.getY();

        double nx = x - px;
        double ny = y - py;

        x = nx * Math.cos(kąt) - ny * Math.sin(kąt);
        y = nx * Math.sin(kąt) + ny * Math.cos(kąt);

        x += px;
        y += py;
    }

    public void odbij(Prosta p)
    {
        if(p.a * x + p.b * y + p.c == 0)return;

        Prosta prostopadła = new Prosta(-p.b, p.a, p.b * x - p.a * y);
        Punkt przecięcieProstych = Prosta.punktPrzecięcia(p, prostopadła);

        przesuń(new Wektor(2 * (przecięcieProstych.getX() - x), 2 * (przecięcieProstych.getY() - y)));
    }

    public double getX() { return x; }

    public double getY() { return y; }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }
}
