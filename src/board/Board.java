package board;

import game.*;
import pieces.*;

/**
 * The `Board` class represents a chess board.
 */
public class Board {
    /** 
    * The `private final Square[][] chessBoard;` declaration creates a 2D array
    * named `chessBoard` to represent the chess board. Each element in the array 
    * corresponds to a square on the chess board and holds a `Square` object.
    */
    private final Square[][] chessBoard;
    private boolean win;
    private Player localP1;
    private Player localP2;

    /**
    * Initializes a new instance of the `Board` object.
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
     * Returns the Square object at the specified coordinates.
     * *
     * @param x represents the column number on the chess board.
     * @param y represents the row number on the chess board.
     * @return A `Square` object located at the specified coordinates `(x, y)`
     */
    public Square getSquare(int x, int y) {
        return chessBoard[x][y];
    }

    /**
     * Executes a player's move in a chess game.
     * 
     * @param p represents a `Player` object. 
     * @return returns a boolean value - `true` if the move was successfully
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

    /**
     * Validates the a Player's move in a chess game.
     * @param p represents a 'Player' object.
     * @param mv represents a 'Move' object and is what will be validated.
     * @param outputError represents a boolean value. If true, the method will output an error message if the move is invalid.
     * @return returns a boolean value - `true` if the move is valid, and `false` if the move is invalid.
     */
    private boolean validateMove(Player p, Move mv, boolean outputError) {
        Piece piece = mv.getPiece();

        if (!isValidCord(mv.getStartX(), mv.getStartY()) && !isValidCord(mv.getEndX(), mv.getEndY())) {
            if(outputError) System.out.println("One or more inputed coordinates are invalid!");
            return false;
        }

        if (piece == null || piece.isWhite() != p.white) {
            if(outputError) System.out.println("No " + (p.white ? "white" : "black") + " piece on inputted starting coordinate!");
            return false;
        }

        // if(piece.isWhite() != p.white) {
        //     if(outputError) System.out.println("No + ")
        // }

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

    /**
     * Checks if the given coordinates are valid.
     * @param x represents the column number on the chess board.
     * @param y represents the row number on the chess board.
     * @return returns a boolean value - `true` if the coordinates are valid, and `false` if the coordinates are invalid.
     */
    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < chessBoard.length && y >= 0 && y < chessBoard[0].length;
    }

    /**
     * Checks if the given player is in check.
     * @param p1 represents a 'Player' object.
     * @param p2 represents a 'Player' object.
     * @param outputError represents a boolean value. If true, the method will output an error message if the player is in check.
     * @return returns a boolean value - `true` if the player is in check, and `false` if the player is not in check.
     */
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

    /**
     * Checks if the given player is in checkmate.
     * @param playerInCheck represents a 'Player' object.
     * @param opponent represents a 'Player' object.
     * @return returns a boolean value - `true` if the player is in checkmate, and `false` if the player is not in checkmate.
     */
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

    /**
     * Tests a move on the board without actually executing it.
     * @param mv represents a 'Move' object.
     * @return returns a 'Piece' object of what would the hypothetical move captured.
     */
    private Piece testMove(Move mv) {
        Piece movingPiece = mv.getPiece();
        Piece capturedPiece = chessBoard[mv.getEndX()][mv.getEndY()].getPiece();

        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(movingPiece);

        movingPiece.setPosition(mv.getEndX(), mv.getEndY());

        return capturedPiece;
    }

    /**
     * Undoes a test move on the board.
     * @param mv represents a 'Move' object.
     * @param capturedPiece represents a 'Piece' object.
     */
    private void undoTestMove(Move mv, Piece capturedPiece) {
        Piece movingPiece = mv.getPiece();
    
        // Restore the piece's original position
        chessBoard[mv.getStartX()][mv.getStartY()].occupySquare(movingPiece);
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(capturedPiece);
    
        // Reset the piece's position
        movingPiece.setPosition(mv.getStartX(), mv.getStartY());
    }

    /**
     * Moves a piece on the board, whitout validating the move's logic.
     * @param startX represents the starting x-coordinate.
     * @param startY represents the starting y-coordinate.
     * @param endX represents the ending x-coordinate.
     * @param endY represents the ending y-coordinate.
     * @return returns a 'Piece' object of what the move captured.
     */
    public Piece movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = chessBoard[startX][startY].getPiece(); // Get the piece to move
        if (piece != null) {
            piece.setPosition(endX, endY); // Update piece's position
            chessBoard[startX][startY].releaseSquare(); // Clear the starting square
            Piece taken = chessBoard[endX][endY].occupySquare(piece); // Occupy the destination square with the piece
            return taken; // Return the taken piece
        }
        return null;
    }
}
