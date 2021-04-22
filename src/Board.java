public class Board {
    private Piece[][] rawBoard;
    private final Empty EMPTY_PIECE = new Empty(this);
    private int counter;



    public Board(){
        counter = 0;
        /*
        -----------------
        |r|n|b|q|k|b|n|r|
        -----------------
        |p|p|p|p|p|p|p|p|
        -----------------
        | | | | | | | | |
        -----------------
        | | | | | | | | |
        -----------------
        | | | | | | | | |
        -----------------
        | | | | | | | | |
        -----------------
        |P|P|P|P|P|P|P|P|
        -----------------
        |R|N|B|Q|K|B|N|R|
        -----------------
         */
        rawBoard = new Piece[8][8];
        for(int row = 0; row < rawBoard.length; row++){
            rawBoard[row] = new Piece[8];
        }

        rawBoard[0][0] = new Rook(Piece.BLACK ,this);
        rawBoard[0][1] = new Knight(Piece.BLACK ,this);
        rawBoard[0][2] = new Bishop(Piece.BLACK ,this);
        rawBoard[0][3] = new Queen(Piece.BLACK ,this);
        rawBoard[0][4] = new King(Piece.BLACK ,this);
        rawBoard[0][5] = new Bishop(Piece.BLACK ,this);
        rawBoard[0][6] = new Knight(Piece.BLACK ,this);
        rawBoard[0][7] = new Rook(Piece.BLACK ,this);

        for(int column = 0; column < rawBoard[1].length; column++){
            rawBoard[1][column] = new Pawn(Piece.BLACK, this);
        }

        for(int row = 2; row < 5; row++){
            for(int column = 0; column < 8; column++){
                rawBoard[row][column] = EMPTY_PIECE;
            }
        }

        for(int column = 0; column < rawBoard[6].length; column++){
            rawBoard[6][column] = new Pawn(Piece.WHITE, this);
        }

        rawBoard[7][0] = new Rook(Piece.WHITE ,this);
        rawBoard[7][1] = new Knight(Piece.WHITE ,this);
        rawBoard[7][2] = new Bishop(Piece.WHITE ,this);
        rawBoard[7][3] = new Queen(Piece.WHITE ,this);
        rawBoard[7][4] = new King(Piece.WHITE ,this);
        rawBoard[7][5] = new Bishop(Piece.WHITE ,this);
        rawBoard[7][6] = new Knight(Piece.WHITE ,this);
        rawBoard[7][7] = new Rook(Piece.WHITE ,this);
    }

    public boolean canMoveFromTo(int[] piecePos, int[] nextPos){
        int[][] avaliableSpots = rawBoard[piecePos[0]][piecePos[1]].getValidSpots(piecePos);
        for(int[] spot : avaliableSpots){
            if(nextPos.equals(spot)){
                return true;
            }
        }
        return false;
    }

    public void movePiece(int[] piecePos, int[] nextPos){
        rawBoard[nextPos[0]][nextPos[1]] = rawBoard[piecePos[0]][piecePos[1]];
        rawBoard[piecePos[0]][piecePos[1]] = EMPTY_PIECE;
    }

    public boolean didWin(){
        boolean whiteKingExists = false;
        boolean blackKingExists = false;
        for(Piece[] rows : rawBoard){
            for(Piece checkedPiece : rows){
                if(checkedPiece.equals("k")){
                    blackKingExists = true;
                } else if(checkedPiece.equals("K")){
                    whiteKingExists = true;
                } 
            }
        }
        if(blackKingExists && whiteKingExists){
            return false;
        }
        return true;
    }


    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Piece[][] getRawBoard() {
        return this.rawBoard;
    }

    @Override
    public String toString(){
        return "-----------------\n" +
               "|" + rawBoard[0][0] + "|" + rawBoard[0][1] + "|" + rawBoard[0][2] + "|" + rawBoard[0][3] + "|" + rawBoard[0][4] + "|" + rawBoard[0][5] + "|" + rawBoard[0][6] + "|" + rawBoard[0][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[1][0] + "|" + rawBoard[1][1] + "|" + rawBoard[1][2] + "|" + rawBoard[1][3] + "|" + rawBoard[1][4] + "|" + rawBoard[1][5] + "|" + rawBoard[1][6] + "|" + rawBoard[1][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[2][0] + "|" + rawBoard[2][1] + "|" + rawBoard[2][2] + "|" + rawBoard[2][3] + "|" + rawBoard[2][4] + "|" + rawBoard[2][5] + "|" + rawBoard[2][6] + "|" + rawBoard[2][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[3][0] + "|" + rawBoard[3][1] + "|" + rawBoard[3][2] + "|" + rawBoard[3][3] + "|" + rawBoard[3][4] + "|" + rawBoard[3][5] + "|" + rawBoard[3][6] + "|" + rawBoard[3][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[4][0] + "|" + rawBoard[4][1] + "|" + rawBoard[4][2] + "|" + rawBoard[4][3] + "|" + rawBoard[4][4] + "|" + rawBoard[4][5] + "|" + rawBoard[4][6] + "|" + rawBoard[4][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[5][0] + "|" + rawBoard[5][1] + "|" + rawBoard[5][2] + "|" + rawBoard[5][3] + "|" + rawBoard[5][4] + "|" + rawBoard[5][5] + "|" + rawBoard[5][6] + "|" + rawBoard[5][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[6][0] + "|" + rawBoard[6][1] + "|" + rawBoard[6][2] + "|" + rawBoard[6][3] + "|" + rawBoard[6][4] + "|" + rawBoard[6][5] + "|" + rawBoard[6][6] + "|" + rawBoard[6][7] + "\n" + 
               "-----------------\n" +
               "|" + rawBoard[7][0] + "|" + rawBoard[7][1] + "|" + rawBoard[7][2] + "|" + rawBoard[7][3] + "|" + rawBoard[7][4] + "|" + rawBoard[7][5] + "|" + rawBoard[7][6] + "|" + rawBoard[7][7] + "\n" + 
               "-----------------";
    }
}
