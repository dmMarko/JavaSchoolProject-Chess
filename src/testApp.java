class testApp {
    public static void main(String[] args) {
        Board game = new Board();
        System.out.println(game);
        System.out.println(game.canMoveFromTo(new int[]{7,1}, new int[]{5,1}));
        System.out.println(game.canMoveFromTo(new int[]{7,1}, new int[]{5,0}));

        System.out.println(game.canMoveFromTo(new int[]{6,1}, new int[]{5,1}));
        System.out.println(game.canMoveFromTo(new int[]{6,1}, new int[]{4,1}));
        System.out.println(game.canMoveFromTo(new int[]{6,1}, new int[]{3,1}));
        System.out.println(game.canMoveFromTo(new int[]{6,1}, new int[]{4,2}));

    }
}