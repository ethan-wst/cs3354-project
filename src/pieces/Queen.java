package pieces;
import board.*;

public class Queen extends Piece{
    public Queen (boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        //need to implement movement logic
        return false;
    }
    
}
