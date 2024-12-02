package pieces;

import board.*;

/**
 * The Knight class represents a knight chess piece
 */
public class Knight extends Piece {
    /**
     * {@inheritDoc}
     */
    public Knight(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }


    /**
     * The function checks if a move on a chess board is valid for a knight
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return (Math.abs(startX - endX) * Math.abs(startY - endY)) == 2;
    }


    /**
     * This function returns the symbol of the knight piece.
     */
    @Override
    public String getSymbol() {
        return "\u2658";
    }
}
