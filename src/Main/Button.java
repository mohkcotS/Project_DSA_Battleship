package Main;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Font font =  new Font("Times New Roman",Font.BOLD,18);
    public Button(String x){
        setText(x);
        setBackground(new Color(0xFFF1AD));
        setForeground(Color.black);
        setFont(font);
        setBorderPainted(false);
        setFocusPainted(false);

    }
}
