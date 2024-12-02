package pieces;

import board.*;

/**
 * The Bishop class represents a bishop chess piece
 */
public class Bishop extends Piece {
    /**
     * {@inheritDoc}
     */
    public Bishop(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }


    /**
     * The function checks if a move on a chess board is valid for a bishop
     */
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        // Not a diagnol move b1 c2
        if (startX == endX || startY == endY)
            return false;
        // Not perfectly diagnol
        if (Math.abs(startX - endX) != Math.abs(startY - endY))
            return false;

        int xOffset = 1, yOffset = 1;

        if (startX > endX)
            xOffset = -1;
        if (startY > endY)
            yOffset = -1;

        for (int x = startX + xOffset, y = startY + yOffset; x != endX; x += xOffset) {
            if (board.getSquare(x, y).getPiece() != null) {
                return false;
            }
            y += yOffset;
        }
        return true;
    }


    /**
     * This function returns the symbol of the bishop piece.
     */
    @Override
    public String getSymbol() {
        return "\u265D";
    }
}
