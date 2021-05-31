/**
 * represents a spot on the board
 */
public class Spot {

    /**
     * the row of the point. must be between 0 to 7
     */
    private int row;

    /**
     * the column of the point. must be between 0 to 7
     */
    private int column;

    public Spot(int row, int column) {
        // check row between bounds
        setRow(row);

        // check column between bounds
        setColumn(column);
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int newRow) {
        if (newRow <= 7 && newRow >= 0) {
            this.row = newRow;
        } else {

            // specifically this exception is needed because it can pass over it when you
            // encounter out of bounds value in any piece's getValidSpot method.s
            throw new IndexOutOfBoundsException("row " + newRow + " is out of bounds. row between 0 and 7 is needed.");
        }
    }

    public void setColumn(int newColumn) {
        if (newColumn <= 7 && newColumn >= 0) {
            this.column = newColumn;
        } else {

            // specifically this exception is needed because it can pass over it when you
            // encounter out of bounds value in any piece's getValidSpot method.
            throw new IndexOutOfBoundsException(
                    "column " + newColumn + " is out of bounds. column between 0 and 7 is needed.");
        }
    }

    public boolean equals_to(Spot other) {
        return (this.row == other.row && this.column == other.column);
    }
}
