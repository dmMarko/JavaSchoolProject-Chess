public class Constants {
    public static final int[] PLUS_MINUS = { +1, -1 }; // an array containing plus and minus, used to check both sides

    public static final int[] AXIS_ITER = { 0, 1 }; // an array containing zero and one, decide stuff

    public static final int[] PLUS_ZERO_MINUS = { +1, 0, -1 }; // and array containing plus minus and neutral, used to
                                                               // check both sides and the middle

    public static final String RULES_TEXT = "In this game, the objective of both players is to capture the enemy's king \n"
            + "In each turn, the player will be allowed to move one of it's pieces by the following rules: \n"
            + "A pawn can move one square forward at a time, if it's the pawn's first turn, it can also move 2 squares forward. It can only capture pieces one square diagonally forward. \n"
            + "A rook can move to any unblocked square on its row or column. It can capture as it moves. \n"
            + "A knight can move two squares in one direction and one in the other direction simultaneously. It can capture as it moves. This is  the only piece that can't be blocked \n"
            + "A bishop can move to any unblocked square on its diagonals. It can capture as it moves. \n"
            + "A queen can move to any unblocked square on its row, column, or diagonals. It can capture as it moves. \n"
            + "A king can move to any adjacent unblocked square. It can capture as it moves. If you capture your opponent's king, you win! \n"
            + "A pawn that reaches the end of the board can be promoted into a queen. \n"
            + "If the king and a rook hadn't moved yet, you can \"castle\" - the rook moves next to the king, and the king moves to the other side of the rook. It will happen if you try to move the king two squares to one of the sides. \n"
            + "In order to move, click on the piece you want to move, and then click on the spot you want to move it to \n"
            + "You can always resign or offer a draw by clicking on the apropriate buttons \n"; // the rules of
                                                                                                         // the game

    public static final int WHITE_FIRST_ROW = 7;
    public static final int BLACK_FIRST_ROW = 0;

    public static final int QUEENSIDE_ROOK_COLUMN = 0;
    public static final int KINGSIDE_ROOK_COLUMN = 7;

    public static final int SHORT_CASTLE_KING_DEST = 6;
    public static final int LONG_CASTLE_KING_DEST = 2;

    public static final int SHORT_CASTLE_ROOK_DEST = 5;
    public static final int LONG_CASTLE_ROOK_DEST = 3;

    public static final int BOARD_SIZE = 8;

    



}
