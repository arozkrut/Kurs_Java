package gra;

public class Komnata
{
    boolean wGórę, wDół, wLewo, wPrawo;

    Komnata()
    {
        this.wGórę = false;
        this.wDół = false;
        this.wLewo = false;
        this.wPrawo = false;
    }

    @Override
    public String toString() {
        String s = " ";

        if(wGórę)s+="o \n";
        else s+="z \n";

        if(wLewo)s+="oK";
        else s+="zK";

        if(wPrawo)s+="o\n ";
        else s+="z\n ";

        if(wDół)s+="o ";
        else s+="z ";

        return s;
    }
}
