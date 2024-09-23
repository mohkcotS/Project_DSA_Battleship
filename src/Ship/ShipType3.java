package Ship;

import java.awt.*;

public class ShipType3 extends Ship {
    public ShipType3(){
        this.name = "Ship 3";
        this.size = 4;
        this.c = new Color(0xFFC09C);
        this.color = "Light Apricot";
        img = setup("Ship/Ship3",96,96);
    }
}
