package tankgame.gameobjects;

import tankgame.gameobjects.collidable.Collision;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject implements Collision {
    protected int   x,
                    y,
                    vx,
                    vy,
                    angle;
    protected BufferedImage img;
    protected Rectangle rect;
    private boolean isDestroyed;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public int getVx() {
        return vx;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public abstract void drawImage(Graphics g);

    public int getImgWidth(){
        return img.getWidth();
    }
    public int getImgHeight(){
        return img.getHeight();
    }

    public int getRectX(){
        return (int) rect.getX();
    }
    public int getRectY(){
        return (int) rect.getY();
    }

    public Rectangle getRect() {
        return rect;
    }
}
