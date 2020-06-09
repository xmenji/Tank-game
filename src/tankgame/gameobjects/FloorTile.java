package tankgame.gameobjects;

import fx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloorTile extends GameObject {
    private BufferedImage img; // w = 320 h = 240

    public FloorTile(int x, int y) {
        super(x, y);
        img = Assets.background;
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

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y,null);
    }
}
