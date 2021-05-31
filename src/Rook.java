public class Rook extends Piece {

    protected Rook(int tag, Board state) {
        super(tag, state);
        this.symbol = 'r';
    }

    /**
     * this method calculates the rook's leagal moves
     * 
     * @param spot - the rook's position
     * @return all leagal spots the rook can move to
     */
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // get the board as an array in order to not write state.getRawBoard() everytime
        Spot[] spots = new Spot[14]; // the array that will be returned in the end
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;
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
        return spots;
    }
}