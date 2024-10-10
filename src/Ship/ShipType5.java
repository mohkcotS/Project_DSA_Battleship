package Ship;

import java.awt.*;

public class ShipType5 extends Ship{
    public ShipType5(){
        this.name = "Ship 5";
        this.size = 6;
        this.c = new Color(0xA90E95);
        this.color = "Hot Pink";
        img = setup("Ship/Ship5",96,96);
    }
}
