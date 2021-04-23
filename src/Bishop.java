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
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[13][2];
        int[] checkedSpot;

        int indexCounter = 0;

        // checking 4 diagonal directions
        for (int hd : Utilities.PLUS_MINUS) { // horizontal direction
            for (int vd : Utilities.PLUS_MINUS) { // vertical direction
                try {
                    for (int i = 1; true; i++) {
                        checkedSpot = new int[] { spot[0] + i * vd, spot[1] + i * hd }; // currently the checked spot
                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) { // if empty spot
                            spots[indexCounter++] = checkedSpot; // add to available spots
                        } else if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) { // if spots is foe
                            spots[indexCounter++] = checkedSpot; // add to available spots
                            throw new IndexOutOfBoundsException("reached a piece of opposite colour"); // break from the
                                                                                                       // current
                                                                                                       // diagonal
                        } else {
                            throw new IndexOutOfBoundsException("reached a piece of the same colour"); // break from the
                                                                                                       // current
                                                                                                       // diagonal
                        }
                    }
                } catch (IndexOutOfBoundsException e) { // if checked spot out of bounds just skip it
                }
            }
        }
        return spots;
    }

}