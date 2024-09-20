package Main;

import Battle.Board;
import Controller.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;    //960
    final int screenHeight = tileSize * maxScreenRow;   //576
    public UI ui = new UI(this);
    public Board b = new Board (this);
    public Player player = new Player(this);
    public MouseHandler mouse = new MouseHandler(this);
    public ActionHandler action = new ActionHandler(this);

    public int timer;
    public boolean start = false;

    //GAMES STATES

    public int gameState;
    public final int openingState = 1;
    public final int setupState = 2;
    public final int playState = 3;

    public void setupGame(){
        gameState = setupState;
        b.shipSetUp = b.ship1;
        timer = 0;
    }


    Thread gameThread;
    int FPS = 60;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta > 1){
            //UPDATE
            update();
            //REPAINT
            repaint();
            delta--;
            }
        }
    }

    public void update(){
        if (start) {
            timer++;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        ui.draw(g2);
    }
}
