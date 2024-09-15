package Main;
import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font1,font1a,font2;

    Button b1,b2,b3,b4,b5,play;
    public UI(GamePanel gp){
        this.gp = gp;
        font1 = new Font("Times New Roman",Font.BOLD,15);
        font1a = new Font("Times New Roman",Font.BOLD,25);
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
        g2.setColor(new Color(0x003366));
        g2.fillRect(0,0,960,576);
        g2.setColor(new Color(0x000000));
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(2,2,956,572);
        g2.drawLine(0,10*gp.tileSize,20*gp.tileSize,10*gp.tileSize);


        // Player Board
        drawPlayerBoard();

        //Title
        g2.setFont(font2);
        g2.setColor(new Color(0x90EE90));
        g2.drawString("Player",center("Player",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Set up Ships",center("Set up Ships",gp.tileSize*10,gp.tileSize*10),gp.tileSize);

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

        //Button
        drawButton();
    }
    public void drawGamePlay(){
        g2.setColor(new Color(0x003366));
        g2.fillRect(0,0,960,576);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(2,2,956,572);

        g2.drawLine(0,10*gp.tileSize,20*gp.tileSize,10*gp.tileSize);
        g2.drawLine(10*gp.tileSize,0,10*gp.tileSize,10*gp.tileSize);


        int x = gp.tileSize * 5/4;
        int x1 = gp.tileSize * 45/4;
        int y = gp.tileSize * 2;
        int size = gp.tileSize * 3/4;
        for(int i = 0; i<10;i++){
            for(int j = 0; j<10;j++){
                g2.setColor(new Color(0xFFFFE0));
                if(gp.b.getFromBoardPlayer(j,i) == 1){
                    g2.setColor(Color.GREEN);
                }
                g2.fillRect(x,y,size,size);
                g2.fillRect(x1,y,size,size);
                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(x,y,size,size);
                g2.drawRect(x1,y,size,size);
                x+= size;
                x1+= size;
            }
            y+= size;
            x = gp.tileSize * 5/4;
            x1 = gp.tileSize * 45/4;
        }

        g2.setColor(Color.white);
        g2.setFont(font1);
        x = gp.tileSize * 5/4;
        x1 = gp.tileSize * 45/4;
        y = gp.tileSize * 15/8;
        for(int i = 65; i<= 74; i++){
            String s = String.valueOf((char)i);
            g2.drawString(s,center(s,x,size),y);
            g2.drawString(s,center(s,x1,size),y);
            x += size;
            x1+= size;
        }

        x = gp.tileSize;
        x1 = gp.tileSize * 11;
        y = gp.tileSize * 5/2;
        for(int i = 0; i<= 9; i++){
            g2.drawString(i+"",x,y);
            g2.drawString(i+"",x1 ,y);
            y+= size;
        }

        g2.setFont(font2);
        g2.setColor(new Color(0x90EE90));
        g2.drawString("Player",center("Player",0,gp.tileSize*10),gp.tileSize);
        g2.drawString("Computer",center("computer",gp.tileSize*10,gp.tileSize*10),gp.tileSize);



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
                g2.setFont(font1a);
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
                g2.setFont(font1a);
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
        play.setFont(font1a);
        play.setBounds(x,y,width*2,height);

    }
    public int center(String s, int x ,  int size) {
        int textWidth = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return x + (size - textWidth) / 2;
    }


}
