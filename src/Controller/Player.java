package Controller;

import Main.GamePanel;
import Ship.*;


public class Player {
    GamePanel gp;
    ShipType1 s1;
    ShipType2 s2;
    ShipType3 s3;
    ShipType4 s4;
    ShipType5 s5;
    public Player(GamePanel gp){
        this.gp = gp;
        this.s1 = new ShipType1();
        this.s2 = new ShipType2();
        this.s3 = new ShipType3();
        this.s4 = new ShipType4();
        this.s5 = new ShipType5();
    }


}
