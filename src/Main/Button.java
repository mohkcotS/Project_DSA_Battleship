package Main;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Font font =  new Font("Times New Roman",Font.BOLD,18);

    public Button(String x){
        setText(x);
        setBackGround(false);
        setFont(font);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    public void setBackGround(Boolean isChoose){
        if(isChoose){
            setBackground(new Color(0xD08344));
            setForeground(Color.white);
        }
        else {
            setBackground(new Color(0xFFF1AD));
            setForeground(Color.black);
        }
    }
}
