public class Knight extends Piece {

    protected Knight(int tag, Board state) {
        super(tag, state);
        this.symbol = 'n';
    }
    /**
     * this method calculates the knight's leagal moves
     * @param spot - the knight's position
     * @return all leagal spots the knight can move to
     */
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // get the board as an array in order to not write state.getRawBoard() everytime
        Spot[] spots = new Spot[8]; // the array that will be returned in the end
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;

        // the next 3 for loops account for all possible locations a knight can move to
        for (int axis : Constants.AXIS_ITER) {
            for (int direction : Constants.PLUS_MINUS) {
                for (int side : Constants.PLUS_MINUS) { // for each side in that direction
                    try {
                        if (axis == 0) { // on the x axis
                            checkedSpot = new Spot(spot.getRow() + 2 * direction, spot.getColumn() + side); // check 2 to the side and 1 forwards
                        } else { // on the y axis
                            checkedSpot = new Spot( spot.getRow() + side, spot.getColumn() + 2 * direction ); // check 2 forward and 1 to one side
                        }

                        if (board.getPiece(checkedSpot).getTag() != this.tag) { // the only existing square a knight can't move to is one with a piece of the same colour
                            spots[indexCounter++] = checkedSpot;
                        }
                    } catch (IndexOutOfBoundsException e) { // if it tried checking out of board, ignore and pass onwards
                    }
                }
            }
        }
        return spots;
    }
}
