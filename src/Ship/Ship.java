package Ship;

import Battle.Coordinate;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Ship {
    public String name;
    public int isDestroy = 0;
    public int setupStatusNo=3;
    public int size;
    public ArrayList<Coordinate> cor = new ArrayList<>();
    public Color c ;
    public String color;
    public BufferedImage img;

    public void sort(){
        cor.sort(Comparator.comparing(Coordinate::getX));
        cor.sort(Comparator.comparing(Coordinate::getY));
    }

    public void print(){
        System.out.println(cor.size());
    }

    public void addCoordinate(Coordinate c){
        cor.add(c);
    }

    public void removeCoordinate(Coordinate c){
       for(int i = 0; i<cor.size();i++){
           if(cor.get(i).getX() == c.getX() && cor.get(i).getY() == c.getY()){
               cor.remove(i);
               break;
           }
       }
    }

    public int checkValid(){
        int x = 0;
        int y = 0;
        if(cor.isEmpty() ){
            return 3;
        }
        for (int i =1 ;i<cor.size();i++){
            if(cor.get(i).getX() - cor.get(i-1).getX() > 1 || cor.get(i).getY() - cor.get(i-1).getY() > 1){
                return 2;
            }

            if(cor.get(i).getX() - cor.get(i-1).getX() > 0){
                x = 1;
            }
            if(cor.get(i).getY() - cor.get(i-1).getY() > 0){
                y = 1;
            }
            if(x == 1 && y == 1){
                return 2;
            }
        }
        if(cor.size() != size){
            return  1;
        }
        return 0;
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO
                    .read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
