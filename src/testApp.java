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

        g.drawImage(GuiConstants.BOARD_IMG, 0, 0, this);

        for (int row = 0; row < Constants.BOARD_SIZE; row++){
            int yCoord = 100 * row;
            for (int column = 0; column < Constants.BOARD_SIZE; column++){
                int xCoord = 100 * column;

                Piece checked_piece = state.getRawBoard()[row][column];

                if (state.getRawBoard()[row][column].getTag() == Piece.WHITE){
                    if ( instanceof Rook){

                    }
                } else if (state.getRawBoard()[row][column].getTag() == Piece.BLACK){
                    
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