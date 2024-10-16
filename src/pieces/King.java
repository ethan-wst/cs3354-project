package pieces;
import board.*;

/**
 * Represents a king chess piece
 */
public class King extends Piece { 

    private boolean hasMoved = false; 
  
    public King(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
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
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        // if (end.getPiece().isWhite() == this.isWhite()) return false;
  
        // int x = Math.abs(start.getX() - end.getX()); 
        // int y = Math.abs(start.getY() - end.getY()); 
        // return x + y == 1;
        return false;
    } 
  
    //Need to implement castling
} 