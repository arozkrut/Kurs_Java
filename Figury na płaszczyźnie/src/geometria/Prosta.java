package geometria;

public class Prosta
{
    public final double a, b, c;

    public Prosta(double a, double b, double c) throws IllegalArgumentException
    {
        if(a == 0 && b == 0)throw new IllegalArgumentException();

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Prosta przesuń(Prosta p, Wektor v)
    {
        if(p.a == 0)return new Prosta(0.0, p.b, p.c - p.b * v.dy);
        if(p.b == 0)return new Prosta(p.a, 0.0, p.c - p.a * v.dx);

        Punkt przesunięty = new Punkt(v.dx, -p.c / p.b + v.dy);
        return new Prosta(p.a, p.b, -(p.a * przesunięty.getX() + p.b * przesunięty.getY()));
    }

    public static boolean prosteRównoległe(Prosta k, Prosta l)
    {
        if(k.a * l.b == l.a * k.b)return true;
        return false;
    }

    public static boolean prosteProstopadłe(Prosta k, Prosta l)
    {
        if(k.a * l.a + l.b * k.b == 0)return true;
        return false;
    }

    public static Punkt punktPrzecięcia(Prosta k, Prosta l)
    {
        if(prosteRównoległe(k, l))throw new IllegalArgumentException();

        double w = k.a * l.b - l.a * k.b;
        return new Punkt((k.b * l.c - l.b * k.c) / w, (k.c * l.a - l.c * k.a) / w);
    }

    @Override
    public String toString() {
        return String.valueOf(a) + "x + " + String.valueOf(b) + "y + " + String.valueOf(c) + " = 0";
    }
}
