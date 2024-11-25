package pieces;

import board.*;

/**
 * The King class represents a chess piece with functionality to track if it has
 * moved and validate its
 * movement on a chess board.
 */
public class King extends Piece {

    /**
     * {@inheritDoc}
     */
    public King(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }


    /**
     * The function checks if a move from a starting position to an ending position
     * on a board is valid
     * by ensuring that the difference in x or y coordinates is exactly 1.
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