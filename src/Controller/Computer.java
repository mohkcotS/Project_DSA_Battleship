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
    int turn =0;
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
        //0.up 1.down 2.right 3.left
        Random rd = new Random();
        int num = rd.nextInt(99);
        int num1 = numHit;
        boolean checkChooseSuccess = false;
        while (!checkChooseSuccess){
            if(num1 != -1 && turn <= 3){
                int direction = -1;
                int checkCanChoose = -1;
                while (checkCanChoose != 1){
                    direction = rd.nextInt(4);

                    if(num1 == 0 && direction == 3){
                        checkCanChoose = 0;
                    }
                    else if(num1 == 99 && direction == 2){
                        checkCanChoose = 0;
                    }
                    else if(num1 <= 9 && direction == 0){
                        checkCanChoose = 0;
                    }
                    else if(num1 >= 90 && direction == 1){
                        checkCanChoose = 0;
                    }
                    else if(num % 10 == 0 && direction == 3){
                        checkCanChoose = 0;
                    }

                    else if(num % 10 == 9 && direction == 2){
                        checkCanChoose = 0;
                    }
                    else{
                        checkCanChoose = 1;
                    }
                }
                if(direction == 0){
                    num1 -= 10;
                }
                if(direction == 1){
                    num1 += 10;
                }
                if(direction == 2){
                    num1 += 1;
                }
                if(direction == 3){
                    num1 -= 1;
                }

                int a = num1 % 10;
                int b = num1 / 10;

                if(gp.b.getFromBoardPlayer(a,b) <= 0){
                    computerChooseX = a;
                    computerChooseY = b;
                    checkChooseSuccess = true;
                    if(gp.b.getFromBoardPlayer(a,b) < 0){
                        numHit = num1;
                        turn = 0;
                    }
                    else {
                        turn++;
                    }
                }
                if(turn == 3){
                    turn = 0;
                    numHit = -1;
                }
                System.out.println("1 "+num1);
            }
            else {
                num %= 100;
                int x = num % 10;
                int y = num / 10;
                if (gp.b.getFromBoardPlayer(x, y) <= 0) {
                    computerChooseX = x;
                    computerChooseY = y;
                    checkChooseSuccess = true;
                    if (gp.b.getFromBoardPlayer(x, y) < 0) {
                        numHit = num;
                    }
                } else {
                    num++;
                }
                System.out.println("2  " + num);
            }
        }
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
