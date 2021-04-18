public class Bishop extends Piece{

    protected Bishop(int tag, Board state) {
        super(tag, state);
        symbol = 'b';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard();
        int[][] spots = new int[13][2];
        int[] checkedSpot;
        
        int indexCounter = 0;
        for (int hd : Utilities.PLUS_MINUS){
            for (int vd : Utilities.PLUS_MINUS){
                try{
                    for(int i = 1; true; i++){
                        checkedSpot = new int[]{spot[0] + i*vd, spot[1] + i*hd};
                        if(board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY){
                            spots[indexCounter++] = checkedSpot;
                        } else if(board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag) {
                            spots[indexCounter++] = checkedSpot;
                            throw new IndexOutOfBoundsException("reached a piece of opposite colour");
                        } else {
                            throw new IndexOutOfBoundsException("reached a piece of the same colour");
                        }
                    }
                } catch (IndexOutOfBoundsException e){
                }
            }
        }
        return spots;
    }
    
}