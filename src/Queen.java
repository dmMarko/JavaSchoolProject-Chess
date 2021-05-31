public class Queen extends Piece {

    protected Queen(int tag, Board state) {
        super(tag, state);
        this.symbol = 'q';
    }

    @Override
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // get the board as an array in order to not write state.getRawBoard() everytime
        Spot[] spots = new Spot[27]; // the array that will be returned in the end
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;

        // rook-like
        for (int axis : Constants.AXIS_ITER) {
            for (int direction : Constants.PLUS_MINUS) {
                boolean breakCondition = true;
                int spotCounter = 1; // the index of the checked spot in the current direction
                while (breakCondition) {
                    try {
                        if (axis == 0) { // check the correct spot for horizontal axis
                            checkedSpot = new Spot( spot.getRow(), spot.getColumn() + spotCounter * direction );
                        } else { // check the correct spot for vertical axis
                            checkedSpot = new Spot( spot.getRow() + spotCounter * direction, spot.getColumn() );
                        }
                        if (board.getPiece(checkedSpot).getTag() == EMPTY) {
                            spots[indexCounter++] = checkedSpot;
                        } else if (board.getPiece(checkedSpot).getTag() == -this.tag) { // if checked spot is foe
                            spots[indexCounter++] = checkedSpot;
                            breakCondition = false;
                        } else { // if checked spot is the same color as this piece.
                            breakCondition = false;
                        }
                    } catch (IndexOutOfBoundsException e) { // if checked spot out of bounds break
                        breakCondition = false;
                    }
                    spotCounter++;
                }
            }
        }

        // bishop-like
        for (int hDirection : Constants.PLUS_MINUS) { // horizontal direction
            for (int vDirection : Constants.PLUS_MINUS) { // vertical direction
                try {
                    for (int i = 1; true; i++) {
                        checkedSpot = new Spot( spot.getRow() + i * vDirection, spot.getColumn() + i * hDirection );
                        if (board.getPiece(checkedSpot).getTag() == EMPTY) {
                            spots[indexCounter++] = checkedSpot;
                        } else if (board.getPiece(checkedSpot).getTag() == -this.tag) { // if spots is foe
                            spots[indexCounter++] = checkedSpot;
                            throw new IndexOutOfBoundsException("reached a piece of opposite colour");
                        } else {
                            throw new IndexOutOfBoundsException("reached a piece of the same colour");
                        }
                    }
                } catch (IndexOutOfBoundsException e) { // if checked spot out of bounds just skip it
                }
            }
        }
        return spots;
    }
}
