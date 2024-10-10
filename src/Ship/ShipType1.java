package Ship;

import java.awt.*;

public class ShipType1 extends Ship{
    public ShipType1(){
        this.name = "Ship 1";
        this.size = 2;
        this.c = new Color(0x28D228);
        this.color = "Mint Green";
        img = setup("Ship/Ship1",96,96);
    }

}
