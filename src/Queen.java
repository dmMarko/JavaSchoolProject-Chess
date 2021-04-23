public class Queen extends Piece {

    protected Queen(int tag, Board state) {
        super(tag, state);
        this.symbol = 'q';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard(); // get the board as an array in order to not write state.getRawBoard() everytime
        int[][] spots = new int[27][]; // the array that will be returned in the end
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        int indexCounter = 0;

        // rook-like
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
                        } else if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) { // if checked spot is foe
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

        // bishop-like
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
                                                                                                       // current diagonal
                        } else {
                            throw new IndexOutOfBoundsException("reached a piece of the same colour"); // break from the
                                                                                                       // current diagonal
                        }
                    }
                } catch (IndexOutOfBoundsException e) { // if checked spot out of bounds just skip it
                }
            }
        }
        return spots;

    }

}
