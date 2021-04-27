public class Trojkat
{
    private double a, b, c;

    public Trojkat(double a, double b, double c)throws WyjątekTrojkata
    {
        if(a < 0.0 || b < 0.0 || c < 0.0)throw new WyjątekTrojkata();
        if(!(a != 0.0 && b != 0.0 && c != 0.0))throw new WyjątekTrojkata();
        if(a > b && a > c && !(a < b + c))throw new WyjątekTrojkata();
        if(b > a && b > c && !(b < a + c))throw new WyjątekTrojkata();
        if(c > b && c > a && !(c < b + a))throw new WyjątekTrojkata();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "(" + a + " " + b + " " + c + ")";
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
