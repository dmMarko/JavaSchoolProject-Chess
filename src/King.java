public class King extends Piece {

    public static final char KING_SYMBOL = 'k';
    
    protected King(int tag, Board state) {
        super(tag, state);
        this.symbol = KING_SYMBOL;
    }

    @Override
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // get the board as an array in order to not write state.getRawBoard() everytime
        Spot[] spots = new Spot[9]; // the array that will be returned in the end
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int spotsIndexCounter = 0;
        for (int hDirection : Constants.PLUS_ZERO_MINUS) { // check each direction (horizontal)
            for (int vDirection : Constants.PLUS_ZERO_MINUS) { // and each direction in that direction (vertical)
                if (!(vDirection == 0 && hDirection == 0)) { // the king can't move to it's own spot
                    try {
                        checkedSpot = new Spot(spot.getRow() + vDirection, spot.getColumn() + hDirection); 

                        if (board.getPiece(checkedSpot).getTag() != this.tag) { // if foe or empty
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
            if (!board.getPiece(new Spot(firstRow, Constants.KINGSIDE_ROOK_COLUMN)).hasMoved && board.getPiece(new Spot(firstRow, 6)).getTag() == EMPTY
                    && board.getPiece(new Spot(firstRow, 5)).getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new Spot( firstRow, Constants.SHORT_CASTLE_KING_DEST );
            }

            // check long castling
            if (!board.getPiece(new Spot(firstRow, Constants.QUEENSIDE_ROOK_COLUMN)).hasMoved && board.getPiece(new Spot(firstRow, 1)).getTag() == EMPTY
                    && board.getPiece(new Spot(firstRow, 2)).getTag() == EMPTY && board.getPiece(new Spot(firstRow, 3)).getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new Spot( firstRow, Constants.LONG_CASTLE_KING_DEST );
            }
        }

        return spots;
    }

}
