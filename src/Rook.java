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
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[14][2];
        int[] checkedSpot;

        // checking both direction for each axis
        int indexCounter = 0;
        for (int axis : Utilities.AXIS_ITER) { // x axis, y axis
            for (int d : Utilities.PLUS_MINUS) { // negative direction, positive direction
                boolean breakCondition = true;
                int counter = 1; // index of the checked spot
                while (breakCondition) {
                    try {
                        if (axis == 0) { // check the correct spot for horizontal axis
                            checkedSpot = new int[] { spot[0], spot[1] + counter * d };
                        } else { // check the correct spot for vertical axis
                            checkedSpot = new int[] { spot[0] + counter * d, spot[1] };
                        }

                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) { // if checked spot is empty
                            spots[indexCounter++] = checkedSpot; // add spot to available spots

                        } else if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) { // if checked spot is
                                                                                                  // foe
                            spots[indexCounter++] = checkedSpot; // add spot to avaliable spots
                            breakCondition = false; // break from the loop

                        } else { // if checked spot is the same color as this piece.
                            breakCondition = false; // break from the loop
                        }
                    } catch (IndexOutOfBoundsException e) { // if checked spot out of bounds break
                        breakCondition = false;
                    }
                    counter++;
                }
            }
        }
        return spots;
    }

}