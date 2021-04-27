import kalendarz.GłównyPanel;

import javax.swing.*;
import java.awt.*;

public class Kalendarz
{
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TabbedPaneDemo");
        GłównyPanel głównyPanel = new GłównyPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(głównyPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
