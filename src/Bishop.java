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
        Piece[][] board = this.state.getRawBoard(); // get the board as an array in order to not write state.getRawBoard() everytime
        int[][] spots = new int[13][]; // the array that will be returned in the end
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;

        for (int hDirection : Constants.PLUS_MINUS) {
            for (int vDirection : Constants.PLUS_MINUS) {
                try {
                    for (int i = 1; true; i++) {
                        checkedSpot = new int[] { spot[0] + i * vDirection, spot[1] + i * hDirection };
                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) { // if empty spot
                            spots[indexCounter++] = checkedSpot;
                        } else if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) { // if spots is foe
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