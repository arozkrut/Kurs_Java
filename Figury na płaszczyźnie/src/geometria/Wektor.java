package geometria;

public class Wektor
{
    public final double dx, dy;

    public Wektor(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public double getX() { return dx; }

    public double getY() { return dy; }

    public double getDługość() { return Math.sqrt(dx*dx + dy*dy); }

    public static Wektor złóż(Wektor v1, Wektor v2)
    {
        return new Wektor(v1.dx + v2.dx, v1.dy + v2.dy);
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(dx) + ", " + String.valueOf(dy) + "]";
    }
}
