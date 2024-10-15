package pieces;
import board.*;

public class Queen extends Piece{
    public Queen (int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        // // End square is occupied by same color piece
        
        // if (end.getPiece().isWhite() == this.isWhite()) return false;

        // //Implement movement logic bishop + rook
        return false;
    }
    
}
