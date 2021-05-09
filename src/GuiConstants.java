import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GuiConstants {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 800);

    public static final BufferedImage BOARD_IMG;
    public static final BufferedImage BLACK_ROOK_IMG;
    public static final BufferedImage WHITE_ROOK_IMG;

    static {
        BufferedImage tmpBoard = null;
        BufferedImage tmpBlackRook = null;
        BufferedImage tmpWhiteRook = null;

        try {
            tmpBoard = ImageIO.read(new File("src\\resources\\Board.png"));
            tmpBlackRook = ImageIO.read(new File("src\\resources\\black_rook.png"));
            tmpWhiteRook = ImageIO.read(new File("src\\resources\\white_rook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BOARD_IMG = tmpBoard;
        BLACK_ROOK_IMG = tmpBlackRook;
        WHITE_ROOK_IMG = tmpWhiteRook;
    }
}