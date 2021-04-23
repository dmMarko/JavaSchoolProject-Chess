class testApp {
    public static void main(String[] args) {
        int[][] x = InputManager.parseInput("a3-b4 ");
        System.out.println("" + x[0][0] + "," + x[0][1] + "   " + x[1][0] + "," + x[1][1]);
        x = InputManager.parseInput("a8-a1 ");
        System.out.println("" + x[0][0] + "," + x[0][1] + "   " + x[1][0] + "," + x[1][1]);
        x = InputManager.parseInput("h1-h8 ");
        System.out.println("" + x[0][0] + "," + x[0][1] + "   " + x[1][0] + "," + x[1][1]);
        x = InputManager.parseInput("e4-d5 ");
        System.out.println("" + x[0][0] + "," + x[0][1] + "   " + x[1][0] + "," + x[1][1]);

        int[] y = InputManager.convertChessCoords('a', 1);
        System.out.println(y[0] + "," + y[1]);
    }
}