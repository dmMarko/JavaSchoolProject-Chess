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
            if (board[checkedSpot[0]][checkedSpot[1]].tag == EMPTY) {
                spots[0] = checkedSpot;

                checkedSpot = new int[] {spot[0] + this.tag * 2, spot[1]};

                if (!this.hasMoved && board[checkedSpot[0]][checkedSpot[1]].tag == EMPTY){
                    spots[1] = checkedSpot;
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        int indexCounter = 2;
        for (int side : Utilities.PLUS_MINUS) {
            try {
                checkedSpot = new int[] {spot[0] + this.tag, spot[1] + side};

                if (board[checkedSpot[0]][checkedSpot[1]].tag == -this.tag){
                    spots[indexCounter] = checkedSpot;
                }
            } catch (IndexOutOfBoundsException e) {
            }
            indexCounter++;
        }

        return spots;
    }

}