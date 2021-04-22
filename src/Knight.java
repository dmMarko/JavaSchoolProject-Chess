public class Knight extends Piece {

    protected Knight(int tag, Board state) {
        super(tag, state);
        symbol = 'n';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[8][2];
        int[] checkedSpot;

        int indexCounter = 0;

        for (int axis : Utilities.AXIS_ITER) { // axis
            for (int d : Utilities.PLUS_MINUS) { // direction
                for (int t : Utilities.PLUS_MINUS) { // "turn" (like turn left)
                    if (axis == 0) {
                        checkedSpot = new int[] { spot[0] + 2 * d, spot[1] + t };
                    } else {
                        checkedSpot = new int[] { spot[0] + t, spot[1] + 2 * d };
                    }

                    if (board[checkedSpot[0]][checkedSpot[1]].getTag() != this.tag) {
                        spots[indexCounter++] = checkedSpot;
                    }
                }
            }
        }
        return spots;
    }

}
