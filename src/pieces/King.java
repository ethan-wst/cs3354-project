package pieces;
import board.*;

/**
 * Represents a king chess piece
 */
public class King extends Piece { 
    private boolean castlingDone = false; 
  
    public King(boolean white) 
    { 
        super(white); 
    } 
  
    public boolean isCastlingDone() 
    { 
        return this.castlingDone; 
    } 
  
    public void setCastlingDone(boolean castlingDone) 
    { 
        this.castlingDone = castlingDone; 
    } 
  
    @Override
    public boolean canMove(Board board, Square start, Square end) 
    { 
        if (end.getPiece().isWhite() == this.isWhite()) return false;
  
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        if (x + y == 1) { 
            // need to implement check check (king cannot move into a position where it is in check)
            // potential utility class to check if moving a piece to a certain square leaves it
            // vunerable to opponent.
            return true; 
        } 
  
        return this.isValidCastling(board, start, end); 
    } 
  
    private boolean isValidCastling(Board board, Square start, Square end) 
    { 
        if (this.isCastlingDone()) { 
            return false; 
        } 
        return true;

        
        // Need to implement logic for valid king/queen side castling
    } 
  
    public boolean isCastlingMove(Square start, Square end) 
    { 
        // Need to implement logic for valid king/queen side castling
        return true;
    } 
} 