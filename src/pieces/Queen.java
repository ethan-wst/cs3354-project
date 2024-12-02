package pieces;

import board.*;

/**
 * The Queen class represents a queen chess piece
 */
public class Queen extends Piece {
    /**
     * {@inheritDoc}
     */
    public Queen(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function checks if a move on a chess board is valid for a queen
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return (new Rook(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX, endY)
                || new Bishop(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX, endY));
    }

    /**
     * This function returns the symbol of the queen piece.
     */
    @Override
    public String getSymbol() {
        return "\u2655";
    }
}
