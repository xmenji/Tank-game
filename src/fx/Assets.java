package fx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background,
            tank1,
            tank2,
            ubWall, //unbreakable wall (Wall1)
            bWall,  //breakable wall    (Wall2)
            shell,
            bullet,
            powerup;//health

    //loads all of the images for the game
    public static void init(){
        background = ImageLoader.loadImage("resources/Background.bmp");
        tank1 = ImageLoader.loadImage("resources/tank1.png");
        tank2 = ImageLoader.loadImage("resources/Tank2.gif");
        ubWall = ImageLoader.loadImage("resources/Wall1.gif");
        bWall = ImageLoader.loadImage("resources/Wall2.gif");
        shell = ImageLoader.loadImage("resources/Shell.gif");
        powerup = ImageLoader.loadImage("resources/Pickup.gif");
        bullet = ImageLoader.loadImage("resources/Shell.gif");
    }

}