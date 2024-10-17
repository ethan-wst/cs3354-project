package pieces;
import board.*;

public class Queen extends Piece{
    public Queen (int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    }

    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        return new Rook(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX, endY) 
            || new Bishop(this.getX(), this.getY(), true, this.isWhite()).validMove(board, startX, startY, endX, endY);
    }
    
}
