import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GuiUtilities {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 950);

    public static final HashMap<String, BufferedImage> IMAGES = new HashMap<String, BufferedImage>();

    public static final byte COORDS_SPOT_RATIO = 100;

    static {
        try {
            File[] imagesArray = (new File("src\\resources").listFiles());
            for (File file : imagesArray) {
                IMAGES.put(file.getName().substring(0, file.getName().length() - 4), ImageIO.read(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static int[] coordsToSpot(int[] spot) {
        return new int[]{spot[0]/COORDS_SPOT_RATIO, spot[1]/COORDS_SPOT_RATIO};
    }
}