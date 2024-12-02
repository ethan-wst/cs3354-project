package pieces;

import board.*;

/**
 * The King class represents a king chess piece
 */
public class King extends Piece {
    /**
     * {@inheritDoc}
     */
    public King(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }


    /**
     * The function checks if a move on a chess board is valid for a king
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1 && (startX != endX || startY != endY);
    }


    /**
     * This function returns the symbol of the king piece.
     */
    @Override
    public String getSymbol() {
        return "\u2654";
    }

}