package pieces;
import board.*;

/**
 * Represents a king chess piece
 */
public class King extends Piece { 

    private boolean hasMoved = false; 
  
    public King(boolean white) 
    { 
        super(white); 
    } 
  
    public boolean gethasMoved() 
    { 
        return this.hasMoved; 
    } 
  
    public void sethasMoved(boolean hasMoved) 
    { 
        this.hasMoved = hasMoved; 
    } 
  
    @Override
    public boolean canMove(Board board, Square start, Square end) 
    { 
        if (end.getPiece().isWhite() == this.isWhite()) return false;
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        return x + y == 1;
    } 
  
    //Need to implement castling
} 