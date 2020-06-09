package tankgame.gameobjects.collidable;

import fx.Assets;
import tankgame.GameWorld;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.breakable.Tank;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
    private BufferedImage img;
    private final int R = 5;


    public Bullet(Tank tank, int x, int y, int angle) {
        super(x, y);
        this.img = Assets.bullet;

        this.x = x;
        this.y = y;
        this.angle = angle;
        this.vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.rect = new Rectangle(x,y, img.getWidth(), img.getHeight());
    }

    @Override
    public void update() {
        x += vx;
        y += vy;
        rect.setLocation(x,y);

    }

    @Override
    public void checkCollision(GameObject obj) {
        //Bullet collides w/ object
        if (this.getRect().intersects(obj.getRect())){
            obj.takeHit();
        }
        //Check if Bullet hit map objects (specifically breakable walls)
        for(int i = 0; i < GameWorld.mapObjects.size()-1; i++){
            if(this.getRect().intersects(GameWorld.mapObjects.get(i).getRect())){
                GameWorld.mapObjects.get(i).takeHit();
                if(GameWorld.mapObjects.get(i).isDestroyed()){
                    GameWorld.mapObjects.remove(i);
                    //GameWorld.bulletsShot.remove(i);
                }
            }
        }
    }

    @Override
    public void takeHit() {

    }


    @Override
    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, x, y,null);

        //make rectangle visible on the screen
        g2d.setColor(Color.green);
        g2d.drawRect(this.getRectX(),this.getRectY(), img.getWidth(), img.getHeight());
    }
}
