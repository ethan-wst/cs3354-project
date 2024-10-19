package pieces;

import board.*;

/**
 * The Queen class represents a chess piece that can move like a Rook or a
 * Bishop on a chess board.
 */
public class Queen extends Piece {
    /**
     * {@inheritDoc}
     */
    public Queen(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    /**
     * The function determines if a move is valid for a piece by checking if it is a
     * valid move for a
     * Rook or a Bishop on a board.
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return new Rook(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX, endY)
                || new Bishop(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX,
                        endY);
    }

}
