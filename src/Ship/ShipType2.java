package Ship;

import java.awt.*;

public class ShipType2 extends Ship{
    public ShipType2(){
        this.name = "Ship 2";
        this.size = 3;
        this.c = new Color(0xA0DBFF);
        this.color = "Sky Blue";
        img = setup("Ship/Ship2",96,96);
    }
}
