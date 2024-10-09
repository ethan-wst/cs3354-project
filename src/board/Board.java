package board;
import pieces.*;

/**
 * Represents the board
 */
public class Board {
    Square[][] chessBoard = new Square[8][8];

    /**
     * Creates a new board object that is in its opening state
     */
    public Board() {
        this.resetBoard();
    }

    /**
     * Gets the square object at the specified x, y coordinate
     * @param x X coordinate of square
     * @param y Y coordinate of square
     * @return  The square object at the specified x, y coordinate
     */
    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            String error = x + ", " + y + ": Index out of bounds"; 
            throw new IndexOutOfBoundsException(error);
        } 
        return chessBoard[x][y];
    }

    /**]
     * Sets board object back to original state 
     */
    public final void resetBoard() {
        chessBoard[0][0] = new Square(0, 0, new Rook(true)); 
        chessBoard[1][0] = new Square(1, 0, new Knight(true));
        chessBoard[2][0] = new Square(2, 0, new Bishop(true));
        chessBoard[3][0] = new Square(3, 0, new Queen(true));
        chessBoard[4][0] = new Square(4, 0, new King(true));
        chessBoard[5][0] = new Square(5, 0, new Bishop(true));
        chessBoard[6][0] = new Square(6, 0, new Knight(true));
        chessBoard[7][0] = new Square(7, 0, new Rook(true));

        chessBoard[0][1] = new Square(0, 1, new Pawn(true)); 
        chessBoard[1][1] = new Square(1, 1, new Pawn(true));
        chessBoard[2][1] = new Square(2, 1, new Pawn(true));
        chessBoard[3][1] = new Square(3, 1, new Pawn(true));
        chessBoard[4][1] = new Square(4, 1, new Pawn(true));
        chessBoard[5][1] = new Square(5, 1, new Pawn(true));
        chessBoard[6][1] = new Square(6, 1, new Pawn(true));
        chessBoard[7][1] = new Square(7, 1, new Pawn(true));

       chessBoard[0][7] = new Square(0, 7, new Rook(false)); 
       chessBoard[1][7] = new Square(1, 7, new Knight(false));
       chessBoard[2][7] = new Square(2, 7, new Bishop(false));
       chessBoard[3][7] = new Square(3, 7, new Queen(false));
       chessBoard[4][7] = new Square(4, 7, new King(false));
       chessBoard[5][7] = new Square(5, 7, new Bishop(false));
       chessBoard[6][7] = new Square(6, 7, new Knight(false));
       chessBoard[7][7] = new Square(7, 7, new Rook(false));

       chessBoard[0][6] = new Square(0, 6, new Pawn(false)); 
       chessBoard[1][6] = new Square(1, 6, new Pawn(false));
       chessBoard[2][6] = new Square(2, 6, new Pawn(false));
       chessBoard[3][6] = new Square(3, 6, new Pawn(false));
       chessBoard[4][6] = new Square(4, 6, new Pawn(false));
       chessBoard[5][6] = new Square(5, 6, new Pawn(false));
       chessBoard[6][6] = new Square(6, 6, new Pawn(false));
       chessBoard[7][6] = new Square(7, 6, new Pawn(false));

       for (int i = 2; i < 6; i++) { 
        for (int j = 0; j < 8; j++) { 
            chessBoard[i][j] = new Square(i, j, null); 
        } 
    } 
    }
}