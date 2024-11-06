package util;
import board.*;
import game.*;
import java.util.ArrayList;
import java.util.List;
import pieces.*;

public class ValidMoves {
    private final List<Move> valMoves = new ArrayList<>();

    public void calcMoves(Player p, Board board) {
        valMoves.clear();
        for (Piece pc: p.getPieces()) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if(pc.validMove(board, pc.getX(), pc.getY(), x, y)) {
                        valMoves.add(new Move(pc, pc.getX(), pc.getY(), x, y));
                    }
                }
            }
        }
    }

    public List<Move> getMoves() {
        return valMoves;
    }
}
