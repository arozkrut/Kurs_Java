package grafika;

import javax.swing.*;
import java.awt.*;

public class GłówneOkno extends JFrame
{
    private JPanel głównyPanel;
    private PasekNarzędzi pasekNarzędzi;
    private PanelDoRysowania panelDoRysowania;
    private JScrollPane scrollPane;
    private JScrollBar wertykalny;
    private JScrollBar horyzontalny;
    private DolnyPasek dolnyPasek;

    public GłówneOkno()
    {
        super();
        inicjalizuj();
    }

    public JScrollBar getHoryzontalny() {
        return horyzontalny;
    }

    public void setHoryzontalny(int wart) {
        this.horyzontalny.setValue(wart);
    }

    public void setWertykalny(int wart) {
        this.wertykalny.setValue(wart);
    }

    public JScrollBar getWertykalny() {
        return wertykalny;
    }

    private void inicjalizuj()
    {
        głównyPanel = new JPanel(new BorderLayout());
        dolnyPasek = new DolnyPasek();
        panelDoRysowania = new PanelDoRysowania(dolnyPasek);
        pasekNarzędzi = new PasekNarzędzi(panelDoRysowania, this, dolnyPasek);


        scrollPane=new JScrollPane(panelDoRysowania, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(770, 630));

        wertykalny = scrollPane.getVerticalScrollBar();
        horyzontalny = scrollPane.getHorizontalScrollBar();

        wertykalny.addAdjustmentListener(e -> {
            Graphics2D g = (Graphics2D) panelDoRysowania.getGraphics();
            g.drawImage(panelDoRysowania.getObrazDoRysowania(),0,0,panelDoRysowania);
        });

        horyzontalny.addAdjustmentListener(e -> {
            Graphics2D g = (Graphics2D) panelDoRysowania.getGraphics();
            g.drawImage(panelDoRysowania.getObrazDoRysowania(), 0, 0, panelDoRysowania);
        });

        głównyPanel.add(pasekNarzędzi, BorderLayout.PAGE_START);
        głównyPanel.add(scrollPane, BorderLayout.CENTER);
        głównyPanel.add(dolnyPasek, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(głównyPanel);

        głównyPanel.setVisible(true);
        scrollPane.setVisible(true);

        pack();
        setTitle("Edytor zdjęć");
        setVisible(true);
    }
}
