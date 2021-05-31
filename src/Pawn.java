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
    public Spot[] getValidSpots(Spot spot) {
        Board board = this.state; // the board itself (array and not class) on which the pawn stands
        Spot[] spots = new Spot[4]; // this array will contain all of the spots on which this specific pawn can move to
        Spot checkedSpot; // temporary variable, used to temporeraly hold the spot that the program will check next

        try{ // farward check
            checkedSpot = new Spot(spot.getRow() + this.tag, spot.getColumn());

            if (board.getPiece(checkedSpot).getTag() == EMPTY) {
                spots[0] = checkedSpot;

                checkedSpot = new Spot(spot.getRow() + this.tag * 2, spot.getColumn());
                // pawns' condition for moving 1 spot forward is included in the condition for moving 2 spots, hence the nested if
                if (!this.hasMoved && board.getPiece(checkedSpot).getTag() == EMPTY){
                    spots[1] = checkedSpot;
                }
            }
        }catch (IndexOutOfBoundsException e){ 
            //if we try to check out of bounds an error will be triggered. pawns can't move outside of the board so it will just quite the forward check
        }

        int indexCounter = 2;
        for (int side : Constants.PLUS_MINUS) {
            try {
                checkedSpot = new Spot(spot.getRow() + this.tag, spot.getColumn() + side); // set next checked spot to one to the side and one farward
                
                if (board.getPiece(checkedSpot).getTag() == -this.tag){ 
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