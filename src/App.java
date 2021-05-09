import java.util.Scanner;

public class App {
    static Scanner inputGetter = new Scanner(System.in);

    static Board gameBoard = new Board();

    static int turnColour = Piece.WHITE; // the current player
    static String playerColourName; // string form

    static int firstRow; // the first row (with the king and rook etc) depends on the colour

    static boolean gameOver;

    static boolean offeredDraw; // whether the other player offered a draw last turn
    static boolean offeredDrawThisTurn; // whether the current player offered a draw this turn

    /**
     * the main menu of the game. blocks you until you typed play.
     */
    public static void mainMenu() {
        String input = ""; // the string that saves the user's input

        while (!input.equals("play")) {
            System.out.println("Welcom to chess for 2 \ntype \"play\" or \"rules\"");
            input = inputGetter.nextLine().toLowerCase();

            // try again if the input wasn't valid
            while (!(input.equals("play") || input.equals("rules"))) {
                System.out.println("Invalid input, please try again");
                input = inputGetter.nextLine().toLowerCase();
            }

            if (input.equals("rules")) {
                System.out.println(Constants.RULES_TEXT);
            }
        }
    }

    /**
     * this method gets an input from the user, checks whether he wants to draw or resign.
     * @return the move the user would like to make [[srcRow, srcColumn], [destRow, destColumn]]
     */
    public static int[][] getMove() {
        String rawInput; // the variable that will get the raw input, used to enable the user to enter
                         // non move commands
        int[][] input; // the variable that will contain the input, it should be a 2x2 2d array (two
                       // spots)

        // loop until the user input is syntax-valid and rules-valid
        boolean input_valid;
        do {
            input_valid = true;

            rawInput = inputGetter.nextLine(); // input from the user
            input = (rawInput.toLowerCase().equals("resign") || rawInput.toLowerCase().equals("draw")) ? null
                    : InputManager.parseInput(rawInput); // if the player doesn't resign, parse the input

            if (rawInput.toLowerCase().equals("draw") && !offeredDraw) { // if the player has offered a draw
                System.out.println(
                        "a draw offer will be sent during your opponent's next turn. please enter where you'd like to move");
                offeredDraw = true;
                offeredDrawThisTurn = true;
                input_valid = false; // need to move anyway

            } else if (rawInput.toLowerCase().equals("draw") && !offeredDrawThisTurn) {
                // if white has accepted the draw
                System.out.println(gameBoard);
                gameOver = true;
                System.out.println("Both players agreed to draw!");
            } else if (rawInput.toLowerCase().equals("draw")) {
                System.out.println("You have already offered a draw, please enter where you'd like to move");
                input_valid = false;
            } else if (rawInput.toLowerCase().equals("resign")) {
                gameOver = true;
                System.out.println(gameBoard);
                // and print a resignation message
                System.out.println(playerColourName + " has resigned after " + ((gameBoard.getTurnCounter() + 2) >> 1)
                        + " turns!");
            } else if (input == null) { // check if the syntax matches the format
                System.out.println("This position is invalid, please enter a valid position in the right format");
                input_valid = false; // invalid input
            } else if (gameBoard.getRawBoard()[input[0][0]][input[0][1]].getTag() != turnColour) {
                System.out.println(
                        "the spot you are trying to move form does not contain a piece of your colour, please try another spot");
                input_valid = false; // invalid input
            } else if (!gameBoard.canMoveFromTo(input[0], input[1])) {
                 // check if the selsected piece can move to the selected spot
                System.out.println("This piece cannot be moved to the desired location, please try another one");
                input_valid = false; // invalid input
            }
        } while (!input_valid);

        return input;
    }

    /**
     * if the user chose to castle, this method will move the rook accordingly
     * 
     * @param input - the input from the user
     */
    public static void castleMove(int[][] input) {
        // only happens if the chosen source spot is a king that hasn't moved and trying
        // to move to the same row (the first one)
        if (gameBoard.getRawBoard()[input[0][0]][input[0][1]] instanceof King && input[1][0] == firstRow
                && !gameBoard.getRawBoard()[input[0][0]][input[0][1]].hasMoved) {

            // if trying to do castle long
            if (input[1][1] == Constants.LONG_CASTLE_KING_DEST) {
                gameBoard.movePieceFromTo(new int[] { firstRow, Constants.QUEENSIDE_ROOK_COLUMN },
                        new int[] { firstRow, Constants.LONG_CASTLE_ROOK_DEST });

                // if trying to do castle short
            } else if (input[1][1] == Constants.SHORT_CASTLE_KING_DEST) {
                gameBoard.movePieceFromTo(new int[] { firstRow, Constants.KINGSIDE_ROOK_COLUMN },
                        new int[] { firstRow, Constants.SHORT_CASTLE_ROOK_DEST });

            }
        }
    }

    /**
     * checks whether someone won by checkmate and prints who won
     * 
     * @return whether the current player won
     */
    public static boolean checkWin() {
        // check if the player won the game
        if (gameBoard.didWin(turnColour)) {
            System.out.println(gameBoard); // print the board for the final time
            // print win message
            System.out.println(playerColourName + " won in " + ((gameBoard.getTurnCounter() + 2) >> 1) + " turns!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        mainMenu(); // show menu

        gameOver = false;

        offeredDraw = false;

        // the variable that will contain the input, it should be a 2x2 2d array (two
        // spots)
        int[][] input;

        do {
            System.out.println(gameBoard); // print the board

            offeredDrawThisTurn = false;
            turnColour = (gameBoard.getTurnCounter() & 1) == 0 ? Piece.WHITE : Piece.BLACK; // get the current
                                                                                            // player
            playerColourName = turnColour == Piece.WHITE ? "White" : "Black"; // string form
            firstRow = turnColour == Piece.WHITE ? 7 : 0; // the first row (with the king and rook etc) depends on
                                                          // the colour

            // print input message
            System.out.println(playerColourName
                    + "'s turn, enter the position of the piece you want to move and the position you want to move it to (e2-e4 for example): \n" +
                    "remember that you can always type \"resign\" to resign or \"draw\" to draw");
            if (offeredDraw) {
                System.out.println("you also have a draw offer, type \"draw\" in order to accept, ignore to regect");
            }

            input = getMove();

            if (!gameOver) {
                if (!offeredDrawThisTurn) {
                    offeredDraw = false;
                }

                gameBoard.movePieceFromTo(input[0], input[1]);

                gameBoard.promote(); // promote all pawns on the edges

                gameOver = checkWin();

                gameBoard.setTurnCounter(gameBoard.getTurnCounter() + 1);
            }

        } while (!gameOver);
        // game(); // start a game

        inputGetter.close(); // close the scanner instance
    }
}