package tankgame.gameobjects.collidable.breakable;


import tankgame.GameWorld;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.Bullet;
import tankgame.gameobjects.collidable.powerup.HealthPowerup;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author anthony-pc
 */
public class Tank extends GameObject {

    private static final int DEFAULT_LIVES = 3;
    private static final double DEFAULT_HEALTH = 100.0;

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private final int R = 2;
    private final int ROTATIONSPEED = 4;


    private double health;
    private int lives;

    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;

    private GameWorld game;

    public Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        super(x,y);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.health = DEFAULT_HEALTH;
        this.lives = DEFAULT_LIVES;
        this.rect = new Rectangle(x,y,img.getWidth(), img.getHeight());

    }


    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShootPressed() {this.shootPressed = true;}

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {this.shootPressed = false;}



    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.shootPressed){
            this.shoot();
        }
        rect.setLocation(x,y);

        //center camera on the tank
        //game.getCamera().followTank(this);

        for(int i = 0; i < GameWorld.healthPacks.size(); i++){
            if(this.getRect().intersects(GameWorld.healthPacks.get(i).getRect())){
                GameWorld.healthPacks.remove(i);

                health += 20;
            }
        }
    }

    public int getLives() {
        return lives;
    }

    public double getHealth() {
        return health;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void checkCollision(GameObject obj) {


    }

    @Override
    public void takeHit() {
        health -= 0.1;
    }

    private void shoot(){ // tank keeps firing 4+ bullets each time button is pressed
        Bullet bullet = new Bullet(this, this.x, this.y, this.angle);
        GameWorld.bulletsShot.add(bullet);
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }



    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWorld.SCREEN_WIDTH - 88) {
            x = GameWorld.SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWorld.SCREEN_HEIGHT- 80) {
            y = GameWorld.SCREEN_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

        //make rectangle visible on the screen
        g2d.setColor(Color.green);
        g2d.drawRect(this.getRectX(),this.getRectY(), img.getWidth(), img.getHeight());
    }



}
