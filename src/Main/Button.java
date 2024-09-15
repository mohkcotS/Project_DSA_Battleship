package Main;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Font font =  new Font("Times New Roman",Font.BOLD,18);
    public Button(String x){
        super.setText(x);
        super.setBackground(new Color(0xFFF1AD));
        super.setForeground(Color.black);
        super.setFont(font);
    }
}
