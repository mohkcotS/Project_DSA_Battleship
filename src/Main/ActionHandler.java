package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    GamePanel gp;
    int index;
    public ActionHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gp.gameState == gp.openingState){
            openingState(e);
        }
        else if(gp.gameState == gp.setupState){
            setupState(e);
        }
        else if(gp.gameState == gp.finishState){
            finishState(e);
        }
    }

    public void openingState(ActionEvent e){
        if(e.getSource() == gp.ui.exit){
            System.exit(0);
        }
        else if(e.getSource() == gp.ui.newGame){
            gp.gameState = gp.setupState;
        }

    }

    public void setupState(ActionEvent e){
        if(e.getSource() == gp.ui.b1){
            gp.b.shipSetUp = gp.b.ship1;
            gp.ui.ship = gp.player.ship.get(0);
        }
        else if(e.getSource() == gp.ui.b2){
            gp.b.shipSetUp = gp.b.ship2;
            gp.ui.ship = gp.player.ship.get(1);
        }
        else if(e.getSource() == gp.ui.b3){
            gp.b.shipSetUp = gp.b.ship3;
            gp.ui.ship = gp.player.ship.get(2);
        }
        else if(e.getSource() == gp.ui.b4){
            gp.b.shipSetUp = gp.b.ship4;
            gp.ui.ship = gp.player.ship.get(3);
        }
        else if(e.getSource() == gp.ui.b5){
            gp.b.shipSetUp = gp.b.ship5;
            gp.ui.ship = gp.player.ship.get(4);
        }
        else if(e.getSource() == gp.ui.back){
            gp.gameState = gp.openingState;
        }
        else if(e.getSource() == gp.ui.play){
            if(gp.player.canPlay()){
                gp.gameState = gp.playState;
                gp.mouse.col1 = -1;
                gp.mouse.row1 = -1;
            }
        }
        if(e.getSource() != gp.ui.play){
        gp.mouse.index = gp.b.shipSetUp - 1;
        index = gp.b.shipSetUp - 1;
        setUpShip();
        }
        else {
            if(!gp.player.canPlay()){
                gp.ui.statusNo = 5;
            }
        }
    }

    public void finishState(ActionEvent e){
        if(e.getSource() == gp.ui.exit1){
            System.exit(0);
        }
        else if(e.getSource() == gp.ui.newGame1){
            gp.gameState = gp.openingState;
        }
    }
    public void setUpShip() {
        gp.player.ship.get(index).sort();
        gp.player.ship.get(index).setupStatusNo = gp.player.ship.get(index).checkValid();
        gp.ui.statusNo = gp.player.ship.get(index).setupStatusNo;
    }

 }

