package pieces;

import board.*;

/**
 * The King class represents a chess piece with functionality to track if it has
 * moved and validate its
 * movement on a chess board.
 */
public class King extends Piece {

    private boolean hasMoved = false;

    /**
     * {@inheritDoc}
     */
    public King(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function `gethasMoved()` returns the value of the `hasMoved` boolean
     * variable.
     * 
     * @return The method `gethasMoved()` is returning the value of the `hasMoved`
     *         instance variable.
     */
    public boolean gethasMoved() {
        return this.hasMoved;
    }

    /**
     * The function `sethasMoved` sets the value of the `hasMoved` boolean variable.
     * 
     * @param hasMoved The `sethasMoved` method is a setter method used to update
     *                 the `hasMoved`
     *                 attribute of an object. The `hasMoved` parameter is a boolean
     *                 value that indicates whether the
     *                 object has moved or not. By calling this method with a
     *                 boolean value, you can update the
     *                 `hasMoved
     */
    public void sethasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * The function checks if a move from a starting position to an ending position
     * on a board is valid
     * by ensuring that the difference in x or y coordinates is exactly 1.
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) == 1 || Math.abs(startY - endY) == 1;
    }

    // Need to implement castling

    /**
     * This function returns the symbol of the king piece.
     */
    @Override
    public String getSymbol() {
        return "\u2654";
    }
}