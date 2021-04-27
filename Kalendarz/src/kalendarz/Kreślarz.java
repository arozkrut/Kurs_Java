package kalendarz;

import javax.swing.*;
import java.awt.*;

public class Kreślarz extends JLabel
        implements ListCellRenderer<String>
{
    private Font font;

    Kreślarz(int rozmiar)
    {
        super("", SwingConstants.CENTER);
        setBackground(new Color(143, 180, 255));
        font = new Font("LobsterTwo", Font.BOLD, rozmiar);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list,
                                                  String value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus)
    {

        setText(value);
        setFont(font);
        if((!value.matches(".*[a-z]+.*") && index % 7 == 6) || value.matches("niedziela.+"))setForeground(Color.red);
        else setForeground(Color.black);

        return this;
    }
}