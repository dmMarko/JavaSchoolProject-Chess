import java.util.Scanner;

public class App {
    static Scanner inputGetter = new Scanner(System.in); // input

    /**
     * the main menu of the game. blocks you until you typed play.
     */
    public static void mainMenu() {
        String input = ""; // the string that saves the user's input

        while (!input.equals("play")) { // the game only starts when the user wants to play
            System.out.println("Welcom to chess for 2 \ntype “play” or “rules”"); // welcome message
            input = inputGetter.nextLine().toLowerCase(); // get the input

            while (!(input.equals("play") || input.equals("rules"))) { // try again if the input wasn't valid
                System.out.println("Invalid input, please try again"); // fail message
                input = inputGetter.nextLine().toLowerCase(); // get the input
            }

            if (input.equals("rules")) { // if the user wanted the rules, give him the rules
                System.out.println(Utilities.RULES_TEXT); // print the
            }

        }
    }

    public static void game() {
        Board gameBoard = new Board();
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println(gameBoard);
            int turnColour = (gameBoard.getTurnCounter() & 1) == 0 ? Piece.WHITE : Piece.BLACK;
            String playerColourName = turnColour == Piece.WHITE ? "White" : "Black";

            System.out.println(
                        playerColourName + "’s turn, enter the position of the piece you want to move and the position you want to move it to (e2-e4 for example):");

            int[][] input;
            boolean input_valid;
            do {
                input_valid = true;

                input = InputManager.parseInput(inputGetter.nextLine());

                if (input == null) {
                    System.out.println("This position is invalid, please enter a valid position in the right format");
                    input_valid = false;
                } else if (gameBoard.getRawBoard()[input[0][0]][input[0][1]].getTag() != turnColour) {
                    System.out.println(
                            "the spot you are trying to move form does not contain a piece of your colour, please try another spot");
                    input_valid = false;
                } else if (!gameBoard.canMoveFromTo(input[0], input[1])) {
                    System.out.println("This piece cannot be moved to the desired location, please try another one");
                    input_valid = false;
                }
            } while (!input_valid);

            gameBoard.movePieceFromTo(input[0], input[1]);

            if (gameBoard.didWin(turnColour)) {
                gameOver = true;
                System.out.println(playerColourName + " won in " + (gameBoard.getTurnCounter() + 1) + " turns!");
            }

            gameBoard.setTurnCounter(gameBoard.getTurnCounter()+1); // gameBoard.turnCounter++
        }
    }

    public static void main(String[] args) {
        mainMenu();

        game();

        inputGetter.close();
    }

}
