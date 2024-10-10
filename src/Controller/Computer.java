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
    int numHit=-1;
    ArrayList<Coordinate> listHit = new ArrayList<>();
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
        int num = rd.nextInt(100);
        boolean checkChooseSuccess = false;
        boolean check;
        int up=0,down =0,right=0,left=0;
        while (!checkChooseSuccess){
            if(numHit != -1){
                check = true;
                int num1 = numHit;
                if(up == 0){
                    if(num1 >9){
                        num1 -= 10;
                    }
                    else{
                        check = false;
                    }
                    up = 1;
                }
                else if(down == 0){
                    if(num1 < 90){
                        num1 += 10;
                    }
                    else{
                        check = false;
                    }
                    down = 1;
                }
                else if(right == 0){
                    if(num1 % 10 != 9){
                        num1 += 1;
                    }
                    else{
                        check = false;
                    }
                    right = 1;
                }
                else if(left == 0){
                    if(num1 % 10 != 0){
                        num1 -= 1;
                    }
                    else{
                        check = false;
                    }
                    left = 1;
                }
                else{
                    numHit = -1;
                }

                if(check){
                    int x = num1 % 10;
                    int y = num1 / 10;
                    if (checkCanChoosePosition(x, y)) {
                        if (gp.b.getFromBoardPlayer(x, y) < 0) {
                            numHit = num1;
                            listHit.add(new Coordinate(x,y));

                        }
                        computerChooseX = x;
                        computerChooseY = y;
                        checkChooseSuccess = true;
                    }
                }
            }
            else{
                num %= 100;
                int x = num % 10;
                int y = num / 10;
                if (checkCanChoosePosition(x, y)) {
                    if (gp.b.getFromBoardPlayer(x, y) < 0) {
                        numHit = num;
                        listHit.add(new Coordinate(x,y));

                    }
                    computerChooseX = x;
                    computerChooseY = y;
                    checkChooseSuccess = true;
                } else {
                    num++;
                }
            }
        }
    }


    public boolean checkCanChoosePosition(int x , int y){
        return gp.b.getFromBoardPlayer(x,y) <= 0;
    }

    public void shooting(){
        if(gp.timer == 100){
            choosePosition();}
        if(computerChooseX != -1 && computerChooseY != -1) {
            int num = gp.b.getFromBoardPlayer(gp.computer.computerChooseX,gp.computer.computerChooseY);
            switch (num){
                case -1 -> {
                    gp.b.setPlayerBoard(gp.computer.computerChooseX, gp.computer.computerChooseY, 1);
                    gp.player.ship.get(0).removeCoordinate(new Coordinate(gp.computer.computerChooseX, gp.computer.computerChooseY));
                }
                case -2 -> {
                    gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,2);
                    gp.player.ship.get(1).removeCoordinate(new Coordinate(gp.computer.computerChooseX, gp.computer.computerChooseY));
                }
                case -3 -> {
                    gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,3);
                    gp.player.ship.get(2).removeCoordinate(new Coordinate(gp.computer.computerChooseX, gp.computer.computerChooseY));
                }
                case -4 -> {
                    gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,4);
                    gp.player.ship.get(3).removeCoordinate(new Coordinate(gp.computer.computerChooseX, gp.computer.computerChooseY));
                }
                case -5 -> {
                    gp.b.setPlayerBoard(gp.computer.computerChooseX,gp.computer.computerChooseY,5);
                    gp.player.ship.get(4).removeCoordinate(new Coordinate(gp.computer.computerChooseX, gp.computer.computerChooseY));
                }
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
            int direction = random(0)%2;
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
        return rd.nextInt(10-x);
    }

}
