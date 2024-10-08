package chessboard;
import chesspieces.*;
public class Board {
    Square[][] board;

    public Board() {
        this.resetBoard();
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            String error = x + ", " + y + ": Index out of bounds"; 
            throw new IndexOutOfBoundsException(error);
        } 
        return board[x][y];

    }

    public final void resetBoard() {
       Square[0][0] = new Square(0, 0, new Rook(true)); 
    }
}