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

    /**
     * a new game method
     */
    public static void game() {
        Board gameBoard = new Board(); // new board instance
        boolean gameOver = false; // decides when the game is over
        boolean offeredDraw = false; // whether the other player offered a draw last turn
        boolean offeredDrawThisTurn = false; // whether the other player offered a draw this turn

        while (!gameOver) { // if the game is not over the game goes on
            System.out.println(gameBoard); // print the board

            offeredDrawThisTurn = false;
            int turnColour = (gameBoard.getTurnCounter() & 1) == 0 ? Piece.WHITE : Piece.BLACK; // get the current player
            String playerColourName = turnColour == Piece.WHITE ? "White" : "Black"; // string form
            int firstRow = turnColour == Piece.WHITE ? 7 : 0; // the first row (with the king and rook etc) depends on the colour

            // print input message
            System.out.println(playerColourName
                    + "’s turn, enter the position of the piece you want to move and the position you want to move it to (e2-e4 for example):");
            if (offeredDraw) {
                System.out.println("you also have a draw offer, type “draw” in order to accept, ignore to regect");
            }

            String rawInput; // the variable that will get the raw input, used to enable the user to enter
                             // non move commands
            int[][] input; // the variable that will contain the input, it should be a 2x2 2d array (two
                           // spots)

            // loop until the user input is syntax-valid and rules-valid
            boolean input_valid;
            do {
                input_valid = true;

                rawInput = inputGetter.nextLine(); // input from the user
                input = !(rawInput.toLowerCase().equals("resign") || rawInput.toLowerCase().equals("draw"))
                        ? InputManager.parseInput(rawInput)
                        : null; // if the player doesn't resign, parse the input

                if (rawInput.toLowerCase().equals("draw") && !offeredDraw) { // if the player has offered a draw
                    // print draw offer message
                    System.out.println("a draw offer will be sent during your opponent's next turn. please enter where you'd like to move"); 
                    offeredDraw = true; // offer a draw
                    offeredDrawThisTurn = true;
                    input_valid = false; // and move in case the other player doesn't draw
                } else if (rawInput.toLowerCase().equals("draw")) { // if the player has accepted the draw
                    System.out.println(gameBoard); // print the board for the final time
                    gameOver = true; // end the game
                    System.out.println("Both players agreed to draw!"); // print draw message
                } else if (rawInput.toLowerCase().equals("resign")) { // if the player resigned
                    gameOver = true; // end the game
                    System.out.println(gameBoard); // print the board for the final time
                    // and print a resignation message
                    System.out.println(playerColourName + " has resigned after "
                            + ((gameBoard.getTurnCounter() + 2) >> 1) + " turns!");
                } else if (input == null) { // check if the syntax matches the format
                    System.out.println("This position is invalid, please enter a valid position in the right format");
                    input_valid = false; // invalid input
                } else if (gameBoard.getRawBoard()[input[0][0]][input[0][1]].getTag() != turnColour) { // check if the player
                                                                                                       // didn't picked a
                                                                                                       // piece of their
                                                                                                       // own colour
                    System.out.println(
                            "the spot you are trying to move form does not contain a piece of your colour, please try another spot");
                    input_valid = false; // invalid input
                } else if (!gameBoard.canMoveFromTo(input[0], input[1])) { // check if the selsected piece can move to
                                                                           // the selected spot
                    System.out.println("This piece cannot be moved to the desired location, please try another one");
                    input_valid = false; // invalid input
                }
            } while (!input_valid);

            if (!gameOver) { // if the game didn't end yet, don't continue
                if (!offeredDrawThisTurn) {
                    offeredDraw = false;
                }

                // custom rook movement in case of castling
                if (gameBoard.getRawBoard()[input[0][0]][input[0][1]].getClass() == King.class
                        && input[1][0] == firstRow) { // if the moved piece is king and targets its row
                    if (input[1][1] == 2) { // if trying to left-castle
                        gameBoard.movePieceFromTo(new int[] { firstRow, 0 }, new int[] { firstRow, 3 }); // move rook
                        // accordingly
                    } else if (input[1][1] == 6) { // if trying to right-castle
                        gameBoard.movePieceFromTo(new int[] { firstRow, 7 }, new int[] { firstRow, 5 }); // move roo
                        // accordingly
                    }
                }

                // move piece to new location
                gameBoard.movePieceFromTo(input[0], input[1]);

                gameBoard.promote(); // promote all pawns on the edges

                // check if the player won the game
                if (gameBoard.didWin(turnColour)) {
                    gameOver = true; // end the game
                    System.out.println(gameBoard); // print the board for the final time
                    // print win message
                    System.out.println(playerColourName + " won in " + ((gameBoard.getTurnCounter() + 2) >> 1) + " turns!");
                }

                // increase the turn counter
                gameBoard.setTurnCounter(gameBoard.getTurnCounter() + 1); // gameBoard.turnCounter++

            }
        }
    }

    public static void main(String[] args) {
        mainMenu(); // show menu

        game(); // start a game

        inputGetter.close(); // close the scanner instance
    }

}