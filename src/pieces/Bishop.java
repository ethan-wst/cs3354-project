package pieces;
import board.*;

public class Bishop extends Piece { 
    public Bishop(boolean white) 
    { 
        super(white); 
    } 
  
    @Override
    public boolean canMove(Board board, Square start, Square end) 
    { 
        if (end.getPiece().isWhite() == this.isWhite()) return false;
        // Need to implement movement logic
        return false;
    } 
} 
