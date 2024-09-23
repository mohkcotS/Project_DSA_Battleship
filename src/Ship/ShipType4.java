package Ship;

import java.awt.*;

public class ShipType4 extends Ship {
    public ShipType4(){
        this.name = "Ship 4";
        this.size = 5;
        this.c = new Color(0xFF7C7C);
        this.color = "Light Coral";
        img = setup("Ship/Ship4",96,96);
    }
}
