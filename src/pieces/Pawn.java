package pieces;
import board.*;

/**
 * Represents a pawn chess piece
 */
public class Pawn extends Piece {

    public boolean hasMoved = false;

    /**
     * {@inheritdoc}
     */
    public Pawn(boolean white) {
        super(white);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        if (end.getPiece().isWhite() == this.isWhite()) return false;
        
        if (start.getPiece().isWhite()) {
            // White pawn en passant (move 2 squares if at starting row)
            if (!hasMoved) {
                for (int i = start.getY(); i <= end.getY(); i ++) {
                    if (board.getSquare(start.getX(), i).getPiece() != null) return false; 
                }
                return true;
            //White pawn regular move
            } else if (start.getY() + 1 == end.getY()) {
                if (board.getSquare(start.getX(), start.getY()+1).getPiece() == null) return true;
            }
        }

        if (!start.getPiece().isWhite()) {
            // Black pawn en passant (move 2 squares if at starting row)
            if (!hasMoved) {
                for (int i = end.getY(); i >= start.getY(); i--) {
                    if (board.getSquare(start.getX(), i).getPiece() != null) return false; 
                }
                return true;
            // Black pawn regular move
            } else if (start.getY() - 1 == end.getY()) {
                if (board.getSquare(start.getX(), start.getY()-1).getPiece() == null) return true;
            }
        }
        return false;

        //Need to implement promotion
    }
}
