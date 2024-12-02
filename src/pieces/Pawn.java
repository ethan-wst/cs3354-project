package pieces;

import board.*;

/**
 * The Pawn class represents a pawn chess piece
 */
public class Pawn extends Piece {
    /**
     * {@inheritdoc}
     */
    public Pawn(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function checks if a move on a chess board is valid for a pawn
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {

        Piece piece = board.getSquare(startX, startY).getPiece();

        int offset = -1;
        if (piece.isWhite())
            offset = 1;

        // en passant (can move two squares if not previosly moved)
        if (!piece.hasMoved() && endY - startY == 2 * offset && startX - endX == 0) {
            if (board.getSquare(endX, endY).getPiece() == null
                    && board.getSquare(endX, endY - offset).getPiece() == null) {
                return true;
            }
        }
        // taking a piece diagnoly from you
        else if (endY - startY == offset && Math.abs(endX - startX) == 1
                && board.getSquare(endX, endY).getPiece() != null) {
            return true;
        }
        // regular move
        else if (endY - startY == offset && endX - startX == 0) {
            if (board.getSquare(endX, endY).getPiece() == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function returns the symbol of the pawn piece.
     */
    @Override
    public String getSymbol() {
        return "\u2659";
    }
}