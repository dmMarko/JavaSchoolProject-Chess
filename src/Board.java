import java.util.Arrays;

/*
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
    private Piece[][] rawBoard;
    private final Empty EMPTY_PIECE = new Empty(this);
    private int turnCounter;

    public Board() {
        turnCounter = 0;

        rawBoard = new Piece[8][8];
        for (int row = 0; row < rawBoard.length; row++) {
            rawBoard[row] = new Piece[8];
        }

        rawBoard[0][0] = new Rook(Piece.BLACK, this);
        rawBoard[0][1] = new Knight(Piece.BLACK, this);
        rawBoard[0][2] = new Bishop(Piece.BLACK, this);
        rawBoard[0][3] = new Queen(Piece.BLACK, this);
        rawBoard[0][4] = new King(Piece.BLACK, this);
        rawBoard[0][5] = new Bishop(Piece.BLACK, this);
        rawBoard[0][6] = new Knight(Piece.BLACK, this);
        rawBoard[0][7] = new Rook(Piece.BLACK, this);

        for (int column = 0; column < rawBoard[1].length; column++) {
            rawBoard[1][column] = new Pawn(Piece.BLACK, this);
        }

        for (int row = 2; row < 6; row++) {
            for (int column = 0; column < 8; column++) {
                rawBoard[row][column] = EMPTY_PIECE;
            }
        }

        for (int column = 0; column < rawBoard[6].length; column++) {
            rawBoard[6][column] = new Pawn(Piece.WHITE, this);
        }

        rawBoard[7][0] = new Rook(Piece.WHITE, this);
        rawBoard[7][1] = new Knight(Piece.WHITE, this);
        rawBoard[7][2] = new Bishop(Piece.WHITE, this);
        rawBoard[7][3] = new Queen(Piece.WHITE, this);
        rawBoard[7][4] = new King(Piece.WHITE, this);
        rawBoard[7][5] = new Bishop(Piece.WHITE, this);
        rawBoard[7][6] = new Knight(Piece.WHITE, this);
        rawBoard[7][7] = new Rook(Piece.WHITE, this);
    }

    public boolean canMoveFromTo(int[] piecePos, int[] nextPos) {
        int[][] avaliableSpots = rawBoard[piecePos[0]][piecePos[1]].getValidSpots(piecePos);
        for (int[] spot : avaliableSpots) {
            if (Arrays.equals(spot, nextPos)) {
                return true;
            }
        }
        return false;
    }

    public void movePiece(int[] piecePos, int[] nextPos) {
        rawBoard[nextPos[0]][nextPos[1]] = rawBoard[piecePos[0]][piecePos[1]];
        rawBoard[piecePos[0]][piecePos[1]] = EMPTY_PIECE;
        rawBoard[nextPos[0]][nextPos[1]].moved();
    }

    public boolean didWin() {
        boolean whiteKingExists = false;
        boolean blackKingExists = false;
        for (Piece[] rows : rawBoard) {
            for (Piece checkedPiece : rows) {
                if (checkedPiece.toString().equals("k")) {
                    blackKingExists = true;
                } else if (checkedPiece.toString().equals("K")) {
                    whiteKingExists = true;
                }
            }
        }
        return !(blackKingExists && whiteKingExists);
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

        for(Piece[] row : rawBoard){
            returns += "\n  ---------------------------------\n" + rowNum--;
            for(Piece nextPiece : row){
                returns += " | " + nextPiece.toString();
            }
            returns += " |";
        }
        returns += "\n  ---------------------------------" + 
                   "\n    A   B   C   D   E   F   G   H  \n";
        return returns;
    }
}
