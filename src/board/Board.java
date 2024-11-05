package board;

import game.*;
import pieces.*;

/**
 * The `Board` class represents a chess board with methods to initialize pieces,
 * execute moves, check
 * for wins, and display the board state.
 */
public class Board {
    /** 
    * The `private final Square[][] chessBoard;` declaration creates a 2D array
    * named `chessBoard` to
    * represent the chess board. Each element in the array corresponds to a square
    * on the chess board
    * and holds a `Square` object.
    */
    private final Square[][] chessBoard;
    private boolean win;

    /**
    * The `public Board()` constructor in the `Board` class is initializing a new
    * instance of the
    * `Board` object. Here's what it's doing:
    */
    public Board() {
        win = false;
        chessBoard = new Square[8][8];
    }

    /**
     * Initializes white's and black's pieces onto the board object, as well as null
     * squares
     * 
     * @param p1 Player one
     * @param p2 Player two
     */
    public void initialize(Player p1, Player p2) {
        for (int i = 0; i < p1.getPieces().size(); i++) {
            // White's pieces
            chessBoard[p1.getPieces().get(i).getX()][p1.getPieces().get(i).getY()] = new Square(p1.getPieces().get(i));
            // Black's pieces
            chessBoard[p2.getPieces().get(i).getX()][p2.getPieces().get(i).getY()] = new Square(p2.getPieces().get(i));
        }
        // Empty Squares
        for (int x = 0; x < 8; x++) {
            for (int y = 2; y < 6; y++) {
                chessBoard[x][y] = new Square(null);
            }
        }
    }

    /**
     * This Java function executes a player's move in a chess game, checking for
     * valid moves, piece
     * presence, target square occupancy, and updating the game state accordingly.
     * 
     * @param p The parameter `p` in the `executeMove` method represents a `Player`
     *          object. This method is
     *          responsible for executing a move for the player in a chess game. The
     *          method retrieves the current
     *          move from the player, validates the move, checks if the move is
     *          valid for the piece being moved
     * @return The `executeMove` method returns a boolean value - `true` if the move
     *         was successfully
     *         executed, and `false` if there was an issue with the move execution.
     */
    public boolean executeMove(Player p) {
        Move mv = p.getCurrentMove();
        Piece piece = mv.getPiece();

        if (mv.startX == -1 || mv.startY == -1 || mv.startX == -1 || mv.startY == -1) {
            p.removeCurrentMove();
            System.out.println("One or more inputed coordinates are invalid!");
            return false;
        }

        if (piece == null) {
            p.removeCurrentMove();
            if (p.white)
                System.out.println("No white piece on inputed starting coordinate!");
            else
                System.out.println("No black piece on inputed starting coordinate!");
            return false;
        }

        // check the move step is valid for piece
        if (!piece.validMove(this, mv.startX, mv.startY, mv.endX, mv.endY)) {
            // if not valid mv remove the move and return false
            p.removeCurrentMove();
            String pieceString = (piece.toString().split("[.]")[1]).split("@")[0].toLowerCase();

            System.out.println("Invalid move, that's not how a " + pieceString + " works");
            return false;

        }

        // check that target square is not occupied by friendly piece
        if (chessBoard[mv.endX][mv.endY].getPiece() != null) {
            if (chessBoard[mv.endX][mv.endY].getPiece().isWhite() == piece.isWhite()) {
                p.removeCurrentMove();
                System.out.println("Targer square is occupied by a friendly piece");
                return false;
            }
        }

        // check and change the state on spot
        Piece taken = chessBoard[mv.endX][mv.endY].occupySquare(piece);
        piece.setX(mv.endX);
        piece.setY(mv.endY);
        piece.setMoved(true);
        
        if (taken != null && taken.getClass().getName().equals("pieces.King")) {
            win = true;
        }
        chessBoard[mv.startX][mv.startY].releaseSquare();

        return true;
    }

    public boolean isCheck(Player p1, Player p2) {
        for(Piece p1Piece : p1.getPieces()) {
            if (p1Piece.getClass().getName().equals("pieces.King")) {
                for(Piece p2Piece : p2.getPieces()) {
                    if (p2Piece.validMove(this, p2Piece.getX(), p2Piece.getY(), p1Piece.getX(), p1Piece.getY())) {
                        isCheckmate(p1, p2, p1Piece, p2Piece);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Player p1, Player p2, Piece King, Piece threat) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; x++) {
                if (threat.validMove(this, threat.getX(), threat.getY(), x, y) && threat.validMove(this, x, y,  King.getX(), King.getY())) {
                    
                }
            }
        }
        return true;
    }

    /**
     * Accsessor Method for win parameter
     * 
     * @return win
     */
    public boolean getWin() {
        return win;
    }

    /**
     * The getSquare function in Java returns the Square object at the specified
     * coordinates on a chess
     * board.
     * 
     * @param x The parameter `x` represents the row number on the chess board.
     * @param y The `y` parameter in the `getSquare` method represents the vertical
     *          position on the
     *          chess board. It is used to specify the row of the square you want to
     *          retrieve.
     * @return A `Square` object located at the specified coordinates `(x, y)` on
     *         the `chessBoard` is
     *         being returned.
     */
    public Square getSquare(int x, int y) {
        return chessBoard[x][y];
    }

    /**
     * The `display` method prints out a visual representation of a chess board with
     * pieces represented
     * by letters and symbols.
     */
    public void display() {
        for (int y = 8; y >= 0; y--) {
            if (y == 8)
                System.out.print("\n   ");
            else
                System.out.print((y + 1) + "  ");
            for (int x = 0; x < 8; x++) {
                if (y == 8)
                    System.out.print((char) (x + 65) + "  ");
                else {

                    if (chessBoard[x][y].getPiece() == null) {
                        if ((x + y) % 2 != 0)
                            System.out.print("   ");
                        else
                            System.out.print("## ");
                    } else {
                        String colorPre = "w";
                        if (!chessBoard[x][y].getPiece().isWhite())
                            colorPre = "b";
                        if (chessBoard[x][y].getPiece() instanceof Rook)
                            System.out.print(colorPre + "R ");
                        else if (chessBoard[x][y].getPiece() instanceof Knight)
                            System.out.print(colorPre + "N ");
                        else if (chessBoard[x][y].getPiece() instanceof Bishop)
                            System.out.print(colorPre + "B ");
                        else if (chessBoard[x][y].getPiece() instanceof Queen)
                            System.out.print(colorPre + "Q ");
                        else if (chessBoard[x][y].getPiece() instanceof King)
                            System.out.print(colorPre + "K ");
                        else if (chessBoard[x][y].getPiece() instanceof Pawn)
                            System.out.print(colorPre + "p ");
                    }
                }
            }
            System.out.println();
        }

    }
}