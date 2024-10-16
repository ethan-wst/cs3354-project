package pieces;
import board.*;

public class Knight extends Piece { 
    public Knight(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    } 
  
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        // // End square is occupied by same color
        // if (end.getPiece().isWhite() == this.isWhite()) { 
        //     return false; 
        // } 
  
        // int x = Math.abs(start.getX() - end.getX()); 
        // int y = Math.abs(start.getY() - end.getY()); 
        // return x * y == 2; 
        return false;
    } 
} 
