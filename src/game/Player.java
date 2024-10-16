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
    public String playerName;
    private final List<Move> moveSet = new ArrayList<>();
    private final List<Piece> pieces = new ArrayList<>();

    /**
     * Creates new Player object
     * @param name Player Name
     * @param white True if white, False if black
     */
    public Player(String name, boolean white) {
        this.playerName = name;
        this.white = white;
        initializePieces();
    }

    /**
     * gets the players piece set
     * @return pieces list
     */
    public List<Piece> getPieces() {
        return pieces;
    }

    /**
     * Adds move to move set
     * @param mv Move to add to moveSet
     */
    public void addMove(Move mv) {
        moveSet.add(mv);
    }

    /**
     * @return last move on moveSet
     */
    public Move getCurrentMove() {
        return moveSet.getLast();
    }

    /**
     * Removes last piece added to piece set
     */
    public void removeCurrentMove() {
        moveSet.removeLast();
    }

    /**
     * Creates players piece set
     */
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
            pieces.add(new Rook(0, 7,true, false));
            pieces.add(new Rook(7, 7,true, false));
            pieces.add(new Bishop(1, 7,true, false));
            pieces.add(new Bishop(6, 7,true, false));
            pieces.add(new Knight(2, 7,true, false));
            pieces.add(new Knight(5, 7,true, false));
            pieces.add(new Queen(3, 7,true, false));
            pieces.add(new King(4, 7,true, false));
        }
    }
}