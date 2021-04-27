package grafika;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanelDoRysowania extends JPanel implements MouseMotionListener, MouseListener
{
    private BufferedImage obrazDoRysowania;
    private BufferedImage obraz;

    private int szerokośćPanelu, wysokośćPanelu;
    private static final Color kolorTła = new Color(168, 169, 185);

    private int przybliżenie;
    private DolnyPasek dolnyPasek;

    public PanelDoRysowania(DolnyPasek dolnyPasek)
    {
        super();
        this.dolnyPasek = dolnyPasek;
        inicjalizuj();
        ustawWłaściwości();
    }

    private void narysujBiałyObraz()
    {
        Graphics g = obraz.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, obrazDoRysowania.getWidth(), obrazDoRysowania.getHeight());
        g.dispose();
    }

    private void rysujDoPanelu()
    {
        Graphics g = obrazDoRysowania.getGraphics();

        g.drawImage(obraz, 0, 0, obraz.getWidth() * przybliżenie, obraz.getHeight() * przybliżenie, null);

        g.dispose();
    }

    private void rysuj(MouseEvent e)
    {
        Graphics g = obraz.getGraphics();

        if(SwingUtilities.isLeftMouseButton(e))g.setColor(dolnyPasek.getColor(true));
        else g.setColor(dolnyPasek.getColor(false));

        g.fillRect(e.getX() / przybliżenie, e.getY() / przybliżenie,  1,  1);

        g.dispose();
        rysujDoPanelu();
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(obrazDoRysowania, 0, 0, null);
    }

    void zapisz(File file)
    {
        String nazwaPliku = file.getAbsolutePath();
        int indeks = nazwaPliku.lastIndexOf('.');
        try
        {
            ImageIO.write(obraz, nazwaPliku.substring(indeks + 1), file);
        }
        catch (Exception e)
        {
            wyświetlWiadomość("Nie można zapisać pliku: " + e.getMessage());
        }
    }

    void wczytaj(File file)
    {
        try
        {
            obraz = ImageIO.read(file);
            setPreferredSize(new Dimension(obraz.getWidth(), obraz.getHeight()));
            obrazDoRysowania = new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            przybliżenie = 1;
            rysujDoPanelu();
            repaint();
        }
        catch(Exception e)
        {
            wyświetlWiadomość("Nie można otworzyć pliku: " + e.getMessage());
        }
    }

    void przybliż()
    {
        przybliżenie *= 2;
        setPreferredSize(new Dimension(obraz.getWidth() * przybliżenie, obraz.getHeight() * przybliżenie));
        obrazDoRysowania = new BufferedImage(obraz.getWidth() * przybliżenie, obraz.getHeight() * przybliżenie, BufferedImage.TYPE_3BYTE_BGR);
        rysujDoPanelu();
        repaint();
    }

    void oddal()
    {
        przybliżenie /= 2;
        obrazDoRysowania = new BufferedImage(obraz.getWidth() * przybliżenie, obraz.getHeight() * przybliżenie, BufferedImage.TYPE_3BYTE_BGR);
        setPreferredSize(new Dimension(obraz.getWidth() * przybliżenie, obraz.getHeight() * przybliżenie));
        rysujDoPanelu();
        repaint();
    }

    BufferedImage getObrazDoRysowania() {
        return obrazDoRysowania;
    }

    private void inicjalizuj()
    {
        przybliżenie = 1;
        szerokośćPanelu = 770;
        wysokośćPanelu = 630;
        obrazDoRysowania = new BufferedImage(szerokośćPanelu, wysokośćPanelu, BufferedImage.TYPE_3BYTE_BGR);
        obraz = obrazDoRysowania = new BufferedImage(szerokośćPanelu, wysokośćPanelu, BufferedImage.TYPE_3BYTE_BGR);
        narysujBiałyObraz();
        rysujDoPanelu();
    }

    private void ustawWłaściwości()
    {
        setPreferredSize(new Dimension(szerokośćPanelu, wysokośćPanelu));
        setBackground(kolorTła);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    void wyświetlWiadomość(String tekst)
    {
        JFrame frame = new JFrame();
        JTextArea poleTkestowe = new JTextArea(tekst);
        frame.add(poleTkestowe);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setTitle("Uwaga");
        setVisible(true);
    }

    boolean możliwePrzybliżenie()
    {
        return przybliżenie < 8;
    }
    boolean możliweOddalenie()
    {
        return przybliżenie > 1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        rysuj(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        rysuj(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        dolnyPasek.zmieńWspółrzędną(e.getX() / przybliżenie, e.getY() / przybliżenie);
    }
}
