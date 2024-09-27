package Controller;

import Battle.Coordinate;
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
    public boolean check;
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

    public void shooting(){
        if(gp.mouse.colPlayerChoose != -1 && gp.mouse.rowPlayerChoose != -1) {
            int num = gp.b.getFromBoardComputer(gp.mouse.colPlayerChoose,gp.mouse.rowPlayerChoose);
            switch (num){
                case   0 -> gp.b.setComputerBoard(gp.mouse.colPlayerChoose,gp.mouse.rowPlayerChoose,6);
                case  -1 -> {
                    gp.b.setComputerBoard(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose, 1);
                    gp.computer.ship.get(0).removeCoordinate(new Coordinate(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose));
                }
                case  -2 -> {
                    gp.b.setComputerBoard(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose, 2);
                    gp.computer.ship.get(1).removeCoordinate(new Coordinate(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose));
                }
                case  -3 -> {
                    gp.b.setComputerBoard(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose, 3);
                    gp.computer.ship.get(2).removeCoordinate(new Coordinate(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose));
                }
                case  -4 -> {
                    gp.b.setComputerBoard(gp.mouse.colPlayerChoose,gp.mouse.rowPlayerChoose,4);
                    gp.computer.ship.get(3).removeCoordinate(new Coordinate(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose));
                }
                case  -5 -> {
                    gp.b.setComputerBoard(gp.mouse.colPlayerChoose,gp.mouse.rowPlayerChoose,5);
                    gp.computer.ship.get(4).removeCoordinate(new Coordinate(gp.mouse.colPlayerChoose, gp.mouse.rowPlayerChoose));
                }
            }

        }
        gp.computer.checkDestroy();
    }

    public void checkDestroy(){
        for(Ship s : ship){
            if(s.cor.isEmpty() && s.isDestroy == 0){
                gp.ui.destroyStatus = s.name;
                s.isDestroy = 1;
                check = true;
            }
        }
    }

}
