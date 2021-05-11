public class Pawn extends Piece {

    protected Pawn(int tag, Board state) {
        super(tag, state);
        this.symbol = 'p';
    }
    
    /**
     * this method calculates the pawn's leagal moves
     * @param spot - the pawn's position
     * @return all leagal spots the pawn can move to
     */
    public int[][] getValidSpots(int[] spot) {
        Piece[][] board = this.state.getRawBoard(); // the board itself (array and not class) on which the pawn stands
        int[][] spots = new int[4][]; // this array will contain all of the spots on which this specific pawn can move to
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        try{ // farward check
            checkedSpot = new int[] {spot[0] + this.tag, spot[1]};
            if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) {
                spots[0] = checkedSpot;

                checkedSpot = new int[] {spot[0] + this.tag * 2, spot[1]};
                // pawns' condition for moving 1 spot forward is included in the condition for moving 2 spots, hence the nested if
                if (!this.hasMoved && board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY){
                    spots[1] = checkedSpot;
                }
            }
        }catch (IndexOutOfBoundsException e){ 
            //if we try to check out of bounds an error will be triggered. pawns can't move outside of the board so it will just quite the forward check
        }

        int indexCounter = 2;
        for (int side : Constants.PLUS_MINUS) {
            try {
                checkedSpot = new int[] {spot[0] + this.tag, spot[1] + side}; // set next checked spot to one to the side and one farward
                
                if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag){ 
                    spots[indexCounter++] = checkedSpot;
                }
            } catch (IndexOutOfBoundsException e) { 
                //handle the cases when pawns are placed on the edge of the board
            }
        }
        return spots;
    }

    @Override
    public String toString(){
        String symbol = String.valueOf(this.symbol); 
        switch(tag){
            case BLACK:
                return "δ";
            case WHITE:
                return "P";
            case EMPTY:
                return symbol;
        }
        return "-"; //never gonna happen
    }
}