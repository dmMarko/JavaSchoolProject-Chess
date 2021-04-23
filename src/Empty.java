public class Empty extends Piece{

    protected Empty(Board state) {
        super(EMPTY, state);
        this.symbol = ' ';
    }

    @Override
    public int[][] getValidSpots(int[] spot) {
        return new int[][]{};
    }
    
}
