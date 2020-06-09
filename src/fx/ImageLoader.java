package fx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Does the actual image loading for the game
public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
