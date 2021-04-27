import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Plansza extends JPanel implements KeyListener, ActionListener, Serializable
{
    private final static int blockLength = 35, N = 20, M = 20;
    public int[] stanDzieci;
    private BufferedImage obrazPlanszy;
    private Image[] obrazyDzieci;
    private Image obrazMikołaja, obrazPrezentu, obrazŚpiącegoDziecka, obrazDzieckazPrezentem;
    private static final int biega = 0, śpi = 1, maPrezent = 2, mikołaj = 0, prezent = 13, puste = -1;
    private Engine engine;
    private int[][] rozmieszczenie;
    public Timer timer;
    private static final int czas = 50;

    public Plansza()
    {
        super();

        obrazPlanszy = new BufferedImage(M * blockLength, N * blockLength, BufferedImage.TYPE_3BYTE_BGR);
        stanDzieci = new int[12];
        obrazyDzieci = new Image[12];

        narysujPlanszęPierwszyRaz();
        wczytajObrazy();

        engine = new Engine(this);
        addKeyListener(this);
        setPreferredSize(new Dimension(M * blockLength, N * blockLength));
        setVisible(true);
        setFocusable(true);

        timer = new Timer(czas, this);
        timer.setInitialDelay(2000);
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        rozmieszczenie = engine.getTablicaRozmieszczeń();
        int dziecko;

        g.drawImage(obrazPlanszy, 0, 0, null);
        for(int i = 0; i < N; ++i)
        {
            for(int j = 0; j < M; ++j)
            {
                if(rozmieszczenie[i][j] != puste)
                {
                    switch (rozmieszczenie[i][j])
                    {
                        case mikołaj:
                            g.drawImage(obrazMikołaja, j * blockLength, i * blockLength, null);
                            break;
                        case prezent:
                            g.drawImage(obrazPrezentu, j * blockLength, i * blockLength, null);
                            break;
                        default:
                            dziecko = rozmieszczenie[i][j] - 1;
                            switch (stanDzieci[dziecko])
                            {
                                case biega:
                                    g.drawImage(obrazyDzieci[dziecko], j * blockLength, i * blockLength, null);
                                    break;
                                case śpi:
                                    g.drawImage(obrazŚpiącegoDziecka, j * blockLength, i * blockLength, null);
                                    break;
                                case maPrezent:
                                    g.drawImage(obrazDzieckazPrezentem, j * blockLength, i * blockLength, null);
                                    break;
                            }
                            break;
                    }
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //if(eksploatorWRuchu)return;

        int key = e.getKeyCode();

        if(key != KeyEvent.VK_DOWN && key != KeyEvent.VK_UP && key != KeyEvent.VK_RIGHT && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_SPACE)return;

        //eksploatorWRuchu = true;
        //progresRuchu = 0;

        switch (key)
        {
            case KeyEvent.VK_UP:
                engine.ruchMikołaja(0);
                break;
            case KeyEvent.VK_RIGHT:
                engine.ruchMikołaja(1);
                break;
            case KeyEvent.VK_DOWN:
                engine.ruchMikołaja(2);
                break;
            case KeyEvent.VK_LEFT:
                engine.ruchMikołaja(3);
                break;
            case KeyEvent.VK_SPACE:
                engine.zostawPrezent();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void narysujPlanszęPierwszyRaz()
    {
        Graphics g = obrazPlanszy.createGraphics();
        g.setColor(new Color(206, 152, 111));
        g.fillRect(0, 0, M * blockLength, N * blockLength);
        g.dispose();
    }

    public void zmieńStanDziecka(int dziecko, int stan)
    {
        stanDzieci[dziecko] = stan;
    }

    private void wczytajObrazy()
    {
        try
        {
            for(int i = 0; i < 12; ++i)obrazyDzieci[i] = ImageIO.read(new File("src/obrazy/dziecko" + i + ".png"));
            obrazMikołaja = ImageIO.read(new File("src/obrazy/mikołaj.png"));
            obrazPrezentu = ImageIO.read(new File("src/obrazy/prezent.png"));
            obrazŚpiącegoDziecka = ImageIO.read(new File("src/obrazy/śpiące.png"));
            obrazDzieckazPrezentem = ImageIO.read(new File("src/obrazy/dzieckozPrezentem.png"));
        }
        catch (Exception e)
        {
            System.err.println("nie można wczytać obrazu: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void zakończGrę()
    {
        timer.stop();
        engine.zakończGrę();
    }

    public void wznówGrę()
    {
        timer.start();
    }
}
