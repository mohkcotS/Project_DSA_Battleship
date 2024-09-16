package Main;


import Battle.Coordinate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    GamePanel gp;
    int col,row,col1=-1,row1=-1;
    int index; // shipSetup - 1
    int computerRow, computerCol;
    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setUpBoardPlayer(e);
        setUpShip();

    }

//        if(e.getX() >= 540 &&e.getX() <= 900 && e.getY() >= 96 && e.getY() <= 456 ){
//        computerCol = (e.getX() - 540)/36;
//        computerRow =(e.getY() - 96)/36;
//        System.out.println("Computer: "+(char)(computerCol+65)+""+computerRow);
//
//        }
//    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position(e);
    }

    public void setUpBoardPlayer(MouseEvent e){
        if(e.getX() >= 60 &&e.getX() <= 420 && e.getY() >= 96 && e.getY() <= 456 ){
            col = (e.getX() - 60)/36;
            row =(e.getY() - 96)/36;
        }
        if(gp.b.getFromBoardPlayer(col,row) == 0 && e.getX() >= 60 &&e.getX() <= 420 && e.getY() >= 96 && e.getY() <= 456 ){
            gp.b.setUpBoardPlayer(col,row, -gp.b.shipSetUp);
            gp.player.ship.get(index).addCoordinate(new Coordinate(col,row));
        }
        else if(gp.b.getFromBoardPlayer(col,row) == -gp.b.shipSetUp){
            gp.b.setUpBoardPlayer(col,row, 0);
            gp.player.ship.get(index).removeCoordinate(new Coordinate(col,row));
        }
    }

    public void setUpShip(){
        gp.player.ship.get(index).sort();
        gp.player.ship.get(index).setupStatusNo = gp.player.ship.get(index).checkValid();
        gp.ui.statusNo = gp.player.ship.get(index).setupStatusNo;
        if (gp.b.getFromBoardPlayer(col,row) != -gp.b.shipSetUp &&gp.b.getFromBoardPlayer(col,row) != 0) {
            gp.ui.statusNo = 4;
        }
    }



    public void position(MouseEvent e){
        if(e.getX() >= 60 &&e.getX() <= 420 && e.getY() >= 96 && e.getY() <= 456 ){
            col1 = (e.getX() - 60)/36;
            row1 =(e.getY() - 96)/36;
        }
        else{
            col1 = -1;
            row1 = -1;
        }
    }
}
