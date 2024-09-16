package Controller;

import Main.GamePanel;
import Ship.*;

import java.util.ArrayList;


public class Player {
    GamePanel gp;
    public ShipType1 ship1;
    public ShipType2 ship2;
    public ShipType3 ship3;
    public ShipType4 ship4;
    public ShipType5 ship5;

    public ArrayList<Ship> ship;
    public Player(GamePanel gp){
        this.gp = gp;
        this.ship = new ArrayList<>();
        this.ship1 = new ShipType1();
        this.ship2 = new ShipType2();
        this.ship3 = new ShipType3();
        this.ship4 = new ShipType4();
        this.ship5 = new ShipType5();
        setupShipForPlayer();
    }
    public void setupShipForPlayer(){
        ship.add(ship1);
        ship.add(ship2);
        ship.add(ship3);
        ship.add(ship4);
        ship.add(ship5);
    }

    public boolean canPlay(){
        for (Ship value : ship) {
            if (value.setupStatusNo != 0) {
                return false;
            }
        }
        return true;

    }

}
