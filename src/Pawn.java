public class Pawn extends Piece {

    protected Pawn(int tag, Board state) {
        super(tag, state);
        this.symbol = 'p';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[4][2];
        int[] checkedSpot;

        try{
            checkedSpot = new int[] {spot[0] + this.tag, spot[1]};
            if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) {
                spots[0] = checkedSpot;

                checkedSpot = new int[] {spot[0] + this.tag * 2, spot[1]};

                if (!this.hasMoved && board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY){
                    spots[1] = checkedSpot;
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        int spotsIndexCounter = 2;
        for (int side : Utilities.PLUS_MINUS) {
            try {
                checkedSpot = new int[] {spot[0] + this.tag, spot[1] + side};

                if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag){
                    spots[spotsIndexCounter++] = checkedSpot;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        return spots;
    }

}