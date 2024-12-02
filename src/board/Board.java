package board;

import game.*;
import pieces.*;

/**
 * The `Board` class represents a chess board.
 */
public class Board {
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
     * Initializes white's and black's pieces onto the board object, as well as null squares
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
     * Accessor method for win parameter
     * 
     * @return win
     */
    public boolean getWin() {
        return win;
    }


    /**
     * Accessor method for the Square object at a specified coordinates.
     * 
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
     * @param p represents the 'Player' who's turn it is. 
     * @return returns a boolean value - `true` if the move was successfully
     *         executed, and `false` if there was an issue with the move execution.
     */
    public boolean executeMove(Player p) {
        Move mv = p.getCurrentMove();
        Piece piece = mv.getPiece();

        // Check if the move is a castle
        if (isCastle(p, mv, true)) {
            Castle(mv);
            return true;
        }

        // Check if the move is valid
        if(!validateMove(p, mv, true)) {
            p.removeCurrentMove();
            return false;
        }

        // Change the state and position of the moving piece and taken piece if any
        piece.setPosition(mv.getEndX(), mv.getEndY());
        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        piece.setMoved(true);
        Piece taken = chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(piece);
        if (taken != null) taken.setAlive(false);
        
        // Check if the move puts the player in check or checkmate
        Player opponent = (p == localP1) ? localP2 : localP1;
        opponent.setChecked(isCheck(opponent, p, win));
        p.setChecked(isCheck(p, opponent, win));
        if (isCheckmate(opponent, p, false)) win = true;

        return true;
    }


    /**
     * Validates the a Player's move in a chess game.
     * 
     * @param p represents tje 'Player' object that is making the move.
     * @param mv represents a 'Move' object and is what will be validated.
     * @param outputError represents a boolean value. If true, the method will output an error message if the move is invalid.
     * @return returns a boolean value - `true` if the move is valid, and `false` if the move is invalid.
     */
    private boolean validateMove(Player p, Move mv, boolean outputError) {
        Piece piece = mv.getPiece();

        // Check if the move is within the board
        if (!isValidCord(mv.getStartX(), mv.getStartY()) && !isValidCord(mv.getEndX(), mv.getEndY())) {
            if(outputError) System.out.println("One or more inputed coordinates are invalid!");
            return false;
        }

        // Check there is a piece of the correct color on the starting square
        if (piece == null || piece.isWhite() != p.white) {
            if(outputError) System.out.println("No " + (p.white ? "white" : "black") + " piece on inputted starting coordinate!");
            return false;
        }

        // Check the move is valid for the piece type
        if (!piece.validMove(this, mv.getStartX(), mv.getStartY(), mv.getEndX(), mv.getEndY())) {
            if(outputError) System.out.println("Invalid move, that's not how a " + piece.getType() + " works");
            return false;
        }

        // Check the target square is not occupied by friendly piece
        Piece targetPiece = chessBoard[mv.getEndX()][mv.getEndY()].getPiece();
        if (targetPiece != null && targetPiece.isWhite() == piece.isWhite()) {
                if(outputError) System.out.println("Targer square is occupied by a friendly piece");
                return false;
        }

        /*
        * Checks the move would not place the player's king in check, 
        * or if the player is already in check, that the move would not leave the king in check.
        */ 
        Piece capturedPiece = testMove(mv);
        boolean kingInCheck = isCheck(p, (p == localP1) ? localP2 : localP1, false);
        undoTestMove(mv, capturedPiece);
        if(kingInCheck) {
            if (p.checked) {
                if(outputError) System.out.println("Invalid move, king would still be checked.");
                return false;
            } else {
                if(outputError) System.out.println("Invalid move, king would be checked.");
                return false;
            }
        }

        return true;
    }


    /**
     * Checks if the given player is in check.
     * 
     * @param p1 represents a 'Player' object.
     * @param p2 represents a 'Player' object.
     * @param outputError represents a boolean value. If true, the method will output an error message if the player is in check.
     * @return returns a boolean value - `true` if the player is in check, and `false` if the player is not in check.
     */
    public boolean isCheck(Player p1, Player p2, boolean outputError) {
        // Gets the king piece of the player in question
        Piece king = p1.getKing();
        if (king == null) {
            if(outputError) System.out.println("No king found for player");
            return false;
        }

        // Checks if any of the opponent's pieces can move to the king's square
        for (Piece piece : p2.getPieces()) {
            if (piece.validMove(this, piece.getX(), piece.getY(), king.getX(), king.getY())) {
                if(outputError) System.out.println("Check on " + (p1.isWhite() ? "white" : "black") + " king.");
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the given player is in checkmate.
     * 
     * @param p1 represents a 'Player' object.
     * @param p2 represents a 'Player' object.
     * @param outputError represents a boolean value. If true, the method will output an error message if the player is in checkmate.
     * @return returns a boolean value - `true` if the player is in checkmate, and `false` if the player is not in checkmate.
     */
    public boolean isCheckmate(Player p1, Player p2, boolean outputError) {
        // If the player is not in check, they cannot be checkmated
        if (!isCheck(p1, p2, false)) return false;

        // Itterates through all the player's pieces and checks if any of them can move to a square that would remove the check
        for (Piece piece : p1.getPieces()) {
            // Skip the piece if it is dead
            if (!piece.isAlive()) continue;
            // Itterates through all the squares on the board
            for (int endX = 0; endX < 8; endX++) {
                for (int endY = 0; endY < 8; endY++) {
                    // Checks if the piece can move to the square
                    Move testMove = new Move(piece, piece.getX(), piece.getY(), endX, endY);
                    if (outputError) System.out.println("Testing move: " + piece.getType() + " from " + piece.getX() + ", " + piece.getY() + " to " + endX + ", " + endY);
                    if (validateMove(p1, testMove, false)) {
                        // Checks if move would remove the check on the player's king
                        Piece capturedPiece = testMove(testMove);
                        if (!isCheck(p1, p2, false)) {
                            undoTestMove(testMove, capturedPiece);
                            return false;
                        }
                        undoTestMove(testMove, capturedPiece);
                    }
                }
            }
        }   
        return true;
    }

    
    /**
     * Checks if the move is a valid castle move.
     * 
     * @param p represents a 'Player' object that is making the move.
     * @param mv represents a 'Move' object that is being checked.
     * @param outputError represents a boolean value. If true, the method will output an error message if the move is invalid.
     * @return returns a boolean value - `true` if the move is a valid castle move, and `false` if the move is not a valid castle move.
     */
    private boolean isCastle(Player p, Move mv, boolean outputError) {
        // Gets the king and rook pieces
        Piece kingPiece = mv.getPiece();
        Piece rookPiece = chessBoard[mv.getEndX()][mv.getEndY()].getPiece();

        // Checks if the pieces are the correct type and color
        if (kingPiece == null || rookPiece == null) return false;
        if (!(kingPiece instanceof King) || !(rookPiece instanceof Rook)) return false;
        if (kingPiece.isWhite() != p.isWhite() || rookPiece.isWhite() != p.isWhite()) return false;

        // Checks if the king and rook have moved, if so the castle is invalid
        if (kingPiece.hasMoved() || rookPiece.hasMoved()) {
            if (outputError) System.out.println("Unable to castle, either the king or rook has moved!");
            return false;
        }

        // Checks if the player attempting to castle is in check, if so the castle is invalid
        if (p.checked) {
            if (outputError) System.out.println("Unable to castle, cannot castle when in check!");
            return false;
        }

        // Itterates through all the squares between the king and rook
        int offset = (mv.getEndX() > mv.getStartX()) ? 1 : -1;
        for (int x = mv.getStartX() + offset; x != mv.getEndX(); x += offset) {
            // Checks if any of the squares are occupied, if so the castle is invalid
            if (chessBoard[x][mv.getStartY()].getPiece() != null) {
                if (outputError) System.out.println("Unable to castle, all squares between king and rook must be empty!");
                return false;
            }

            // Checks if any of the squares are under attack, if so the castle is invalid
            kingPiece.setPosition(x - offset, mv.getStartY());
            Move testMove = new Move(kingPiece, kingPiece.getX(), mv.getStartY(), x, mv.getStartY());
            testMove(testMove);
            if (isCheck(p, (p == localP1) ? localP2 : localP1, false)) {
                if (outputError) System.out.println("Unable to castle, a square the king would move through is under attack");
                undoTestMove(testMove, null);
                return false;
            }
            undoTestMove(testMove, null);
        }

        return true;
    }


    /**
     * Executes a castle move on the board.
     * 
     * @param mv represents a 'Move' object, that is a castle move.
     */
    public void Castle(Move mv) {
        // Assigns the king and rook pieces, and determines if king or queen side castle
        int offset = (mv.getEndX() > mv.getStartX()) ? 1 : -1;
        Piece rook = chessBoard[mv.getEndX()][mv.getEndY()].getPiece();
        Piece king = chessBoard[mv.getStartX()][mv.getStartY()].getPiece();
        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        chessBoard[mv.getEndX()][mv.getEndY()].releaseSquare();

            // King side castle
            if (offset == 1) {
                chessBoard[6][mv.getEndY()].occupySquare(king);
                chessBoard[5][mv.getStartY()].occupySquare(rook);
                rook.setPosition(5, mv.getStartY());
                king.setPosition(6, mv.getStartY());
            // Queen side castle
            } else {
                chessBoard[2][mv.getEndY()].occupySquare(king);
                chessBoard[3][mv.getStartY()].occupySquare(rook);
                rook.setPosition(3, mv.getStartY());
                king.setPosition(2, mv.getStartY());
            }
    }


    /**
     * Tests a move on the board
     * 
     * @param mv represents a 'Move' object.
     * @return returns a 'Piece' object of what would the hypothetical move captured.
     */
    private Piece testMove(Move mv) {
        Piece movingPiece = mv.getPiece();

        // Move the piece to the target square and notes what piece was captured if any
        movingPiece.setPosition(mv.getEndX(), mv.getEndY());
        chessBoard[mv.getStartX()][mv.getStartY()].releaseSquare();
        Piece capturedPiece = chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(movingPiece);
        if (capturedPiece != null) capturedPiece.setAlive(false);

        return capturedPiece;
    }


    /**
     * Undoes a test move on the board.
     * 
     * @param mv represents a 'Move' object.
     * @param capturedPiece represents a 'Piece' object.
     */
    private void undoTestMove(Move mv, Piece capturedPiece) {
        Piece movingPiece = mv.getPiece();
    
        // Restore the piece's original position
        chessBoard[mv.getStartX()][mv.getStartY()].occupySquare(movingPiece);
        movingPiece.setPosition(mv.getStartX(), mv.getStartY());
        
        // Restore the captured piece, if any
        chessBoard[mv.getEndX()][mv.getEndY()].occupySquare(capturedPiece);
        if (capturedPiece != null) {
            capturedPiece.setPosition(mv.getEndX(), mv.getEndY());
            capturedPiece.setAlive(true);
        }
        
    }

    
    /**
     * Checks if the given coordinates are valid.
     * 
     * @param x represents the column number on the chess board.
     * @param y represents the row number on the chess board.
     * @return returns a boolean value - `true` if the coordinates are valid, and `false` if the coordinates are invalid.
     */
    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < chessBoard.length && y >= 0 && y < chessBoard[0].length;
    }


    /**
     * Displays the current state of the chess board, for use in debugging of the GUI
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
