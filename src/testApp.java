class testApp {
    public static void main(String[] args) {
        Board game = new Board();
        System.out.println(game);
        if(game.canMoveFromTo(new int[]{7,1}, new int[]{0,0})){
            game.movePieceFromTo(new int[]{7,1}, new int[]{0,0});
        }
        if(game.canMoveFromTo(new int[]{7,0}, new int[]{0,0})){
            game.movePieceFromTo(new int[]{7,0}, new int[]{0,0});
        }
        System.out.println(game);
    }
}