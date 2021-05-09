public class King extends Piece {

    public static final char KING_SYMBOL = 'k';
    
    protected King(int tag, Board state) {
        super(tag, state);
        this.symbol = KING_SYMBOL;
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard(); // get the board as an array in order to not write state.getRawBoard() everytime
        int[][] spots = new int[9][]; // the array that will be returned in the end
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int spotsIndexCounter = 0;
        for (int hDirection : Constants.PLUS_ZERO_MINUS) { // check each direction (horizontal)
            for (int vDirection : Constants.PLUS_ZERO_MINUS) { // and each direction in that direction (vertical)
                if (!(vDirection == 0 && hDirection == 0)) { // the king can't move to it's own spot
                    try {
                        checkedSpot = new int[] { spot[0] + vDirection, spot[1] + hDirection }; 

                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() != this.tag) { // if foe or empty
                            spots[spotsIndexCounter++] = checkedSpot;
                        }
                    } catch (IndexOutOfBoundsException e) { // the king can't go out of bounds
                    }
                }
            }
        }

        int firstRow = this.tag == WHITE ? Constants.WHITE_FIRST_ROW : Constants.BLACK_FIRST_ROW; // the first row (with the king and rook etc) depends on the colour
        if (!this.hasMoved) {

            // check short castling
            if (!board[firstRow][Constants.KINGSIDE_ROOK_COLUMN].hasMoved && board[firstRow][6].getTag() == EMPTY
                    && board[firstRow][5].getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new int[] { firstRow, Constants.SHORT_CASTLE_KING_DEST };
            }

            // check long castling
            if (!board[firstRow][Constants.QUEENSIDE_ROOK_COLUMN].hasMoved && board[firstRow][1].getTag() == EMPTY
                    && board[firstRow][2].getTag() == EMPTY && board[firstRow][3].getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new int[] { firstRow, Constants.LONG_CASTLE_KING_DEST };
            }
        }

        return spots;
    }

}
