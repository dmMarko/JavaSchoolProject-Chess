import java.util.regex.*;

public class InputManager {
    private static Pattern parsePattern = Pattern.compile("\\W*([a-hA-H])([1-8])\\W*([a-hA-H])([1-8])\\W*");

    private static int[] convertChessCoords(char file, int rank) {
        int col = file - 'a';
        int row = 8 - rank;

        return new int[] { col, row };
    }

    public static int[][] parseInput(String input) {
        Matcher inputMatcher = parsePattern.matcher(input);

        if (!inputMatcher.matches()) {
            return null;
        }

        return new int[][] {
                convertChessCoords(inputMatcher.group(1).charAt(0), Integer.parseInt(inputMatcher.group(2))),
                convertChessCoords(inputMatcher.group(3).charAt(0), Integer.parseInt(inputMatcher.group(4))) };
    }
}