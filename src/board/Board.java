package board;
import game.Move;
import game.Player;
import pieces.*;

/**
 * Represents the board
 */
public class Board {
    private final Square[][] chessBoard;
    private boolean win;

    /**
     * Creates a new board object
     */
    public Board() {
        win = false;
        chessBoard = new Square[8][8];
    }

    /**
     * Initializes white's and black's pieces onto the board object, as well as null squares
     * @param p1 Player one
     * @param p2 Player two
     */

    public void initialize(Player p1, Player p2) {
        for (int i = 0; i < p1.getPieces().size(); i++) {
            //White's pieces
            chessBoard[p1.getPieces().get(i).getX()][p1.getPieces().get(i).getY()] = new Square(p1.getPieces().get(i));
            //Black's pieces
            chessBoard[p2.getPieces().get(i).getX()][p2.getPieces().get(i).getY()] = new Square(p2.getPieces().get(i));
        }
        //Empty Squares
        for (int x = 0; x < 8; x++) {
            for (int y = 2; y < 6; y++) {
                chessBoard[x][y] = new Square(null);
            }
        }
    }

    /**
     * Determines if a move is valid, and if so carries out the move
     * @param p Player making the move
     * @return True if move carried out, False otherwise
     */
    public boolean executeMove(Player p) {
        Move mv = p.getCurrentMove();
        Piece piece = mv.getPiece();

        //// check the move step is valid for piece
        if(!piece.validMove(this, mv.startX, mv.startY, mv.endX, mv.endY)) {
            // if not valid mv remove the move and return false
            p.removeCurrentMove();
            return false;
        }

        // check that target square is not occupied by friendly piece
        if(chessBoard[mv.endX][mv.endY] != null && chessBoard[mv.endX][mv.endY].getPiece().isWhite() == piece.isWhite())
            return false;

        // check and change the state on spot
        Piece taken = chessBoard[mv.endX][mv.endY].occupySquare(piece);
        if(taken != null && taken.getClass().getName().equals("King"))
            win = true;   
        chessBoard[mv.startX][mv.startY].releaseSquare();
        return true;
    }

    /**
     * Accsessor Method for win parameter
     * @return win
    */
    public boolean getWin() {
        return win;
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
                    
                    if (chessBoard[x][y].getPiece() == null) {
                        if ((x+y)%2 != 0) System.out.print("   ");
                        else System.out.print("## ");
                    } else {
                        String colorPre = "w";
                        if (!chessBoard[x][y].getPiece().isWhite()) colorPre = "b";
                        if (chessBoard[x][y].getPiece() instanceof Rook) System.out.print(colorPre + "R ");
                        else if (chessBoard[x][y].getPiece() instanceof Knight) System.out.print(colorPre + "N ");
                        else if (chessBoard[x][y].getPiece() instanceof Bishop) System.out.print(colorPre + "B ");
                        else if (chessBoard[x][y].getPiece() instanceof Queen) System.out.print(colorPre + "Q ");
                        else if (chessBoard[x][y].getPiece() instanceof King) System.out.print(colorPre + "K ");
                        else if (chessBoard[x][y].getPiece() instanceof Pawn) System.out.print(colorPre + "p ");
                    }
                }
            }
            System.out.println();
        }
        
    }
}