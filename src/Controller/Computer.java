package Controller;

import Battle.Coordinate;
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

    public boolean check = false;

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
        setUpShipOnBoard();
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
        if(gp.timer == 100){
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
        gp.player.checkDestroy();
    }

    public void setUpShipOnBoard(){
        int n = 5;
        while (n > 0) {
            chooseCoordinateForShip(n);
            n--;
        }
    }

    public void chooseCoordinateForShip(int ship){
        int size = ship + 1;
        boolean setup = false;
        while (!setup){
            int x;
            int y;
            int direction = random(0);
            // y-axis
            if(direction == 0){
                boolean check = true;
                x = random(0);
                y = random(ship);
                for (int i = y; i <= y + size - 1 ; i++) {
                        if (gp.b.getFromBoardComputer(x, i) != 0) {
                            check = false;
                            break;
                        }
                    }

                    if (check) {
                        for (int i = y; i <= y + size - 1 ; i++) {
                            gp.b.setComputerBoard(x, i, -ship);
                            this.ship.get(ship-1).addCoordinate(new Coordinate(x,i));
                        }
                        setup = true;
                    }
            }
            // x - axis
            if(direction == 1){
                boolean check = true;
                x = random(ship);
                y = random(0);
                for (int i = x; i <= x + size - 1 ; i++) {
                    if (gp.b.getFromBoardComputer(i, y) != 0) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    for (int i = x; i <= x + size - 1 ; i++) {
                        gp.b.setComputerBoard(i, y, -ship);
                        this.ship.get(ship-1).addCoordinate(new Coordinate(i,y));
                    }
                    setup = true;
                }
            }
        }
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


    public int random(int x){
        Random rd = new Random();
        return rd.nextInt(9-x);
    }

}
