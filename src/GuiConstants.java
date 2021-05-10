import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GuiConstants {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 800);

    public static final HashMap<String, BufferedImage> IMAGES = new HashMap<String, BufferedImage>();

    static {
        try {
            File[] imagesArray = (new File("src\\resources").listFiles());
            for (File file : imagesArray) {
                IMAGES.put(file.getName(), ImageIO.read(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}