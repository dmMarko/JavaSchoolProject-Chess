import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

class GuiApp extends JPanel implements MouseListener {

    public Board state;

    private boolean pieceSelected = false;

    private int[] spotHolder;

    public GuiApp() {
        this.state = new Board();
        setLayout(null);
        addMouseListener(this);
        JButton resignButton = new JButton("RESIGN");
        JButton tieButton = new JButton("DRAW");

        resignButton.setBounds(75, 820, 225, 110);
        tieButton.setBounds(500, 820, 225, 110);

        resignButton.setBackground(new Color(255, 255, 245));
        tieButton.setBackground(new Color(230, 230, 25));

        resignButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getParent(),
                        (state.getTurnCounter() % 2 == 0 ? "White" : "Black") + " has resigned!", "GAME OVER",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

        });

        tieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(getParent(),
                        (state.getTurnCounter() % 2 == 0 ? "White" : "Black") + " has offered a draw.\n"
                                + (state.getTurnCounter() % 2 == 1 ? "White" : "Black") + ", will you accept?",
                        "Draw?", JOptionPane.YES_NO_OPTION);
                System.out.println(response);

                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(getParent(), "both players agreed to draw.\nclosing game.", "DRAW.",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(getParent(),
                            (state.getTurnCounter() % 2 == 1 ? "White" : "Black") + " has declined the draw offer.",
                            "DECLINE", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        add(resignButton);
        add(tieButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(GuiUtilities.IMAGES.get("board"), 0, 0, this);
        drawPieces(g);
        drawSelectMask(g);
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            int yCoord = GuiUtilities.COORDS_SPOT_RATIO * row;

            for (int column = 0; column < Constants.BOARD_SIZE; column++) {
                int xCoord = GuiUtilities.COORDS_SPOT_RATIO * column;

                Piece checked_piece = state.getRawBoard()[row][column];

                if (checked_piece.getTag() != Piece.EMPTY) {
                    String pieceName = (checked_piece.getTag() == Piece.WHITE ? "white" : "black") + "_"
                            + checked_piece.getClass().getName().toLowerCase();
                    g.drawImage(GuiUtilities.IMAGES.get(pieceName), xCoord, yCoord, this);
                }
            }
        }
    }

    private void drawSelectMask(Graphics g) {
        if (pieceSelected) {
            for (int[] pos : state.getRawBoard()[spotHolder[0]][spotHolder[1]].getValidSpots(spotHolder)) {
                if (pos != null) {
                    g.drawImage(GuiUtilities.IMAGES.get("selectMask"), pos[1] * GuiUtilities.COORDS_SPOT_RATIO,
                            pos[0] * GuiUtilities.COORDS_SPOT_RATIO, this);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return GuiUtilities.SCREEN_SIZE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getY() < 800) {
            int[] chosenPos = new int[] { e.getY() / GuiUtilities.COORDS_SPOT_RATIO,
                    e.getX() / GuiUtilities.COORDS_SPOT_RATIO };
            Piece tempPiece = state.getRawBoard()[e.getY() / GuiUtilities.COORDS_SPOT_RATIO][e.getX()
                    / GuiUtilities.COORDS_SPOT_RATIO];
            if (!pieceSelected && tempPiece.getTag() == (state.getTurnCounter() % 2 == 0 ? Piece.WHITE : Piece.BLACK)) {
                spotHolder = chosenPos;
                pieceSelected = true;
            } else if (pieceSelected) {
                if (state.canMoveFromTo(spotHolder, chosenPos)) {
                    castleMove(new int[][] { spotHolder, chosenPos });
                    state.movePieceFromTo(spotHolder, chosenPos);
                    checkWin();
                    state.promote();
                    state.setTurnCounter(state.getTurnCounter() + 1);
                }
                spotHolder = null;
                pieceSelected = false;
            }
        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void castleMove(int[][] input) {
        int firstRow = state.getTurnCounter() % 2 == 0 ? 7 : 0;
        // only happens if the chosen source spot is a king that hasn't moved and trying
        // to move to the same row (the first one)
        if (state.getRawBoard()[input[0][0]][input[0][1]] instanceof King && input[1][0] == firstRow
                && !state.getRawBoard()[input[0][0]][input[0][1]].hasMoved) {

            // if trying to do castle long
            if (input[1][1] == Constants.LONG_CASTLE_KING_DEST) {
                state.movePieceFromTo(new int[] { firstRow, Constants.QUEENSIDE_ROOK_COLUMN },
                        new int[] { firstRow, Constants.LONG_CASTLE_ROOK_DEST });

                // if trying to do castle short
            } else if (input[1][1] == Constants.SHORT_CASTLE_KING_DEST) {
                state.movePieceFromTo(new int[] { firstRow, Constants.KINGSIDE_ROOK_COLUMN },
                        new int[] { firstRow, Constants.SHORT_CASTLE_ROOK_DEST });

            }
        }
    }

    public void checkWin() {
        if (state.didWin(state.getTurnCounter() % 2 == 0 ? Piece.WHITE : Piece.BLACK)) {
            repaint();
            JOptionPane.showMessageDialog(this.getParent(),
                    (state.getTurnCounter() % 2 == 0 ? "White" : "Black") + " has won!", "GAME OVER",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GuiApp panel = new GuiApp();

        window.add(panel);
        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
    }
}