package gra;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Plansza extends Panel implements KeyListener, Runnable
{
    private int rozmiar, bokOkna, bokKomnaty;
    private Image komnata, wyjście, biegnieLewo, biegniePrawo, stoi;
    private BufferedImage obrazLabiryntu;
    private Frame frame;
    private Color kolor;
    private int szerokośćŚciany;
    private int koniecRząd, koniecKolumna;
    private static final short góra = 0, prawo = 1, dół = 2, lewo = 3;
    private int eksploatorX, eksploatorY;
    private boolean eksploatorWRuchu;
    private int progresRuchu, kierunek;
    private Silnik silnik;
    private Thread animator;
    private static final long odstępCzasu = 5;

    Plansza(int rozmiar, Silnik silnik)
    {
        super();

        this.rozmiar = rozmiar;
        bokKomnaty = 30;
        bokOkna = rozmiar * bokKomnaty;
        szerokośćŚciany = 1;
        eksploatorWRuchu = false;
        this.silnik = silnik;

        frame = new Frame("Labirynt");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        this.setSize(rozmiar * bokKomnaty, rozmiar * bokKomnaty);
        frame.setSize(rozmiar * bokKomnaty, (rozmiar + 1) * bokKomnaty);
        frame.add(this, BorderLayout.CENTER);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setResizable(false);
        frame.setVisible(true);
        setVisible(true);//TODO: opcje?
        setFocusable(true);
        addKeyListener(this);
        //TODO: on close

        try
        {
            komnata = ImageIO.read(new File("komnata.png"));
            wyjście = ImageIO.read(new File("wyjście.png"));
            biegnieLewo = ImageIO.read(new File("bieg.jpg"));
            biegniePrawo = ImageIO.read(new File("biegPrawo.jpg"));
            stoi = ImageIO.read(new File("stoi.png"));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        kolor = new Color(255, 224, 130);
    }

    void nowaGra(Komnata[][] labirynt, int koniecRząd, int koniecKolumna)
    {
        this.koniecRząd = koniecRząd;
        this.koniecKolumna = koniecKolumna;
        eksploatorX = 0;
        eksploatorY = rozmiar - 1;
        obrazLabiryntu = new BufferedImage(rozmiar * bokKomnaty, rozmiar * bokKomnaty, BufferedImage.TYPE_3BYTE_BGR);
        narysujLabirynt(labirynt);
        repaint();
    }

    private void narysujLabirynt(Komnata[][] labirynt)
    {
        Graphics g = obrazLabiryntu.createGraphics();

        for(int i = 0; i < rozmiar; ++i)
        {
            for(int j = 0;j < rozmiar; ++j)
            {
                g.drawImage(komnata, j * bokKomnaty, i * bokKomnaty, null);
            }
        }

        g.setColor(kolor);

        for(int i = 0; i < rozmiar; ++i)
        {
            for(int j = 0; j < rozmiar; ++j)
            {
                if(labirynt[i][j].wPrawo)
                {
                    g.fillRect(j * bokKomnaty + bokKomnaty - szerokośćŚciany, i * bokKomnaty + szerokośćŚciany, szerokośćŚciany * 2, bokKomnaty - 2 * szerokośćŚciany);
                }
                if(labirynt[i][j].wDół)
                {
                    g.fillRect(j * bokKomnaty + szerokośćŚciany, i * bokKomnaty + bokKomnaty - szerokośćŚciany, bokKomnaty - 2 * szerokośćŚciany, szerokośćŚciany * 2);
                }
            }
        }

        g.drawImage(wyjście, koniecKolumna * bokKomnaty + szerokośćŚciany, koniecRząd * bokKomnaty + szerokośćŚciany, null);

        g.dispose();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.drawImage(obrazLabiryntu, 0, 0, null);

        if(eksploatorWRuchu)
        {
            progresRuchu += 1;
            switch (kierunek)
            {
                case góra:
                    g.drawImage(biegniePrawo, eksploatorX * bokKomnaty + szerokośćŚciany, (eksploatorY + 1) * bokKomnaty + szerokośćŚciany - progresRuchu, null);
                    break;
                case prawo:
                    g.drawImage(biegniePrawo, (eksploatorX - 1) * bokKomnaty + szerokośćŚciany + progresRuchu, eksploatorY * bokKomnaty + szerokośćŚciany, null);
                    break;
                case dół:
                    g.drawImage(biegnieLewo, eksploatorX * bokKomnaty + szerokośćŚciany, (eksploatorY - 1) * bokKomnaty + szerokośćŚciany + progresRuchu, null);
                    break;
                case lewo:
                    g.drawImage(biegnieLewo, (eksploatorX + 1) * bokKomnaty + szerokośćŚciany - progresRuchu, eksploatorY * bokKomnaty + szerokośćŚciany, null);
                    break;
            }
        }
        else
        {
            g.drawImage(stoi, eksploatorX * bokKomnaty + szerokośćŚciany, eksploatorY * bokKomnaty + szerokośćŚciany, null);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(eksploatorWRuchu)return;

        int key = e.getKeyCode();

        if(key != KeyEvent.VK_DOWN && key != KeyEvent.VK_UP && key != KeyEvent.VK_RIGHT && key != KeyEvent.VK_LEFT)return;

        eksploatorWRuchu = true;
        progresRuchu = 0;

        switch (key)
        {
            case KeyEvent.VK_UP:
                if(silnik.ruch(góra))
                {
                    kierunek = góra;
                    eksploatorY -= 1;
                }
                else
                {
                    eksploatorWRuchu = false;
                    return;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(silnik.ruch(prawo))
                {
                    kierunek = prawo;
                    eksploatorX += 1;
                }
                else
                {
                    eksploatorWRuchu = false;
                    return;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(silnik.ruch(dół))
                {
                    kierunek = dół;
                    eksploatorY += 1;
                }
                else
                {
                    eksploatorWRuchu = false;
                    return;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(silnik.ruch(lewo))
                {
                    kierunek = lewo;
                    eksploatorX -=1;
                }
                else
                {
                    eksploatorWRuchu = false;
                    return;
                }
                break;
        }

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        long wcześniej, różnicaCzasu, sleep;

        wcześniej = System.currentTimeMillis();

        for(int i = 0; i<bokKomnaty; ++i)
        {
            repaint();
            różnicaCzasu = System.currentTimeMillis() - wcześniej;
            sleep = odstępCzasu - różnicaCzasu;
            if (sleep < 0) {
                sleep = 2;
            }
            try
            {
                Thread.sleep(sleep);
            }
            catch(Exception err)
            {
                System.err.println(err.getMessage());
            }
            wcześniej = System.currentTimeMillis();
        }

        eksploatorWRuchu = false;
        repaint();
        if(eksploatorX == koniecKolumna && eksploatorY == koniecRząd)silnik.nowaGra();
    }
}

//TODO: zmiana argumentow labirynt na cos prostszego
