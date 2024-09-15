package Battle;

import Controller.Player;
import Main.GamePanel;
import Ship.Ship;

public class Board {
    private int [][] boardPlayer;
    private int [][] boardComputer;

    public int shipSetUp;
    public final int ship1 = 1;
    public final int ship2 = 2;
    public final int ship3 = 3;
    public final int ship4 = 4;
    public final int ship5 = 5;

    public String [] status;
    GamePanel gp;

    public Board(GamePanel gp){
        this.gp = gp;
        this.boardPlayer = new int[10][10];
        this.boardComputer = new int[10][10];
        this.status = new String[10];
        setupStatus();
    }

   public void setUpBoardPlayer(int col, int row, int value){
        this.boardPlayer[col][row] = value;
   }

   public int getFromBoardPlayer(int col, int row){
        return boardPlayer[col][row];
   }

   public void checkSetup(int shipSetUp){


   }

   public void setupStatus(){
        status[0] = "Set up successfully";
        status[1] = "Unsuitable size";
        status[2] = "Invalid position";

    }

}
