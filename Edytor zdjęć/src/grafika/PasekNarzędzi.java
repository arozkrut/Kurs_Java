package grafika;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PasekNarzędzi extends JToolBar implements ActionListener
{
    private JButton przyciskWczytywania, przyciskZapisywania;
    private JButton przyciskPrzybliż, przyciskOddal;
    private JButton[] przyciskiNawigacji;
    private JButton przycikWyboruKoloru;
    private JButton[] przyciskiKolorów;
    private Color[] kolory;

    private PanelDoRysowania panelDoRysowania;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter;
    
    private GłówneOkno główneOkno;
    private DolnyPasek dolnyPasek;

    public PasekNarzędzi(PanelDoRysowania panelDoRysowania, GłówneOkno główneOkno, DolnyPasek dolnyPasek)
    {
        super();
        this.panelDoRysowania = panelDoRysowania;
        this.główneOkno = główneOkno;
        this.dolnyPasek = dolnyPasek;
        zainicjalizuj();
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object button = e.getSource();

        if(button == przyciskWczytywania)
        {
            fileChooser.setDialogTitle("Open");
            int wartość = fileChooser.showOpenDialog(this);
            if (wartość == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                panelDoRysowania.wczytaj(file);
            }
        }
        else if (button == przyciskZapisywania)
        {
            fileChooser.setDialogTitle("Save");
            int wartość = fileChooser.showOpenDialog(this);
            if (wartość == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                if(filter.accept(file))
                {
                    panelDoRysowania.zapisz(file);
                }
                else panelDoRysowania.wyświetlWiadomość("Zły format");
            }
        }
        else if(button == przyciskPrzybliż)
        {
            if(!panelDoRysowania.możliwePrzybliżenie())return;
            panelDoRysowania.przybliż();

        }
        else if(button == przyciskOddal)
        {
            if(!panelDoRysowania.możliweOddalenie())return;
            panelDoRysowania.oddal();
        }
        else if(button == przycikWyboruKoloru)
        {
            Color kolor = JColorChooser.showDialog(this,"Wybierz kolor", Color.WHITE);
            dolnyPasek.zmieńColor(kolor);
        }
        else
        {
            for(int i = 0; i < 16; ++i)
            {
                if( button == przyciskiKolorów[i])
                {
                    dolnyPasek.zmieńColor(kolory[i]);
                    break;
                }
            }
        }
    }

    private void zainicjalizuj()
    {
        przyciskWczytywania = stwórzPrzycisk("nowy", "otwórz nowy obraz");

        przyciskZapisywania = stwórzPrzycisk("zapisz", "zapisz obraz");
        przyciskPrzybliż = stwórzPrzycisk("powiększ", "przybliż obraz");
        przyciskOddal = stwórzPrzycisk("oddal", "oddal obraz");

        przyciskiNawigacji = new JButton[4];
        przyciskiNawigacji[0] = stwórzPrzycisk("lewo", "przewiń w lewo");
        przyciskiNawigacji[1] = stwórzPrzycisk("góra", "przewiń w górę");
        przyciskiNawigacji[2] = stwórzPrzycisk("prawo", "przewiń w prawo");
        przyciskiNawigacji[3] = stwórzPrzycisk("dół", "przewiń w dół");
        przyciskiNawigacji[2].addActionListener(e -> główneOkno.setHoryzontalny(główneOkno.getHoryzontalny().getMaximum()));
        przyciskiNawigacji[0].addActionListener(e -> główneOkno.setHoryzontalny(0));
        przyciskiNawigacji[1].addActionListener(e -> główneOkno.setWertykalny(0));
        przyciskiNawigacji[3].addActionListener(e -> główneOkno.setWertykalny((główneOkno.getWertykalny()).getMaximum()));

        przycikWyboruKoloru = stwórzPrzycisk("kolory", "wybierz inny kolor");

        kolory = new Color[16];
        kolory[0] = new Color(0, 0, 0);
        kolory[1] = new Color(255, 255, 255);
        kolory[2] = new Color(255, 249, 12);
        kolory[3] = new Color(252, 255, 165);
        kolory[4] = new Color(255, 146, 26);
        kolory[5] = new Color(255, 207, 129);
        kolory[6] = new Color(255, 0, 0);
        kolory[7] = new Color(255, 120, 123);
        kolory[8] = new Color(255, 3, 175);
        kolory[9] = new Color(255, 133, 229);
        kolory[10] = new Color(133, 0, 255);
        kolory[11] = new Color(183, 131, 255);
        kolory[12] = new Color(0, 0, 255);
        kolory[13] = new Color(143, 180, 255);
        kolory[14] = new Color(0, 255, 0);
        kolory[15] = new Color(127, 255, 127);

        przyciskiKolorów = new JButton[16];
        for(int i = 0;i<16;++i)
        {
            przyciskiKolorów[i] = new JButton();
            przyciskiKolorów[i].setMaximumSize(new Dimension(30, 30));
            przyciskiKolorów[i].setBackground(kolory[i]);
            przyciskiKolorów[i].addActionListener(this);
            add(przyciskiKolorów[i]);
        }

        fileChooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(filter);
    }

    private JButton stwórzPrzycisk(String plik, String text)
    {
        JButton button = new JButton(new ImageIcon("src/obrazy/" + plik + ".png"));
        button.setMaximumSize(new Dimension(30, 30));
        button.setToolTipText(text);
        button.addActionListener(this);
        add(button);
        return button;
    }

}
