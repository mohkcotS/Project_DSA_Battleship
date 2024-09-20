package Main;
import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font1,font1a,font1b,font1c,font2;

    String [] status;
    int timer = 0;

    public int statusNo=3;
    public String playerStatus = "";
    public String computerStatus = "";
    Button b1,b2,b3,b4,b5,play;
    public UI(GamePanel gp){
        this.gp = gp;
        font1 = new Font("Times New Roman",Font.BOLD,15);
        font1a = new Font("Times New Roman",Font.BOLD,20);
        font1b = new Font("Times New Roman",Font.BOLD,25);
        font1c = new Font("Times New Roman",Font.PLAIN,20);
        font2 = new Font("Times New Roman",Font.BOLD,40);
        b1 = new Button("Ship 1");
        b2 = new Button("Ship 2");
        b3 = new Button("Ship 3");
        b4 = new Button("Ship 4");
        b5 = new Button("Ship 5");
        play = new Button("PLAY");

        gp.add(b1);
        gp.add(b2);
        gp.add(b3);
        gp.add(b4);
        gp.add(b5);
        gp.add(play);

        this.status = new String[10];
        setupStatus();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        if(gp.gameState != gp.setupState){
            gp.remove(b1);
            gp.remove(b2);
            gp.remove(b3);
            gp.remove(b4);
            gp.remove(b5);
            gp.remove(play);
        }

        if(gp.gameState == gp.setupState){
            drawSetUpBoard();
        }
        else if(gp.gameState == gp.playState){
            drawGamePlay();
        }
    }

    public void drawSetUpBoard(){

        //Main Border for 3 Area
        g2.setColor(new Color(0x1B4160));
        g2.fillRect(0,0,960,576);
        g2.setColor(new Color(0x000000));
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(2,2,956,572);
        g2.drawLine(0,10*gp.tileSize,20*gp.tileSize,10*gp.tileSize);


        // Player Board
        drawPlayerBoard();

        //Set up area
        int x = gp.tileSize * 45/4;
        int y = gp.tileSize *2;
        int x1 = gp.tileSize * 45/4;
        int y1 = gp.tileSize *27/4;
        g2.setColor(new Color(0xFFE4E8));
        g2.fillRect(x,y,gp.tileSize*15/2,gp.tileSize*9/2);
        g2.fillRect(x1,y1,gp.tileSize*15/2,gp.tileSize*11/4);
        g2.setColor(new Color(0x000000));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x,y,gp.tileSize*15/2,gp.tileSize*9/2,15,15);
        g2.drawRoundRect(x1,y1,gp.tileSize*15/2,gp.tileSize*11/4,15,15);

        //Title
        g2.setFont(font2);
        g2.setColor(new Color(0x90EE90));
        g2.drawString("Player",center("Player",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Set up Ships",center("Set up Ships",gp.tileSize*10,gp.tileSize*10),gp.tileSize);
        g2.setFont(font1b);
        g2.setColor(Color.BLACK);
        g2.drawString("Information",center("Information",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*21/8);
        g2.drawString("Status",center("Status",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*59/8);

        //Status
        g2.setFont(font1a);
        if(statusNo == 0 || statusNo == 6){
            g2.setColor(Color.GREEN);
            g2.drawString("VALID",center("VALID",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*8);
        }
        else{
            g2.setColor(Color.RED);
            g2.drawString("INVALID",center("INVALID",gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*8);
        }
        g2.setFont(font1);
        g2.setColor(Color.BLACK);
        g2.drawString(status[statusNo],center(status[statusNo],gp.tileSize*45/4,gp.tileSize*15/2),gp.tileSize*69/8);

        //Button
        drawButton();
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
                g2.setColor(new Color(0xFFFFE0));
                if(gp.b.getFromBoardPlayer(j,i) == -1){
                    g2.setColor(new Color(0xB4FFB4));
                }

                if(gp.b.getFromBoardPlayer(j,i) == -2){
                    g2.setColor(new Color(0xA0DBFF));
                }

                if(gp.b.getFromBoardPlayer(j,i) == -3){
                    g2.setColor(new Color(0xFFC09C));
                }

                if(gp.b.getFromBoardPlayer(j,i) == -4){
                    g2.setColor(new Color(0xFF7C7C));
                }

                if(gp.b.getFromBoardPlayer(j,i) == -5){
                    g2.setColor(new Color(0xFF5BEC));
                }

                g2.fillRect(x,y,size,size);
                g2.setColor(Color.black);
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
                g2.setFont(font1b);
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
                g2.setFont(font1b);
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

    public void drawButton(){
        int x = gp.tileSize;
        int y = gp.tileSize*21/2;
        int width = gp.tileSize * 2;
        int height = gp.tileSize;

        b1.addActionListener(gp.action);
        b1.setBounds(x,y,width,height);
        x += gp.tileSize * 5/2;

        b2.addActionListener(gp.action);
        b2.setBounds(x,y,width,height);
        x += gp.tileSize * 5/2;

        b3.addActionListener(gp.action);
        b3.setBounds(x,y,width,height);
        x += gp.tileSize * 5/2;

        b4.addActionListener(gp.action);
        b4.setBounds(x,y,width,height);
        x += gp.tileSize * 5/2;

        b5.addActionListener(gp.action);
        b5.setBounds(x,y,width,height);
        x += gp.tileSize * 9/2;

        play.addActionListener(gp.action);
        play.setFont(font1b);
        play.setBounds(x,y,width*2,height);
    }

    public void drawGamePlay(){
        //Background
        g2.setColor(new Color(0x1B4160));
        g2.fillRect(0,0,960,576);
        //Information
        displayInformationPlayState();
        //Select2
        drawSelectionTurn2();
        //Title
        g2.setFont(font2);
        g2.setColor(new Color(0x90EE90));
        g2.drawString("Player",center("Player",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Computer",center("computer",gp.tileSize*10,gp.tileSize*10),gp.tileSize);
        //Player Board
        drawPlayerBoard();
        //Computer Board
        drawComputerBoard();
        //Border
        drawPlayStateBorder();
        //Select1
        drawSelectionTurn1();
    }

    public void drawPlayStateBorder(){
        //Background and area
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(2,2,956,572);

        g2.drawLine(0,10*gp.tileSize,20*gp.tileSize,10*gp.tileSize);
        g2.drawLine(10*gp.tileSize,0,10*gp.tileSize,12*gp.tileSize);
    }
    public void displayInformationPlayState(){
        //Header
        g2.setColor(Color.WHITE);
        g2.setFont(font1c);
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
        g2.drawString(playerStatus, gp.tileSize * 8, gp.tileSize * 43 / 4);
        g2.drawString(computerStatus, 10+gp.tileSize * 18, gp.tileSize * 43 / 4);

        //Play/Computer's position choose
        g2.setFont(font1b);
        g2.setColor(new Color(0xFFFF00));
        if(gp.mouse.colPlayerChoose != -1 && gp.mouse.rowPlayerChoose != -1 ) {
            g2.drawString(""+((char)(gp.mouse.colPlayerChoose + 65)) + gp.mouse.rowPlayerChoose, gp.tileSize*7/2, 2+gp.tileSize * 43 / 4);
        }
        if(gp.mouse.colComputerChoose != -1 && gp.mouse.rowComputerChoose != -1 ) {
            g2.drawString(""+((char)(gp.mouse.colComputerChoose + 65)) + gp.mouse.rowComputerChoose, gp.tileSize * 14, 2+gp.tileSize * 43 / 4);
        }
    }

    public void drawComputerBoard(){
        int x = gp.tileSize * 45/4;
        int y = gp.tileSize * 2;
        int size = gp.tileSize * 3/4;
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                g2.setColor(new Color(0xFFFFE0));
                if(gp.b.getFromBoardPlayer(j,i) == 1){
                    g2.setColor(Color.GREEN);
                }
                g2.fillRect(x,y,size,size);
                g2.setColor(Color.black);
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
        g2.setColor(new Color(0xFFA500));
        g2.setStroke(new BasicStroke(5));
        if(gp.timer == 0){
            gp.b.turn = gp.b.playerTurn;
            g2.drawRect(2,gp.tileSize*10,gp.tileSize*10 -2 ,gp.tileSize*2 -4 );
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(2,2,gp.tileSize*10,gp.tileSize*10 - 4);

            g2.setColor(Color.white);
            g2.setFont(font2);
            g2.drawString("Player Turn",center("Player Turn",0,gp.tileSize*10),gp.tileSize*5);
        }
        else {
            gp.b.turn = gp.b.computerTurn;
            g2.drawRect(gp.tileSize*10,gp.tileSize*10,gp.tileSize*10 -2 ,gp.tileSize*2 -4 );
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(gp.tileSize*10,2,gp.tileSize*10,gp.tileSize*10 - 4);

            g2.setColor(Color.white);
            g2.setFont(font2);
            g2.drawString("Computer Turn",center("Computer Turn",gp.tileSize*10,gp.tileSize*10),gp.tileSize*5);
        }
    }

    public void drawSelectionTurn2(){
        if(gp.timer == 0){
            gp.b.turn = gp.b.playerTurn;
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(gp.tileSize*10,2,gp.tileSize*10,gp.tileSize*10 - 4);
        }
        else {
            gp.b.turn = gp.b.computerTurn;
            g2.setColor(new Color(0,0,0,200));
            g2.fillRect(2,2,gp.tileSize*10,gp.tileSize*10 - 4);

        }
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

    public int center(String s, int x ,  int size) {
        int textWidth = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return x + (size - textWidth) / 2;
    }


}
