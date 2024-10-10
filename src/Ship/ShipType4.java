package Ship;

import java.awt.*;

public class ShipType4 extends Ship {
    public ShipType4(){
        this.name = "Ship 4";
        this.size = 5;
        this.c = new Color(0xA12E2E);
        this.color = "Light Coral";
        img = setup("Ship/Ship4",96,96);
    }
}
