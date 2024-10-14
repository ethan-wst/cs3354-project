package pieces;
import board.*;
/**
 * Represents a rook chess piece
 */

public class Rook extends Piece {

    private boolean moved = false;

    public boolean getMoved () {
        return this.moved;
    }

    public void setMoved (boolean moved) {
        this.moved = moved;
    }

    /**
     * {@inheritDoc}
     */
    public Rook(boolean white) {
        super(white);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        if (end.getPiece().isWhite() == this.isWhite()) return false;
        else if (start.getY() == start.getY()) {
            if (start.getX() < end.getX()) {
                for (int i = start.getX(); i < end.getX(); i++) {
                    if (board.getSquare(i, start.getY()).getPiece() != null) return false;
                }
            } else {
                for (int i = end.getX(); i > start.getX(); i--) {
                    if (board.getSquare(i, start.getY()).getPiece() != null) return false;
                }
            }
            return true;
        } else if (start.getX() == end.getX()){
            if (start.getY() < end.getY()) {
                for (int i = start.getY(); i < end.getY(); i++) {
                    if (board.getSquare(start.getX(), i).getPiece() != null) return false;
                }
            } else {
                for (int i = end.getY(); i > start.getY(); i--) {
                    if (board.getSquare(start.getX(), i).getPiece() != null) return false;
                }
            }
            return true;
        }
        return false;
    }
}
