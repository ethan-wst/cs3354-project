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
        return this.chessBoard[x][y];
    }

    /**
     * Sets board object to starting state
     */
    public final void resetBoard() {
        this.chessBoard[0][0] = new Square(0, 0, new Rook(true)); 
        this.chessBoard[1][0] = new Square(1, 0, new Knight(true));
        this.chessBoard[2][0] = new Square(2, 0, new Bishop(true));
        this.chessBoard[3][0] = new Square(3, 0, new Queen(true));
        this.chessBoard[4][0] = new Square(4, 0, new King(true));
        this.chessBoard[5][0] = new Square(5, 0, new Bishop(true));
        this.chessBoard[6][0] = new Square(6, 0, new Knight(true));
        this.chessBoard[7][0] = new Square(7, 0, new Rook(true));

        this.chessBoard[0][1] = new Square(0, 1, new Pawn(true)); 
        this.chessBoard[1][1] = new Square(1, 1, new Pawn(true));
        this.chessBoard[2][1] = new Square(2, 1, new Pawn(true));
        this.chessBoard[3][1] = new Square(3, 1, new Pawn(true));
        this.chessBoard[4][1] = new Square(4, 1, new Pawn(true));
        this.chessBoard[5][1] = new Square(5, 1, new Pawn(true));
        this.chessBoard[6][1] = new Square(6, 1, new Pawn(true));
        this.chessBoard[7][1] = new Square(7, 1, new Pawn(true));

        this.chessBoard[0][7] = new Square(0, 7, new Rook(false)); 
        this.chessBoard[1][7] = new Square(1, 7, new Knight(false));
        this.chessBoard[2][7] = new Square(2, 7, new Bishop(false));
        this.chessBoard[3][7] = new Square(3, 7, new Queen(false));
        this.chessBoard[4][7] = new Square(4, 7, new King(false));
        this.chessBoard[5][7] = new Square(5, 7, new Bishop(false));
        this.chessBoard[6][7] = new Square(6, 7, new Knight(false));
        this.chessBoard[7][7] = new Square(7, 7, new Rook(false));

        this.chessBoard[0][6] = new Square(0, 6, new Pawn(false)); 
        this.chessBoard[1][6] = new Square(1, 6, new Pawn(false));
        this.chessBoard[2][6] = new Square(2, 6, new Pawn(false));
        this.chessBoard[3][6] = new Square(3, 6, new Pawn(false));
        this.chessBoard[4][6] = new Square(4, 6, new Pawn(false));
        this.chessBoard[5][6] = new Square(5, 6, new Pawn(false));
        this.chessBoard[6][6] = new Square(6, 6, new Pawn(false));
        this.chessBoard[7][6] = new Square(7, 6, new Pawn(false));

        for (int i = 0; i < 8; i++) { 
            for (int j = 2; j < 6; j++) { 
                this.chessBoard[i][j] = new Square(i, j, null); 
            } 
        } 
    }

    /**
     * Prints the current state of the board object in the terminal
     */
    public void display() {
        for (int y = 8; y >= 0; y--) {
            if (y == 8) System.out.print("   ");
            else System.out.print((y+1) + "  ");
            for (int x = 0; x < 8; x++) {
                if (y == 8) System.out.print((char) (x+65) + "  ");
                else {
                    
                    if (this.getSquare(x, y).getPiece() == null) {
                        if ((x+y)%2 != 0) System.out.print("   ");
                        else System.out.print("## ");
                    } else {
                        String colorPre = "w";
                        if (!this.getSquare(x, y).getPiece().isWhite()) colorPre = "b";
                        if (this.getSquare(x, y).getPiece() instanceof Rook) System.out.print(colorPre + "R ");
                        else if (this.getSquare(x, y).getPiece() instanceof Knight) System.out.print(colorPre + "N ");
                        else if (this.getSquare(x, y).getPiece() instanceof Bishop) System.out.print(colorPre + "B ");
                        else if (this.getSquare(x, y).getPiece() instanceof Queen) System.out.print(colorPre + "Q ");
                        else if (this.getSquare(x, y).getPiece() instanceof King) System.out.print(colorPre + "K ");
                        else if (this.getSquare(x, y).getPiece() instanceof Pawn) System.out.print(colorPre + "p ");
                    }
                }
            }
            System.out.println();
        }
        
    }
}