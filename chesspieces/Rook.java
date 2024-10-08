package chesspieces;
import chessboard.*;
/**
 * This class provides the methods neccassa
 */

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        if (end.getPiece().isWhite() == this.isWhite()) return false;
        else if (start.getX() == end.getX() || start.getY() == end.getY()) {


            for (int i = start.getX(); i < end.getX())
            return true;
        }
        return false;
    }
}
