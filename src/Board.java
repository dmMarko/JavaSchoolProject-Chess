import java.util.Arrays;

/* board starting state:
-----------------
|r|n|b|q|k|b|n|r|
-----------------
|p|p|p|p|p|p|p|p|
-----------------
| | | | | | | | |
-----------------
| | | | | | | | |
-----------------
| | | | | | | | |
-----------------
| | | | | | | | |
-----------------
|P|P|P|P|P|P|P|P|
-----------------
|R|N|B|Q|K|B|N|R|
-----------------
*/
public class Board {
    private Piece[][] rawBoard; // the board itself, the thing that contains the pieces
    private final Empty EMPTY_PIECE = new Empty(this); // because all empty pieces are the same all the time,
                                                       // instead of creating countless empty pieces,
                                                       // we create one and point to it everytime we want to add an
                                                       // empty piece
    private int turnCounter; // counts the turns

    public Board() {
        turnCounter = 0;

        rawBoard = new Piece[8][8]; // creating the board array

        // black's first line
        rawBoard[0][0] = new Rook(Piece.BLACK, this);
        rawBoard[0][1] = new Knight(Piece.BLACK, this);
        rawBoard[0][2] = new Bishop(Piece.BLACK, this);
        rawBoard[0][3] = new Queen(Piece.BLACK, this);
        rawBoard[0][4] = new King(Piece.BLACK, this);
        rawBoard[0][5] = new Bishop(Piece.BLACK, this);
        rawBoard[0][6] = new Knight(Piece.BLACK, this);
        rawBoard[0][7] = new Rook(Piece.BLACK, this);

        // black's second line of pawns
        for (int column = 0; column < Constants.BOARD_SIZE; column++) {
            rawBoard[1][column] = new Pawn(Piece.BLACK, this);
        }

        // the empty spots of the board
        for (int row = 2; row < 6; row++) {
            for (int column = 0; column < Constants.BOARD_SIZE; column++) {
                rawBoard[row][column] = EMPTY_PIECE;
            }
        }

        // white's second line
        for (int column = 0; column < Constants.BOARD_SIZE; column++) {
            rawBoard[6][column] = new Pawn(Piece.WHITE, this);
        }

        // white's first line
        rawBoard[7][0] = new Rook(Piece.WHITE, this);
        rawBoard[7][1] = new Knight(Piece.WHITE, this);
        rawBoard[7][2] = new Bishop(Piece.WHITE, this);
        rawBoard[7][3] = new Queen(Piece.WHITE, this);
        rawBoard[7][4] = new King(Piece.WHITE, this);
        rawBoard[7][5] = new Bishop(Piece.WHITE, this);
        rawBoard[7][6] = new Knight(Piece.WHITE, this);
        rawBoard[7][7] = new Rook(Piece.WHITE, this);
    }

    /**
     * the method checks if a piece in a certein spot can move to another spot
     * 
     * @param piecePos - the current position of the piece
     * @param nextPos  - the desired position of the piece
     * @return - whether the move is legal.
     */
    public boolean canMoveFromTo(int[] piecePos, int[] nextPos) {
        int[][] avaliableSpots = rawBoard[piecePos[0]][piecePos[1]].getValidSpots(piecePos);
    
        for (int[] spot : avaliableSpots) {
            if (Arrays.equals(spot, nextPos)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * moves a piece to a location in the board.
     * 
     * @param piecePos - the piece's coord to move
     * @param nextPos  - the desired spot
     */
    public void movePieceFromTo(int[] piecePos, int[] nextPos) {
        rawBoard[nextPos[0]][nextPos[1]] = rawBoard[piecePos[0]][piecePos[1]]; // moves piece to desired location
        rawBoard[piecePos[0]][piecePos[1]] = EMPTY_PIECE; // emptys the original spot
        rawBoard[nextPos[0]][nextPos[1]].moved();
    }

    /**
     * checks whether the given player won the game.
     * 
     * @param turn - the player to check
     * @return - whether the given player won or not
     */
    public boolean didWin(int turn) {
        boolean found_king = false;
        // if turn is white search for black king, and vice versa
        String symbol = (turn == Piece.WHITE) ? Character.toString(King.KING_SYMBOL).toLowerCase() : Character.toString(King.KING_SYMBOL).toUpperCase();

        // iterate through the board and check every piece
        for (Piece[] row : rawBoard) {
            for (Piece piece : row) {
                if (piece.toString().equals(symbol))
                    found_king = true;
            }
        }

        // if the king wasn't found, the player won the game
        return !found_king;

    }

    /**
     * when a pawn reaches the end of the board, it gets promoted to a queen
     */
    public void promote(){
        for (int row : new int[]{Constants.BLACK_FIRST_ROW, Constants.WHITE_FIRST_ROW}){ //check both sides
            for (int column = 0; column < 8; column++){ //check all columns in each side
                if (rawBoard[row][column] instanceof Pawn){ 
                    rawBoard[row][column] = new Queen(rawBoard[row][column].getTag(), this); // make the pawn a queen
                }
            }
        }
    }

    public int getTurnCounter() {
        return this.turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public Piece[][] getRawBoard() {
        return this.rawBoard;
    }

    @Override
    public String toString() {
        String returns = "";
        int rowNum = 8;

        for (Piece[] row : rawBoard) {
            returns += "\n  ---------------------------------\n" + rowNum--;
            for (Piece nextPiece : row) {
                returns += " | " + nextPiece.toString();
            }
            returns += " |";
        }
        returns += "\n  ---------------------------------" + "\n    A   B   C   D   E   F   G   H  \n";
        return returns;
    }
}
