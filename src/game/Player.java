package game;
import board.*;
import java.util.ArrayList;
import java.util.List;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

/**
 * Represents a player (white or black)
 */
public class Player {
    public boolean white;
    private List<Move> moveSet = new ArrayList<>();
    private List<Piece> pieces = new ArrayList<>();

    public Player(boolean white) {
        super();
        this.white = white;
        initializePieces();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public final void initializePieces() {
        if(white) {
            for (int i = 0; i < 8; i++) pieces.add(new Pawn(i,1, true, true));
            pieces.add(new Rook(0, 0,true, true));
            pieces.add(new Rook(7, 0,true, true));
            pieces.add(new Bishop(1, 0,true, true));
            pieces.add(new Bishop(6, 0,true, true));
            pieces.add(new Knight(2, 0,true, true));
            pieces.add(new Knight(5, 0,true, true));
            pieces.add(new Queen(3, 0,true, true));
            pieces.add(new King(4, 0,true, true));
        } else {
            for (int i = 0; i < 8; i++) pieces.add(new Pawn(i,6, true, true));
            pieces.add(new Rook(0, 7,true, true));
            pieces.add(new Rook(7, 7,true, true));
            pieces.add(new Bishop(1, 7,true, true));
            pieces.add(new Bishop(6, 7,true, true));
            pieces.add(new Knight(2, 7,true, true));
            pieces.add(new Knight(5, 7,true, true));
            pieces.add(new Queen(3, 7,true, true));
            pieces.add(new King(4, 7,true, true));
        }
        
    }
}