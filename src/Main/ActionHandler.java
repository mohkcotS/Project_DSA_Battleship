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
        if(gp.gameState == gp.setupState){
            setupState(e);
        }
    }

    public void setupState(ActionEvent e){
        if(e.getSource() == gp.ui.b1){
            gp.b.shipSetUp = gp.b.ship1;
        }
        else if(e.getSource() == gp.ui.b2){
            gp.b.shipSetUp = gp.b.ship2;
        }
        else if(e.getSource() == gp.ui.b3){
            gp.b.shipSetUp = gp.b.ship3;
        }
        else if(e.getSource() == gp.ui.b4){
            gp.b.shipSetUp = gp.b.ship4;
        }
        else if(e.getSource() == gp.ui.b5){
            gp.b.shipSetUp = gp.b.ship5;
        }
        else if(e.getSource() == gp.ui.play){
            if(gp.player.canPlay()){
                gp.gameState = gp.playState;
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
    public void setUpShip(){
        gp.player.ship.get(index).sort();
        gp.player.ship.get(index).setupStatusNo = gp.player.ship.get(index).checkValid();
        gp.ui.statusNo = gp.player.ship.get(index).setupStatusNo;
    }

 }

