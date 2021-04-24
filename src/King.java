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
                if (!(v == 0 && h == 0)) { // the king can't move to it's own spot
                    try {
                        checkedSpot = new int[] { spot[0] + v, spot[1] + h }; // set next spot to check

                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() != this.tag) { // a king can move to any existing square that doesn't contain a piece of the same colour
                            spots[spotsIndexCounter++] = checkedSpot;
                        }
                    } catch (IndexOutOfBoundsException e) { // the king can't go out of bounds
                    }
                }
            }
        }

        int firstRow = this.tag == WHITE ? 7 : 0; // the first row (with the king and rook etc) depends on the colour
        if (!this.hasMoved) { // if the king didn't move

            // check left castling
            if (!board[firstRow][7].hasMoved && board[firstRow][6].getTag() == EMPTY
                    && board[firstRow][5].getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new int[] { firstRow, 6 };
            }

            // check right castling
            if (!board[firstRow][0].hasMoved && board[firstRow][1].getTag() == EMPTY
                    && board[firstRow][2].getTag() == EMPTY && board[firstRow][3].getTag() == EMPTY) {
                spots[spotsIndexCounter++] = new int[] { firstRow, 2 };
            }
        }

        return spots;
    }

}
