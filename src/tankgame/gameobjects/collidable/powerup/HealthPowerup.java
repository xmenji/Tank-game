package tankgame.gameobjects.collidable.powerup;

import fx.Assets;
import tankgame.gameobjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HealthPowerup extends Powerup {
    BufferedImage img;
    private boolean isDestroyed;
    private boolean pickedUp;
    private int index;

    public HealthPowerup(int x, int y) {
        super(x, y);
        img = Assets.powerup;
        this.rect = new Rectangle(x,y,img.getWidth(),img.getHeight());
        isDestroyed = false;
        pickedUp = false;
        index = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void checkCollision(GameObject obj) {

    }

    @Override
    public void takeHit() {

    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y,null);
    }
}
