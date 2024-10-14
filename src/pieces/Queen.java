package pieces;
import board.*;

public class Queen extends Piece{
    public Queen (boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        // End square is occupied by same color piece
        
        if (end.getPiece().isWhite() == this.isWhite()) return false;

        //Implement movement logic bishop + rook
        return false;
    }
    
}
