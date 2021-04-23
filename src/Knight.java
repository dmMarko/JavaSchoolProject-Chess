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
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[8][];
        int[] checkedSpot;

        int indexCounter = 0;

        // the next 3 for loops account for all possible locations a knight can move to
        for (int axis : Utilities.AXIS_ITER) { // for each axis
            for (int d : Utilities.PLUS_MINUS) { // for each direction on that axis
                for (int side : Utilities.PLUS_MINUS) { // for each side in that direction
                    try { // in case it goes out of board
                        if (axis == 0) { // on the y axis
                            checkedSpot = new int[] { spot[0] + 2 * d, spot[1] + side }; // check 2 forward and 1 to one side
                        } else { // on the x axis
                            checkedSpot = new int[] { spot[0] + side, spot[1] + 2 * d }; // check 2 forward and 1 to one side
                        }

                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() != this.tag) { // the only existing square a knight can't move to is one with a piece of the same colour
                            spots[indexCounter++] = checkedSpot; // add an available spot as an available spot
                        }
                    } catch (IndexOutOfBoundsException e) { // if it tried checking out of board, ignore and pass onwards
                    }
                }
            }
        }
        return spots;
    }
}
