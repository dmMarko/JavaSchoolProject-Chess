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
        int[][] spots = new int[4][2]; // this array will contain all of the spots on which this specific pawn can move to
        int[] checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        try{ // farward check
            checkedSpot = new int[] {spot[0] + this.tag, spot[1]}; // set next spot to the spot in front of the pawn
            if (board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY) { // pawns can only move forward if the spot in front of them is empty
                spots[0] = checkedSpot;

                checkedSpot = new int[] {spot[0] + this.tag * 2, spot[1]};
                // pawns' condition for moving 1 spot forward is included in the condition for moving 2 spots, hence the nested if
                if (!this.hasMoved && board[checkedSpot[0]][checkedSpot[1]].getTag() == EMPTY){ // pawns can only move forward twice if the spots
                                                                                                // in front of them are empty and they havn't moved yet
                    spots[1] = checkedSpot;
                }
            }
        }catch (IndexOutOfBoundsException e){ 
            //if we try to check out of bounds an error will be triggered. pawns can't move outside of the board so it will just quite the forward check
        }

        int spotsIndexCounter = 2;
        for (int side : Utilities.PLUS_MINUS) { // left and right
            try {
                checkedSpot = new int[] {spot[0] + this.tag, spot[1] + side}; // set next checked spot to one to the side and one farward
                
                // because the tags for black and white are opposites, so in order to check if it's an enemy pawn, we check for the negative tag
                if (board[checkedSpot[0]][checkedSpot[1]].getTag() == -this.tag){ 
                    spots[spotsIndexCounter++] = checkedSpot; // add the spots to the return list
                }
            } catch (IndexOutOfBoundsException e) { 
                //handle the cases when pawns are placed on the edge of the board
            }
        }
        return spots;
    }

}