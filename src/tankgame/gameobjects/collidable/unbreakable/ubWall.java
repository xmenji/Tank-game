package tankgame.gameobjects.collidable.unbreakable;

import fx.Assets;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.breakable.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.imageio.ImageIO.read;

public class ubWall extends GameObject {
    private BufferedImage img;
    private boolean isDestroyed;


    public ubWall(int x, int y){
        super(x, y);
        img = Assets.ubWall;
        this.rect = new Rectangle(x,y, img.getWidth(), img.getHeight());
        isDestroyed = false;
    }
    @Override
    public void update() {

    }

    @Override
    public void checkCollision(GameObject obj) {

        if(obj instanceof Tank){
            if(obj.getRect().intersects(this.getRect())){

                int top = this.getRectY();
                int objBottom = obj.getRectY() + 40;

                int bottom = this.getRectY() + 32;

                if(objBottom >= top){ //check top
                    obj.setY(top - 75);
                }
                /*else if(obj.getRectY() <= bottom){ //check bottom
                    System.out.println("obj y: " + obj.getRectY() + "  wall y: " + bottom);
                    obj.setY(bottom);
                }*/
            }
        }
    }

    @Override
    public void takeHit() {

    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public BufferedImage getImg(){
        return img;
    }
    public void drawWall(int x, int y){


    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.img, x, y,null);
    }
}
