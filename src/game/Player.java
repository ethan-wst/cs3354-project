package game;

import java.util.ArrayList;
import java.util.List;
import pieces.*;

/**
 * The Player class represents a player in a game of chess.
 */
public class Player {
    public boolean white;
    public boolean checked;
    private final String playerName;
    private final List<Move> moveSet = new ArrayList<>();
    private final List<Piece> pieces = new ArrayList<>();


    /**
     * Creates new Player object
     * 
     * @param name  Player Name
     * @param white True if white, False if black
     */
    public Player(String name, boolean white) {
        this.playerName = name;
        this.white = white;
        initializePieces();
    }


    /**
     * Mutator method for the white boolean
     * 
     * @param white True if white, False if black
     */
    public void setWhite(boolean white) {
        this.white = white;
    }


    /**
     * Mutator method for the checked boolean
     * 
     * @param checked True if checked, False if not
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    /**
     * Accessor method for the players piece set
     * 
     * @return pieces list
     */
    public List<Piece> getPieces() {
        return pieces;
    }


    /**
     * Accessor method for the player's name
     * 
     * @return the player's name
     */
    public String getName() {
        return playerName;
    }


    /**
     * Gets the players King piece
     * 
     * @return King piece
     */
    public Piece getKing() {
        for (Piece piece : pieces) {
            if (piece instanceof King) {
                return piece;
            }
        }
        return null;
    }


    /**
     * Removes piece from piece set
     * 
     * @param piece Piece to remove
     */
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }


    /**
     * Adds piece to piece set
     * 
     * @param piece Piece to add
     */
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }


    /**
     * Accessor method for the white boolean
     * 
     * @return True if white, False if black
     */
    public boolean isWhite() {
        return white;
    }


    /**
     * Accessor method for the checked boolean
     * 
     * @return True if checked, False if not
     */
    public boolean isChecked() {
        return checked;
    }


    /**
     * Accessor method for the move set
     * 
     * @return moveSet list
     */
    public List<Move> getMoveSet() {
        return moveSet;
    }


    /**
     * Accessor method for the current move
     * 
     * @return last move in moveSet
     */
    public Move getCurrentMove() {
        return moveSet.getLast();
    }


    /**
     * Adds move to move set
     * 
     * @param mv Move to add
     */
    public void addMove(Move mv) {
        moveSet.add(mv);
    }


    /**
     * Removes last move from move set
     */
    public void removeCurrentMove() {
        moveSet.removeLast();
    }


    /**
     * Initializes the pieces for the player based on color
     */
    public final void initializePieces() {
        if (white) {
            for (int i = 0; i < 8; i++)
                pieces.add(new Pawn(i, 1, true, true));
            pieces.add(new Rook(0, 0, true, true));
            pieces.add(new Rook(7, 0, true, true));
            pieces.add(new Bishop(2, 0, true, true));
            pieces.add(new Bishop(5, 0, true, true));
            pieces.add(new Knight(1, 0, true, true));
            pieces.add(new Knight(6, 0, true, true));
            pieces.add(new Queen(3, 0, true, true));
            pieces.add(new King(4, 0, true, true));
        } else {
            for (int i = 0; i < 8; i++)
                pieces.add(new Pawn(i, 6, true, false));
            pieces.add(new Rook(0, 7, true, false));
            pieces.add(new Rook(7, 7, true, false));
            pieces.add(new Bishop(2, 7, true, false));
            pieces.add(new Bishop(5, 7, true, false));
            pieces.add(new Knight(1, 7, true, false));
            pieces.add(new Knight(6, 7, true, false));
            pieces.add(new Queen(3, 7, true, false));
            pieces.add(new King(4, 7, true, false));
        }
    }
}