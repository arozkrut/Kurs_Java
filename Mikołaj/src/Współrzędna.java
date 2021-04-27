import java.io.*;

public class Współrzędna implements Serializable
{
    public int wiersz, kolumna;
    public Współrzędna(int wiersz, int kolumna)
    {
        this.wiersz =  wiersz;
        this.kolumna = kolumna;
    }

    public String toString()
    {
        return wiersz + " " + kolumna;
    }

    private void writeObject(final ObjectOutputStream out) throws IOException
    {
        out.writeInt(wiersz);
        out.writeInt(kolumna);
    }

    /**
     * Deserialize this instance from input stream.
     *
     * @param in Input Stream from which this instance is to be deserialized.
     * @throws IOException Thrown if error occurs in deserialization.
     * @throws ClassNotFoundException Thrown if expected class is not found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        wiersz = in.readInt();
        kolumna = in.readInt();
    }

    private void readObjectNoData() throws ObjectStreamException
    {
        throw new InvalidObjectException("Stream data required");
    }
}
