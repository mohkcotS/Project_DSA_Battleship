package Battle;
import Main.GamePanel;

public class Board {
    private final int [][] boardPlayer;
    private final int [][] boardComputer;


    public int turn = 0;
    public int playerTurn = 0;
    public int computerTurn = 1;

    public int shipSetUp;
    public final int ship1 = 1;
    public final int ship2 = 2;
    public final int ship3 = 3;
    public final int ship4 = 4;
    public final int ship5 = 5;

    GamePanel gp;

    public Board(GamePanel gp){
        this.gp = gp;
        this.boardPlayer = new int[10][10];
        this.boardComputer = new int[10][10];
    }

   public void setUpBoardPlayer(int col, int row, int value){
        this.boardPlayer[col][row] = value;
   }

   public int getFromBoardPlayer(int col, int row){
        return boardPlayer[col][row];
   }
   public int getFromBoardComputer(int col, int row){
        return boardComputer[col][row];
   }

   public void setPlayerBoard(int col, int row,int value){
        boardPlayer[col][row] = value;
   }
}
