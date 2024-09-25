package Controller;

import Main.GamePanel;
import Ship.*;

import java.util.ArrayList;
import java.util.Random;

public class Computer {
    GamePanel gp;
    public ShipType1 ship1;
    public ShipType2 ship2;
    public ShipType3 ship3;
    public ShipType4 ship4;
    public ShipType5 ship5;

    public ArrayList<Ship> ship;

    public int computerChooseX = -1;
    public int computerChooseY = -1;
    public Computer(GamePanel gp){
        this.gp = gp;
        this.ship = new ArrayList<>();
        this.ship1 = new ShipType1();
        this.ship2 = new ShipType2();
        this.ship3 = new ShipType3();
        this.ship4 = new ShipType4();
        this.ship5 = new ShipType5();
        setupShipForComputer();
    }
    public void setupShipForComputer(){
        ship.add(ship1);
        ship.add(ship2);
        ship.add(ship3);
        ship.add(ship4);
        ship.add(ship5);
    }

    public void choosePosition(){
        Random rd = new Random();
        computerChooseX = rd.nextInt(9);
        computerChooseY = rd.nextInt(9);
    }

    public void shooting(){
        if(gp.timer == 150){
            choosePosition();}
        if(computerChooseX != -1 && computerChooseY != -1) {
            int num = gp.b.getFromBoardPlayer(gp.computer.computerChooseX,gp.computer.computerChooseY);
            switch (num){
                case -1 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,1);
                case -2 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,2);
                case -3 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,3);
                case -4 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,4);
                case -5 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,5);
                case  0 -> gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,6);
            }
        }
    }

}
