package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    GamePanel gp;
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
//            System.out.println("1");
            gp.b.shipSetUp = 1;
        }
        else if(e.getSource() == gp.ui.b2){
//            System.out.println("2");
            gp.b.shipSetUp = 2;
        }
        else if(e.getSource() == gp.ui.b3){
//            System.out.println("3");
            gp.b.shipSetUp = 3;
        }
        else if(e.getSource() == gp.ui.b4){
//            System.out.println("4");
            gp.b.shipSetUp = 4;
        }
        else if(e.getSource() == gp.ui.b5){
//            System.out.println("5");
            gp.b.shipSetUp = 5;
        }
        else if(e.getSource() == gp.ui.play){
            gp.gameState = gp.playState;
        }


    }


 }

