public class King extends Piece {

    protected King(int tag, Board state) {
        super(tag, state);
        this.symbol = 'k';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard(); // get the board as an array in order to not write state.getRawBoard() everytime
        int[][] spots = new int[9][]; // the array that will be returned in the end
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int spotsIndexCounter = 0;
        for (int h : Utilities.PLUS_ZERO_MINUS) { // check each direction (horizontal)
            for (int v : Utilities.PLUS_ZERO_MINUS) { // and each direction in that direction (vertical)
                if (!(v == 0 && h == 0)) {
                    try {
                        checkedSpot = new int[] { spot[0] + v, spot[1] + h }; 

                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() != this.tag) {
                            spots[spotsIndexCounter++] = checkedSpot;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return spots;
    }

}
