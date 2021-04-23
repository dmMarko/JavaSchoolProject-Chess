public class Rook extends Piece {

    protected Rook(int tag, Board state) {
        super(tag, state);
        this.symbol = 'r';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[14][2];
        int[] checkedSpot;

        int indexCounter = 0;
        for (int axis : Utilities.AXIS_ITER) {
            for (int d : Utilities.PLUS_MINUS) {
                boolean breakCondition = true;
                int counter = 1;
                while(breakCondition){
                    try{
                        if(axis == 0){
                            checkedSpot = new int[]{spot[0], spot[1]+counter*d};
                        } else {
                            checkedSpot = new int[]{spot[0]+counter*d, spot[1]};
                        }
                        if(board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY){
                            spots[indexCounter++] = checkedSpot;
                        } else if(board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag){
                            spots[indexCounter++] = checkedSpot;
                            breakCondition = false;
                        } else {
                            breakCondition = false;
                        }
                    }catch(IndexOutOfBoundsException e){
                        breakCondition = false;
                    }
                    counter++;
                }
            }
        } 
        return spots;
    }

}