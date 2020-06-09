package tankgame;

import fx.Assets;
import fx.GameCamera;
import tankgame.gameobjects.FloorTile;
import tankgame.gameobjects.GameObject;
import tankgame.gameobjects.collidable.Bullet;
import tankgame.gameobjects.collidable.breakable.Tank;
import tankgame.gameobjects.collidable.powerup.HealthPowerup;
import tankgame.gameobjects.collidable.unbreakable.ubWall;
import tankgame.map.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class GameWorld extends JPanel{
    private static GameWorld stage;

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame gameFrame;
    private Tank tank1, tank2;
    protected BufferedImage background, ubWall;

    public static ArrayList<GameObject> mapObjects;
    public static ArrayList<Bullet> bulletsShot = new ArrayList<Bullet>();//must be in the tank class
    public static ArrayList<GameObject> healthPacks = new ArrayList<>();

    //Game Camera
    private GameCamera camera;

    public static void main(String args[]){
        stage = new GameWorld();
        stage.init();

        //call map loader and load objs (walls, powerups)
        mapObjects = new ArrayList<GameObject>();
        MapLoader loadMap = new MapLoader();
        loadMap.loadMap();


        try{
            //the game will keep drawing the tank(s) as long as the program is still active
            while(true){
                stage.tank1.update();
                stage.tank2.update();//added
                stage.repaint();
                Thread.sleep(1000 / 144);
            }
        }
        catch(InterruptedException ignored){ }
    }
    public static GameWorld getInstance(){
        return stage;
    }
    public void addBullet(Bullet bullet){
        bulletsShot.add(bullet);
    }

    public GameCamera getCamera() {
        return camera;
    }

    private void init() {
        this.gameFrame = new JFrame("TANK GAME");
        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Assets.init(); //load imgs
        camera = new GameCamera(this,0,0);

        BufferedImage tank1Img = null,
                tank2Img = null;

        try{//load image resources
            tank1Img = read(new File("resources/tank1.png"));
            background = read(new File("resources/background.bmp"));
            ubWall = Assets.ubWall;
            //tank2Img = read(new File("resources/Tank2.gif"));
            tank2Img = Assets.tank2;
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        //Initialize tank object
        tank1 = new Tank(200, 200, 0, 0, 0, tank1Img);
        tank2 = new Tank(800, 800, 0,0,0, tank2Img);

        //Create tank control object
        TankControl tc1 = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        TankControl tc2 = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE); //added

        //Setup the JFrame
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.add(this);

        this.gameFrame.addKeyListener(tc1);
        this.gameFrame.addKeyListener(tc2);//added

        this.gameFrame.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);
        this.gameFrame.setResizable(true);
        this.gameFrame.setLocationRelativeTo(null);

        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        //Set general font size and type for UI
        buffer.setFont(new Font("Times Roman", Font.BOLD, 18));

        //populate the screen with the background tiles
        for(int row = 0; row <= SCREEN_WIDTH; row += 320){
            for (int col = 0; col <= SCREEN_HEIGHT; col += 240){
                buffer.drawImage(background, row - getCamera().getxOffset() , col -  getCamera().getxOffset(), null);
            }
        }

        //populate the screen contained in mapObjects Arraylist
        for(int i = 0; i < mapObjects.size(); i++){
            mapObjects.get(i).drawImage(buffer);

            //System.out.println(mapObjects.get(i).getRectY());
            //checking collision
            mapObjects.get(i).checkCollision(tank1);
            mapObjects.get(i).checkCollision(tank2);
        }

        //draw the tanks
        this.tank1.drawImage(buffer);
        this.tank2.drawImage(buffer);//added

        //animate bullets if they are being shot
        if(!bulletsShot.isEmpty()){
            for(int i = 0; i < bulletsShot.size(); i++){
                bulletsShot.get(i).drawImage(buffer);
                bulletsShot.get(i).update();

                //check for tank collisions...
                bulletsShot.get(i).checkCollision(tank1);
                bulletsShot.get(i).checkCollision(tank2);
            }
        }

        buffer.setColor(Color.white);
        //Tank 1 UI stuff
        buffer.drawString("Player 1",0,750);
        buffer.drawString("Lives Remaining: " + tank1.getLives(), 0, 780);
        buffer.drawString("Health: ", 0, 810);
        //health bar
        buffer.setColor(Color.black);
        buffer.setStroke(new BasicStroke(3));
        buffer.drawRect(79,800,102, 22);
        buffer.setColor(Color.green);
        for(int x = 0; x <= tank1.getHealth(); x++){
            buffer.fillRect(80, 800, x,20);
        }

        buffer.setColor(Color.white);
        //Tank 2 UI stuff
        buffer.drawString("Player 2",1000,750);
        buffer.drawString("Lives Remaining: " + tank2.getLives(), 1000, 780);
        buffer.drawString("Health: ", 1000, 810);
        //health bar
        buffer.setColor(Color.black);
        buffer.drawRect(1079,800,102, 22);
        buffer.setColor(Color.green);
        for(int x = 0; x <= tank2.getHealth(); x++){
            buffer.fillRect(1080, 800, x,20);
        }

        //Mini map
        BufferedImage miniMap = this.world.getSubimage(0,0,SCREEN_WIDTH, SCREEN_HEIGHT);
        buffer.drawImage(miniMap.getScaledInstance(SCREEN_WIDTH / 5, SCREEN_WIDTH / 5, BufferedImage.TYPE_INT_RGB), 500, 700, null );

        //trying to get split screen (ended up not working....)
        /*BufferedImage t1Screen = this.world.getSubimage(10, 10, SCREEN_WIDTH / 3, SCREEN_HEIGHT /2);
        buffer.drawImage(t1Screen,0,0,null);

        BufferedImage t2Screen = this.world.getSubimage(640,480,300 , 300);
        buffer.drawImage(t2Screen,500, 0,null);*/

        g2.drawImage(world,0,0,null);
    }

}
