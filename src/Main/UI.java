package Main;
import Ship.Ship;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font1,font2,font2a,font3,font4,font5,font6;
    BufferedImage hit,miss,bg,open;
    public String destroyStatus;

    ArrayList<Button> buttonSetUpState = new ArrayList<>();
    ArrayList<Button> buttonOpeningState = new ArrayList<>();
    ArrayList<Button> buttonFinishState = new ArrayList<>();

    String [] status;
    int timer = 0;
    Ship ship;
    public int statusNo=3;
    public String playerStatus = "";
    public String computerStatus = "";
    Button b1,b2,b3,b4,b5,play,newGame,instruction,exit,back,newGame1,exit1;

    int isAddOpeningStateButton = 0;
    int isAddSetUpStateButton = 0;
    int isAddFinishStateButton = 0;

    public int finish = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        addFont();
        this.status = new String[10];
        setupStatus();
        setUpImage();
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        if(gp.gameState == gp.openingState){
            if(isAddFinishStateButton == 1){
                removeButtonFinishState();
            }
            if(isAddSetUpStateButton == 1){
                removeButtonSetUpState();
            }
            if(isAddOpeningStateButton == 0) {
                addButtonOpeningState();
            }
            drawOpeningState();
        }
        else if(gp.gameState == gp.setupState){
            removeButtonOpeningState();

            if(isAddSetUpStateButton == 0){
                addButtonSetUpState();
            }
            drawSetUpBoard();
        }
        else if(gp.gameState == gp.playState){
            removeButtonSetUpState();
            drawGamePlay();
        }
        else if(gp.gameState == gp.finishState){
            if(isAddFinishStateButton == 0){
                addButtonFinishState();
            }
            drawFinishState();
        }
    }

    public void drawOpeningState(){

        g2.drawImage(open,0,0,gp.screenWidth,gp.screenHeight,null);
        g2.setColor(new Color(0,0,0,50));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(new Color(0xFFFFFF));
        g2.setFont(font6);
        g2.drawString("Battle Ship",center("Battle Ship",0,gp.tileSize*20),gp.tileSize*2);
        drawButtonOpeningState();
    }

    public void drawButtonOpeningState(){
        drawButton(gp.tileSize*15/2,gp.tileSize*7/2,new Color(0x202020),new Color(0xFFD154),new Color(0x002795),buttonOpeningState,3);
    }

    public void drawSetUpBoard(){

        //Main Border for 3 Area
        g2.drawImage(bg,0,0,gp.screenWidth,gp.screenHeight,null);
        g2.setColor(new Color(0,0,0,75));
        g2.fillRect(0,0,gp.screenWidth,gp.tileSize*10);

        g2.setColor(new Color(0,0,0,180));
        g2.fillRect(0,gp.tileSize*10,gp.tileSize*20,gp.tileSize*2);
        g2.setColor(Color.WHITE);

        // Player Board
        drawPlayerBoard();

        //Set up area
        int x = gp.tileSize * 45/4;
        int y = gp.tileSize *2;
        int x1 = gp.tileSize * 45/4;
        int y1 = gp.tileSize *27/4;
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(x,y,gp.tileSize*15/2,gp.tileSize*9/2);
        g2.fillRect(x1,y1,gp.tileSize*15/2,gp.tileSize*11/4);
        g2.setColor(new Color(0xFFFFFF));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x,y,gp.tileSize*15/2,gp.tileSize*9/2,15,15);
        g2.drawRoundRect(x1,y1,gp.tileSize*15/2,gp.tileSize*11/4,15,15);

        //Title
        g2.setFont(font5);
        g2.setColor(new Color(0xFFFFFF));
        g2.drawString("Player",center("Player",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Set up Ships",center("Set up Ships",gp.tileSize*10,gp.tileSize*10),gp.tileSize);
        g2.setFont(font3);
        g2.setColor(Color.WHITE);
        g2.drawString("Information",center("Information",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*21/8);
        g2.drawString("Status",center("Status",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*59/8);
        //Information
        drawShipInformation();

        //Status
        g2.setFont(font2);
        if(statusNo == 0 || statusNo == 6){
            g2.setColor(new Color(0x39FF14));
            g2.drawString("VALID",center("VALID",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*8);
        }
        else{
            g2.setColor(new Color(0xFF073A));
            g2.drawString("INVALID",center("INVALID",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*8);
        }
        g2.setFont(font1);
        g2.setColor(Color.WHITE);
        g2.drawString(status[statusNo],center(status[statusNo],gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*69/8);

        //Button
        drawButtonSetUpState();
        //check can play
        if(gp.player.canPlay()){
            statusNo = 6;
        }
    }

    public void drawPlayerBoard(){
        int x = gp.tileSize * 5/4;
        int y = gp.tileSize * 2;
        int size = gp.tileSize * 3/4;
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                int num = gp.b.getFromBoardPlayer(j,i);
                drawChosen(num,x,y,0);
                g2.setColor(new Color(0xFFFFFF));
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(x,y,size,size);

                x+= size;
            }
            y+= size;
            x = gp.tileSize * 5/4;
        }

        x = gp.tileSize * 5/4;
        y = gp.tileSize * 15/8;
        for(int i = 65; i<= 74; i++){
            if(gp.mouse.col1 == i-65){
                g2.setFont(font3);
                g2.setColor(new Color(0xFFD700));
            }
            else{
                g2.setFont(font1);
                g2.setColor(Color.white);
            }
            String s = String.valueOf((char)i);
            g2.drawString(s,center(s,x,size),y);
            x += size;
        }

        x = gp.tileSize*3/4;
        y = gp.tileSize * 5/2;
        for(int i = 0; i<= 9; i++){
            if(gp.mouse.row1 == i){
                g2.setFont(font3);
                g2.setColor(new Color(0xFFD700));
            }
            else{
                g2.setFont(font1);
                g2.setColor(Color.white);
            }
            g2.drawString(i+"",x,y);
            y+= size;
        }
    }

    public void drawButtonSetUpState(){
        isChoose();
        int x = gp.tileSize;
        int y = gp.tileSize*21/2;
        int width = gp.tileSize * 2;
        int height = gp.tileSize;

        for(int i = 0; i<5;i++){
            buttonSetUpState.get(i).addActionListener(gp.action);
            buttonSetUpState.get(i).setBounds(x,y,width,height);
            x += gp.tileSize * 5/2;
        }

        x += gp.tileSize*2;
        play.addActionListener(gp.action);
        play.setFont(font3);
        play.setBounds(x,y,width*2,height);

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        back.addActionListener(gp.action);
        back.setFont(font2);
        back.setBounds(x,y,width,height/2);
    }

    public void drawShipInformation(){
        g2.setColor(Color.WHITE);
        g2.setFont(font3);
        g2.drawString(ship.name,center(ship.name,gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*7/2);
        g2.setFont(font2a);
        g2.drawString("Size",gp.tileSize*15,gp.tileSize*19/4);
        g2.drawString("Color",gp.tileSize*15,gp.tileSize*21/4);
        g2.drawString(":  "+ship.size,gp.tileSize*16,gp.tileSize*19/4);
        g2.drawString(":  "+ship.color,gp.tileSize*16,gp.tileSize*21/4);
        g2.setStroke(new BasicStroke(3));
        g2.drawImage(ship.img,gp.tileSize*12,gp.tileSize*4,gp.tileSize*2,gp.tileSize*2,null);
        g2.drawRoundRect(gp.tileSize*12,gp.tileSize*4,gp.tileSize*2,gp.tileSize*2,10,10);
    }

    public void drawGamePlay(){
        //Background
        g2.drawImage(bg,0,0,gp.screenWidth,gp.screenHeight,null);
        g2.setColor(new Color(0,0,0,75));
        g2.fillRect(0,0,gp.screenWidth,gp.tileSize*10);
        g2.setColor(new Color(0,0,0,180));
        g2.fillRect(0,gp.tileSize*10,gp.tileSize*20,gp.tileSize*2);
        //Information
        displayInformationPlayState();
        //Title
        g2.setFont(font5);
        g2.setColor(Color.WHITE);
        g2.drawString("Player Board",center("Player Board",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Computer Board",center("computer Board",gp.tileSize*10,gp.tileSize*10),gp.tileSize);
        //Player Board
        drawPlayerBoard();
        //Computer Board
        drawComputerBoard();
        //Select1
        drawSelectionTurn1();
        //Border
        drawPlayStateBorder();
    }

    public void drawPlayStateBorder(){
        //Background and area
        g2.setColor(new Color(255,255,255,200));
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(0,10*gp.tileSize,20*gp.tileSize,10*gp.tileSize);
        g2.drawLine(10*gp.tileSize,0,10*gp.tileSize,12*gp.tileSize);
    }
    public void displayInformationPlayState(){
        //Header
        g2.setColor(Color.white);
        g2.setFont(font4);
        g2.drawString("Player choose: ", gp.tileSize / 2, gp.tileSize * 43 / 4);
        g2.drawString("Computer choose: ", gp.tileSize * 21 / 2, gp.tileSize * 43 / 4);
        g2.drawString("Status: ", gp.tileSize * 6, gp.tileSize * 43 / 4);
        g2.drawString("Status: ", gp.tileSize * 16, gp.tileSize * 43 / 4);
        g2.drawString("Notification:    ", gp.tileSize /2, gp.tileSize * 23 / 2);
        g2.drawString("Notification:    ", gp.tileSize * 21/2, gp.tileSize * 23 / 2);
        if(!gp.mouse.check){
            timer++;
            if(timer != 0){
            g2.drawString("Can not choose this position", gp.tileSize * 3, gp.tileSize * 23 / 2);}
            if(timer == 100){
                timer = 0;
                gp.mouse.check = true;
            }
        }
        g2.setColor(new Color(0x32FF00));
        if(gp.computer.check){
            timer++;
            if(timer != 0){
            g2.drawString("Computer's "+destroyStatus+" is destroyed", gp.tileSize * 3, gp.tileSize * 23 / 2);}
            if(timer == 500){
                timer = 0;
                gp.computer.check = false;
            }
        }
        if(gp.player.check){
            timer++;
            if(timer != 0){
            g2.drawString("Player's "+destroyStatus+" is destroyed", gp.tileSize * 13, gp.tileSize * 23 / 2);}
            if(timer == 500){
                timer = 0;
                gp.player.check = false;
            }
        }
        g2.setColor(new Color(0x00FFCC));
        g2.drawString(playerStatus, gp.tileSize * 8, gp.tileSize * 43 / 4);
        g2.drawString(computerStatus, 10+gp.tileSize * 18, gp.tileSize * 43 / 4);

        //Play/Computer's position choose
        g2.setFont(font3);
        g2.setColor(new Color(0xFFFF00));
        checkHitOrMiss();
        if(gp.mouse.colPlayerChoose != -1 && gp.mouse.rowPlayerChoose != -1 ) {
            g2.drawString(""+((char)(gp.mouse.colPlayerChoose + 65)) + gp.mouse.rowPlayerChoose, gp.tileSize*7/2, 2+gp.tileSize * 43 / 4);
        }
        if(gp.computer.computerChooseX != -1 && gp.computer.computerChooseY != -1){
            g2.drawString(""+((char)(gp.computer.computerChooseX + 65)) + gp.computer.computerChooseY, gp.tileSize*14, 2+gp.tileSize * 43 / 4);
        }
    }

    public void drawComputerBoard(){
        int x = gp.tileSize * 45/4;
        int y = gp.tileSize * 2;
        int size = gp.tileSize * 3/4;
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                int num = gp.b.getFromBoardComputer(j,i);
                drawChosen(num,x,y,1);
                g2.setColor(Color.white);
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(x,y,size,size);
                x+= size;
            }
            y+= size;
            x = gp.tileSize * 45/4;
        }

        g2.setColor(Color.white);
        g2.setFont(font1);
        x = gp.tileSize * 45/4;
        y = gp.tileSize * 15/8;
        for(int i = 65; i<= 74; i++){
            String s = String.valueOf((char)i);
            g2.drawString(s,center(s,x,size),y);
            x += size;
        }

        x = gp.tileSize * 43/4;
        y = gp.tileSize * 5/2;
        for(int i = 0; i<= 9; i++){
            g2.drawString(i+"",x,y);
            y+= size;
        }
    }

    public void drawSelectionTurn1(){
        g2.setColor(new Color(0,0,0,0));
        g2.setStroke(new BasicStroke(5));
        if(gp.b.turn == gp.b.playerTurn){
            g2.drawRect(0,gp.tileSize*10,gp.tileSize*10 ,gp.tileSize*2 );
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(0,0,gp.tileSize*10,gp.tileSize*10);

            g2.setColor(Color.white);
            g2.setFont(font5);
            g2.drawString("Player Turn",center("Player Turn",0,gp.tileSize*10),gp.tileSize*5);
        }
        else {
            g2.drawRect(gp.tileSize*10,gp.tileSize*10,gp.tileSize*10,gp.tileSize*2);
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(gp.tileSize*10,0,gp.tileSize*10,gp.tileSize*10);

            g2.setColor(Color.white);
            g2.setFont(font5);
            g2.drawString("Computer Turn",center("Computer Turn",gp.tileSize*10,gp.tileSize*10),gp.tileSize*5);
        }
    }

    public void drawFinishState(){
        drawGamePlay();
        g2.setColor(new Color(0,0,0,230));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        String text;
        if(finish == 0){
            text = "PLAYER WIN";
        }
        else {
            text = "COMPUTER WIN";
        }
        g2.setColor(new Color(0x98FF98));
        g2.setFont(font6);
        g2.drawString(text,center(text,0,gp.screenWidth),gp.tileSize*5/2);
        drawButtonFinishState();
    }
    
    public void drawButtonFinishState(){
        drawButton(gp.tileSize*15/2,gp.tileSize*11/2,new Color(0xFFFFCC),new Color(0xFFD154),new Color(0x002795),buttonFinishState,2);
    }

    public void setupStatus(){
        status[0] = "Set up successfully";
        status[1] = "Unsuitable size";
        status[2] = "Invalid position";
        status[3] = "Ship's position has not been chosen";
        status[4] = "Can not choose this position";
        status[5] = "Incomplete set up ships";
        status[6] = "All set up are completed";

    }

    public void checkHitOrMiss(){
        if(gp.mouse.colPlayerChoose!= -1 && gp.mouse.rowPlayerChoose != -1 && gp.b.getFromBoardComputer(gp.mouse.colPlayerChoose,gp.mouse.rowPlayerChoose) == 6){
            gp.ui.playerStatus = "Miss";
        }
        else if(gp.mouse.colPlayerChoose!= -1 && gp.mouse.rowPlayerChoose != -1){
            gp.ui.playerStatus = "Hit";
        }

        if(gp.computer.computerChooseX != -1 && gp.computer.computerChooseY != -1 && gp.b.getFromBoardPlayer(gp.computer.computerChooseX,gp.computer.computerChooseY) == 6){
            gp.ui.computerStatus = "Miss";
        }
        else if (gp.computer.computerChooseX != -1 && gp.computer.computerChooseY != -1){
            gp.ui.computerStatus = "Hit";
        }
    }

    public int center(String s, int x ,  int size) {
        int textWidth = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return x + (size - textWidth) / 2;
    }
    public void addFont(){
        font1 = new Font("Times New Roman",Font.BOLD,15);
        font2 = new Font("Times New Roman",Font.BOLD,20);
        font2a = new Font("Times New Roman",Font.PLAIN,20);
        font3 = new Font("Times New Roman",Font.BOLD,25);
        font4 = new Font("Times New Roman",Font.PLAIN,20);
        font5 = new Font("Times New Roman",Font.BOLD,40);
        font6 = new Font("Times New Roman",Font.BOLD,80);
    }
    public void addButtonOpeningState(){
        newGame = new Button("New Game");
        instruction = new Button("Instruction");
        exit = new Button("Exit");

        gp.add(newGame);
        gp.add(instruction);
        gp.add(exit);

        buttonOpeningState.add(newGame);
        buttonOpeningState.add(instruction);
        buttonOpeningState.add(exit);

        isAddOpeningStateButton = 1;

    }

    public void removeButtonOpeningState(){
        gp.remove(newGame);
        gp.remove(instruction);
        gp.remove(exit);

        isAddOpeningStateButton = 0;

        buttonOpeningState.clear();
    }
    public void addButtonSetUpState(){
        b1 = new Button("Ship 1");
        b2 = new Button("Ship 2");
        b3 = new Button("Ship 3");
        b4 = new Button("Ship 4");
        b5 = new Button("Ship 5");
        play = new Button("PLAY");
        back = new Button("Back");

        gp.add(b1);
        gp.add(b2);
        gp.add(b3);
        gp.add(b4);
        gp.add(b5);
        gp.add(play);
        gp.add(back);

        buttonSetUpState.add(b1);
        buttonSetUpState.add(b2);
        buttonSetUpState.add(b3);
        buttonSetUpState.add(b4);
        buttonSetUpState.add(b5);
        buttonSetUpState.add(play);
        buttonSetUpState.add(back);

        isAddSetUpStateButton = 1;

    }
    public void removeButtonSetUpState(){
        gp.remove(b1);
        gp.remove(b2);
        gp.remove(b3);
        gp.remove(b4);
        gp.remove(b5);
        gp.remove(play);
        gp.remove(back);

        buttonSetUpState.clear();
        isAddSetUpStateButton = 0;
    }

    public void addButtonFinishState(){
        newGame1 = new Button("Menu");
        exit1 = new Button("Exit");

        gp.add(newGame1);
        gp.add(exit1);

        buttonFinishState.add(newGame1);
        buttonFinishState.add(exit1);

        isAddFinishStateButton = 1;
    }

    public void removeButtonFinishState(){
        gp.remove(newGame1);
        gp.remove(exit1);

        buttonFinishState.clear();
        isAddFinishStateButton = 0;
    }

    public void drawChosen(int num, int x, int y, int index) {
        if (index == 1) {
            if (num == 6) {
                g2.drawImage(miss, x, y, 36, 36, null);
            } else {
                if (num == 1 && gp.computer.ship.get(0).cor.isEmpty()) {
                    drawDestroyedShip(x,y);
                } else if (num == 2 && gp.computer.ship.get(1).cor.isEmpty()) {
                    drawDestroyedShip(x,y);
                } else if (num == 3 && gp.computer.ship.get(2).cor.isEmpty()) {
                    drawDestroyedShip(x,y);
                } else if (num == 4 && gp.computer.ship.get(3).cor.isEmpty()) {
                    drawDestroyedShip(x,y);
                } else if (num == 5 && gp.computer.ship.get(4).cor.isEmpty()) {
                    drawDestroyedShip(x,y);
                } else if (num <= 0) {
                    g2.setColor(new Color(0,0,0,0));
                    g2.fillRect(x, y, 36, 36);
                } else {
                    g2.drawImage(hit, x, y, 36, 36, null);
                }
            }
        }
        else {
            if (num == 0 || num == 6) {
                g2.setColor(new Color(255, 255, 255, 0));
            }
            else if(num == 1 || num == -1) {
                if(gp.player.ship.get(0).cor.isEmpty()){
                    g2.setColor(new Color(0,0,0,180));
                }
                else {
                    g2.setColor(gp.player.ship.get(0).c);
                }
            }
            else if(num == 2 || num == -2) {
                if(gp.player.ship.get(1).cor.isEmpty()){
                    g2.setColor(new Color(0,0,0,180));
                }
                else {
                    g2.setColor(gp.player.ship.get(1).c);
                }
            }
            else if(num == 3 || num == -3) {
                if(gp.player.ship.get(2).cor.isEmpty()){
                    g2.setColor(new Color(0,0,0,180));
                }
                else {
                    g2.setColor(gp.player.ship.get(2).c);
                }
            }
            else if(num == 4 || num == -4) {
                if(gp.player.ship.get(3).cor.isEmpty()){
                    g2.setColor(new Color(0,0,0,180));
                }
                else {
                    g2.setColor(gp.player.ship.get(3).c);
                }
            }
            else if(num == 5 || num == -5) {
                if (gp.player.ship.get(4).cor.isEmpty()) {
                    g2.setColor(new Color(0, 0, 0, 180));
                } else {
                    g2.setColor(gp.player.ship.get(4).c);
                }
            }
            g2.fillRect(x, y, 36, 36);

            if (num == 6)  {
                g2.drawImage(miss, x, y, 36, 36, null);
            }
            else if(num > 0){
                g2.drawImage(hit,x,y,36,36,null);
            }
        }
    }

    public void isChoose(){
        for(int i = 0; i<5;i++){
            buttonSetUpState.get(i).setBackGround(i == gp.b.shipSetUp - 1);
        }
    }

    public void  setUpImage(){
        hit = setup("Choose/Hit",gp.tileSize*3/4,gp.tileSize*3/4);
        miss = setup("Choose/Miss",gp.tileSize*3/4,gp.tileSize*3/4);
        bg = setup("Background/bg1",gp.screenWidth,gp.screenHeight);
        open = setup("Background/bg2",gp.screenWidth,gp.screenHeight);
    }

    public void drawDestroyedShip(int x, int y){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(x, y, 36, 36);
        g2.drawImage(hit, x, y, 36, 36, null);
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            System.out.println("No image");
        }
        return image;
    }
    
    public void drawButton(int x,int y, Color border,Color bg, Color text,ArrayList<Button> l,int num){
        Border b = BorderFactory.createLineBorder(border,5);
        for(int i =0 ;i <num;i++){
            l.get(i).setBackground(bg);
            l.get(i).setForeground(text);
            l.get(i).addActionListener(gp.action);
            l.get(i).setBorderPainted(true);
            l.get(i).setBorder(b);
            l.get(i).setBounds(x,y,gp.tileSize*5,gp.tileSize*3/2);
            l.get(i).setFont(font3);
            y += gp.tileSize*5/2;
        }
    }

}
