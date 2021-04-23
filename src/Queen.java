public class Queen extends Piece {

    protected Queen(int tag, Board state) {
        super(tag, state);
        this.symbol = 'q';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[27][];
        int[] checkedSpot;

        int indexCounter = 0;

        // rook-like
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

        // bishop-like
        for (int hd : Utilities.PLUS_MINUS) {
            for (int vd : Utilities.PLUS_MINUS) {
                try {
                    for (int i = 1; true; i++) {
                        checkedSpot = new int[] { spot[0] + i * vd, spot[1] + i * hd };
                        if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) {
                            spots[indexCounter++] = checkedSpot;
                        } else if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) {
                            spots[indexCounter++] = checkedSpot;
                            throw new IndexOutOfBoundsException("reached a piece of opposite colour");
                        } else {
                            throw new IndexOutOfBoundsException("reached a piece of the same colour");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }

        return spots;

    }

}
