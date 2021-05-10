import java.awt.*;
import javax.swing.*;

class testApp extends JPanel {

    public Board state;

    public testApp() {
        this.state = new Board();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(GuiConstants.IMAGES.get("board.png"), 0, 0, this);
        drawPieces(g);
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            int yCoord = 100 * row;

            for (int column = 0; column < Constants.BOARD_SIZE; column++) {
                int xCoord = 100 * column;

                Piece checked_piece = state.getRawBoard()[row][column];

                if (checked_piece.getTag() != Piece.EMPTY) {
                    String pieceName = (checked_piece.getTag() == Piece.WHITE ? "white" : "black") + "_"
                            + checked_piece.getClass().getName().toLowerCase() + ".png";
                    g.drawImage(GuiConstants.IMAGES.get(pieceName), xCoord, yCoord, this);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return GuiConstants.SCREEN_SIZE;
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testApp panel = new testApp();

        window.add(panel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();
    }
}