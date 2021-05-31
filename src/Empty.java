public class Empty extends Piece{

    protected Empty(Board state) {
        super(EMPTY, state);
        this.symbol = ' ';
    }

    @Override
    public Spot[] getValidSpots(Spot spot) {
        return new Spot[]{};
    }
    
}
