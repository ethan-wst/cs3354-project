package pieces;

import board.*;

/**
 * The Knight class represents a chess piece that can move in an L-shape on the
 * board.
 */
public class Knight extends Piece {
    /**
     * {@inheritDoc}
     */
    public Knight(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function determines if a move on a board is valid by checking if the
     * absolute difference
     * between start and end coordinates is equal to 2.
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return (Math.abs(startX - endX) * Math.abs(startY - endY)) == 2;
    }
}
