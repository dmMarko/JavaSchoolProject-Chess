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
        switch(tag){
            case BLACK:
                return symbol.toLowerCase();
            case WHITE:
                return symbol.toUpperCase();
        }
        return "-"; //never gonna happen
    }

    /**
     * 
     **/
    public void moved(){
        hasMoved = true;
    }

    public abstract int[][] getValidSpots(int[] spot);
}
