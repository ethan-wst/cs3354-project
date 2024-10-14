package pieces;
import board.*;

public class Knight extends Piece { 
    public Knight(boolean white) 
    { 
        super(white); 
    } 
  
    @Override
    public boolean canMove(Board board, Square start, Square end) 
    { 
        // End square is occupied by same color
        if (end.getPiece().isWhite() == this.isWhite()) { 
            return false; 
        } 
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        return x * y == 2; 
    } 
} 
