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
    private Player localP1;
    private Player localP2;

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
        this.localP1 = p1;
        this.localP2 = p2;

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

        if(!validateMove(p, mv, true)) {
            p.removeCurrentMove();
            return false;
        }

        // check and change the state on spot
        piece.setPosition(mv.getEndX(), mv.getEndY());
        piece.setMoved(true);
        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(piece);

        Player opponent = (p == localP1) ? localP2 : localP1;
        opponent.setChecked(isCheck(opponent, p, true));

        if(isCheckmate(opponent, p)) win = true;

        return true;
    }

    private boolean validateMove(Player p, Move mv, boolean outputError) {
        Piece piece = mv.getPiece();

        if (!isValidCord(mv.getStartX(), mv.getStartY()) && !isValidCord(mv.getEndX(), mv.getEndY())) {
            if(outputError) System.out.println("One or more inputed coordinates are invalid!");
            return false;
        }

        if (piece == null) {
            if(outputError) System.out.println("No " + (p.white ? "white" : "black") + " piece on inputted starting coordinate!");
            return false;
        }

        // check the move step is valid for piece
        if (!piece.validMove(this, mv.getStartX(), mv.getStartY(), mv.getEndX(), mv.getEndY())) {
            // if not valid mv remove the move and return false
            if(outputError) System.out.println("Invalid move, that's not how a " + piece.getType() + " works");
            return false;

        }

        // check that target square is not occupied by friendly piece
        Piece targetPiece = chessBoard[mv.getEndY()][mv.getEndY()].getPiece();
        if (targetPiece != null && targetPiece.isWhite() == piece.isWhite()) {
                if(outputError) System.out.println("Targer square is occupied by a friendly piece");
                return false;
        }

        if(p.isChecked()) {
            Piece capturedPiece = testMove(mv);
            boolean kingStillChecked = isCheck(p, (p == localP1) ? localP2 : localP1, false);

            undoTestMove(mv, capturedPiece);

            if(kingStillChecked) {
                if(outputError)System.out.println("Invalid move, king would remain in check.");
                return false;
            }
        }
        
        return true;
    }

    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < chessBoard.length && y >= 0 && y < chessBoard[0].length;
    }

    public boolean isCheck(Player p1, Player p2, boolean outputError) {
        Piece king = null;
        for (Piece piece : p1.getPieces()) {
            if (piece instanceof King) {
                king = piece;
                break;
            }
        }

        if (king == null) {
            System.err.println("No king found for " + (p1.white ? "white" : "black") + " player!");
            return false;
        }


        for(Piece p2Piece : p2.getPieces()) {
            if (p2Piece.validMove(this, p2Piece.getX(), p2Piece.getY(), king.getX(), king.getY())) {
                if(outputError) System.out.println("Check on " + (p1.isWhite() ? "white" : "black") + " king.");
                return true;
            }
        }

        return false;
    }

    public boolean isCheckmate(Player playerInCheck, Player opponent) {
        if (!isCheck(playerInCheck, opponent, false)) {
            return false;
        }

        for (Piece piece : playerInCheck.getPieces()) {
            if (!piece.isAlive()) break;
            int startX = piece.getX();
            int startY = piece.getY();
        

            for (int endX = 0; endX < chessBoard.length; endX++) {
                for (int endY = 0; endY < chessBoard[0].length; endY++) {

                    Move testMove = new Move(piece, startX, startY, endX, endY);
                    ///System.out.println(piece);
                   // System.out.println(testMove.getStartX() + ", " + testMove.getStartY() + " : " + testMove.getEndX() + ", " + testMove.getEndY());

                    if(validateMove(playerInCheck, testMove, false)) {
                        Piece capturedPiece = testMove(testMove);

                        boolean stillChecked = isCheck(playerInCheck, opponent, false);

                        undoTestMove(testMove, capturedPiece);

                        if(!stillChecked) {

                            
                            //System.out.println(testMove.getStartX() + ", " + testMove.getStartY() + " : " + testMove.getEndX() + ", " + testMove.getEndY());
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("Checkmate! " + (playerInCheck.isWhite() ? "White" : "Black") + " loses.");
        return true;
    }

    private Piece testMove(Move mv) {
        Piece movingPiece = mv.getPiece();
        Piece capturedPiece = chessBoard[mv.getEndX()][mv.getEndY()].getPiece();

        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(movingPiece);

        movingPiece.setPosition(mv.getEndX(), mv.getEndY());

        return capturedPiece;
    }

    private void undoTestMove(Move mv, Piece capturedPiece) {
        Piece movingPiece = mv.getPiece();
    
        // Restore the piece's original position
        chessBoard[mv.getStartX()][mv.getStartY()].occupySquare(movingPiece);
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(capturedPiece);
    
        // Reset the piece's position
        movingPiece.setPosition(mv.getStartX(), mv.getStartY());
    }
}
