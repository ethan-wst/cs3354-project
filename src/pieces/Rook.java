package pieces;

import board.*;

/**
 * The Rook class represents a rook chess piece with methods to validate its
 * movement on a board.
 */
public class Rook extends Piece {
    /**
     * {@inheritDoc}
     */
    public Rook(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * This Java function checks if a move on a board is valid for a piece that can
     * move horizontally
     * or vertically without jumping over other pieces.
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        int offset = 1;
        if (startX != endX && startY == endY) {
            if (endX < startX)
                offset = -1;
            for (int x = startX + offset; x != endX; x += offset) {
                if (board.getSquare(x, startY).getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else if (startX == endX && startY != endY) {
            if (endY < startY)
                offset = -1;
            for (int y = startY + offset; y != endY; y += offset) {
                if (board.getSquare(startX, y).getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This function returns the symbol of the rook piece.
     */
    @Override
    public String getSymbol() {
        return "\u265C";
    }
}
