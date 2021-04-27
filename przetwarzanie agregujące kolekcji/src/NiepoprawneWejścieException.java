public class NiepoprawneWejścieException extends Exception
{
    public NiepoprawneWejścieException(int linia, int nrPliku)
    {
        super("Niepoprawne wyrażenie w pliku dane" + nrPliku + ".txt w linii " + linia);
    }
}
