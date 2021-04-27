package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DolnyPasek extends JPanel implements ActionListener
{
    private JLabel współrzędneMyszki, napis;
    private JButton lewy, prawy;
    private boolean lewyAktywny;
    private Color kolorLewego, kolorPrawego;
    private JLabel podglądLewy, podglądPrawy;
    private Color wybrany, niewybrany;

    DolnyPasek()
    {
        super(new FlowLayout());

        lewyAktywny = true;
        kolorLewego = new Color(0,0,0);
        kolorPrawego = new Color(0, 0,0);
        wybrany = new Color(216, 225, 255);
        niewybrany = new Color(162, 162, 162);

        współrzędneMyszki = new JLabel("( 0, 0 )");
        współrzędneMyszki.setHorizontalTextPosition(JLabel.CENTER);
        napis = new JLabel("Przycisk myszy do wyboru koloru:");
        lewy = new JButton("Lewy");
        prawy = new JButton("Prawy");
        lewy.addActionListener(this);
        prawy.addActionListener(this);
        lewy.setBackground(wybrany);
        prawy.setBackground(niewybrany);


        podglądLewy = new JLabel();
        podglądPrawy = new JLabel();
        podglądLewy.setPreferredSize(new Dimension(20,20));
        podglądPrawy.setPreferredSize(new Dimension(20,20));
        podglądLewy.setOpaque(true);
        podglądPrawy.setOpaque(true);
        podglądLewy.setBackground(kolorLewego);
        podglądPrawy.setBackground(kolorPrawego);
        podglądPrawy.setVisible(true);
        podglądLewy.setVisible(true);


        add(współrzędneMyszki);
        add(podglądLewy);
        add(podglądPrawy);
        add(napis);
        add(lewy);
        add(prawy);


        setPreferredSize(new Dimension(770, 30));

        setVisible(true);
    }

    void zmieńWspółrzędną(int x, int y)
    {
        współrzędneMyszki.setText("( " + x + ", " + y + " )");
    }
    void zmieńColor(Color kolor)
    {
        if(lewyAktywny)
        {
            kolorLewego = kolor;
            podglądLewy.setBackground(kolorLewego);
        }
        else
        {
            kolorPrawego = kolor;
            podglądPrawy.setBackground(kolorPrawego);
        }
    }

    Color getColor(boolean lewy)
    {
        if(lewy)return kolorLewego;
        return kolorPrawego;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lewy)
        {
            lewyAktywny = true;
            lewy.setBackground(wybrany);
            prawy.setBackground(niewybrany);
        }
        else
        {
            lewyAktywny = false;
            lewy.setBackground(niewybrany);
            prawy.setBackground(wybrany);
        }
    }
}
