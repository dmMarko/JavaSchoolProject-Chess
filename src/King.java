public class King extends Piece {

    protected King(int tag, Board state) {
        super(tag, state);
        this.symbol = 'k';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[9][2];
        int[] checkedSpot;

        int spotsIndexCounter = 0;
        for (int h : Utilities.PLUS_ZERO_MINUS) {
            for (int v : Utilities.PLUS_ZERO_MINUS) {
                if (!(v == 0 && h == 0)) {
                    try {
                        checkedSpot = new int[] { spot[0] + v, spot[1] + h };

                        if (board[checkedSpot[0]][checkedSpot[1]].tag != this.tag) {
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
