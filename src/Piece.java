public abstract class Piece {
    //constants
    public static final int WHITE = -1; // the tag for white pieces
    public static final int BLACK = +1; // the tag for black pieces
    public static final int EMPTY = 0;  // the tag for empty pieces
    protected int tag; // the variable that decides whether the piece is black white or empty
    protected Board state; // the board on which the piece is placed
    protected char symbol; // the symbol of the piece, different for every kind of piece
    protected boolean hasMoved; // detects if the piece has moved, usefull for kings, rooks and pawns that have spacial properties on their first move

    public int getTag(){
        return this.tag;
    }

    protected Piece(int tag, Board state) {
        this.tag = tag;
        this.state = state;
        this.hasMoved = false;
    }
    
    @Override
    public String toString(){
        String symbol = String.valueOf(this.symbol); 
        switch(tag){ // if the tag is black, the symbol should be lower case, if the tag is white it should be uppercase
            case BLACK:
                return symbol.toLowerCase();
            case WHITE:
                return symbol.toUpperCase();
            case EMPTY:
                return symbol;
        }
        return "-"; //never gonna happen
    }

    /**
     * this method sets the hasMoved variable to true
     **/
    public void moved(){
        hasMoved = true;
    }
    /**
     * this method calculates the piece's leagal moves
     * @param spot - the piece's position
     * @return all leagal spots the piece can move to
     */
    public abstract int[][] getValidSpots(int[] spot);
}
