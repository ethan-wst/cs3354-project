package pieces;

import board.*;

/**
 * The `Pawn` class in Java represents a pawn chess piece with methods to
 * validate its moves on a
 * board.
 */
public class Pawn extends Piece {
    /**
     * {@inheritdoc}
     */
    public Pawn(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function `validMove` in Java checks if a move is valid for a pawn on a
     * chess board,
     * considering en passant, capturing diagonally, and regular moves.
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        Piece piece = board.getSquare(startX, startY).getPiece();
        int offset = -1;
        if (piece.isWhite())
            offset = 1;

        // en passant (can move two squares if not previosly moved)
        if (!piece.hasMoved() && endY - startY == 2 * offset && endX - endX == 0) {
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