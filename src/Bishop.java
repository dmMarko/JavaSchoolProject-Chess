public class Bishop extends Piece {

    protected Bishop(int tag, Board state) {
        super(tag, state);
        this.symbol = 'b';
    }

    /**
     * this method calculates the bishop's leagal moves
     * 
     * @param spot - the bishop's position
     * @return all leagal spots the bishop can move to
     */
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // get the board as an array in order to not write state.getRawBoard() everytime
        Spot[] spots = new Spot[13]; // the array that will be returned in the end
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;

        for (int hDirection : Constants.PLUS_MINUS) {
            for (int vDirection : Constants.PLUS_MINUS) {
                try {
                    for (int i = 1; true; i++) {
                        checkedSpot = new Spot( spot.getRow() + i * vDirection, spot.getColumn() + i * hDirection );
                        if (board.getPiece(checkedSpot).getTag() == EMPTY) { // if empty spot
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