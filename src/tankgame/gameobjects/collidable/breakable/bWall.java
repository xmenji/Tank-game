package tankgame.gameobjects.collidable.breakable;

import fx.Assets;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class bWall extends GameObject {

    public static final double DEFAULT_STRENGTH = 100;

    private BufferedImage img;
    private double strength; //basically the wall's health
    private boolean isDestroyed;

    public bWall(int x, int y) {
        super(x, y);
        img = Assets.bWall;
        this.rect = new Rectangle(x,y, img.getWidth(), img.getHeight());
        strength = DEFAULT_STRENGTH;
        isDestroyed = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void checkCollision(GameObject obj) {

    }

    @Override
    public void takeHit() {
        if(strength == 0){
            isDestroyed = true;
        }
        else{
            strength -= 0.5;
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y,null);
    }
}
