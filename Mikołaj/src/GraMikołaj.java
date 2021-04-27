import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class GraMikołaj
{
    Plansza plansza;

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Mikołaj");
        plansza = new Plansza();
        /*
        try {

            FileInputStream file = new FileInputStream("gra.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            plansza = (Plansza) in.readObject();

            in.close();
            file.close();

            plansza.wznówGrę();
        }
        catch (IOException err)
        {

        }
        catch (ClassNotFoundException err)
        {
            System.err.println(err.getMessage());
            plansza = new Plansza();
        }*/

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                /*try
                {
                    plansza.zakończGrę();
                    FileOutputStream file = new FileOutputStream("gra.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    out.writeObject(plansza);

                    out.close();
                    file.close();
                }
                catch (IOException err)
                {
                    System.err.println("Nie udało się zapisać pliku\n" + err.getMessage());
                }*/
                frame.dispose();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(plansza, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public static void main(String[] args)
    {
        new GraMikołaj().createAndShowGUI();
    }
}
