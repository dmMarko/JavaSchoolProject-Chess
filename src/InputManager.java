import java.util.regex.*;

public class InputManager {
    private static Pattern parsePattern = Pattern.compile("([a-hA-H])([1-8])\\W([a-hA-H])([1-8])\\W*"); // regex pattern

    /**
     * this method converts a universal chess coordinate to the program's coordinates
     * @param file - universal chess coordinate file (row)
     * @param rank - universal chess coordinate rank (column)
     * @return the location as the program's coordinates
     * 
     * note that the method gets the input as column-row(intuative for humans) but returns as row-column(how the program works)
     */
    public static int[] convertChessCoords(char file, int rank) { 
        int col = Character.toLowerCase(file) - 'a'; // get the file/row from a-h to 0-8
        int row = 8 - rank; // get the rank/column from 8-0 to 0-8

        return new int[] {row, col};
    }

    /**
     * this methd extracts the coords from the user's input
     */
    public static int[][] parseInput(String input) {
        Matcher inputMatcher = parsePattern.matcher(input); // match the input with the pattern

        // if the input doesn't match the pattern, we can't extract the coords
        if (!inputMatcher.matches()) { 
            return null;
        }

        // if it does, extract the coords
        return new int[][] { 
                convertChessCoords(inputMatcher.group(1).charAt(0), Integer.parseInt(inputMatcher.group(2))),
                convertChessCoords(inputMatcher.group(3).charAt(0), Integer.parseInt(inputMatcher.group(4))) };
    }
}