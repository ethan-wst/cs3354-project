package pieces;
import board.*;

public class Knight extends Piece { 
    public Knight(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    } 
  
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return (Math.abs(startX - endX) * Math.abs(startY - endY)) == 2; 
    } 
} 
