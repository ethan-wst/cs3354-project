package board;
/**
 * Represents a chess piece
 */

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    /**
     * Creates a piece with a specific color
     * @param white True if white and false if black
     */
    public Piece(boolean white) {
        this.setWhite(white);
    }
    
    /**
     * Gets piece's color
     * @return True if white and false if black
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * Sets a piece's color
     * @param white True if white and false if black
     */
    public final void setWhite(boolean white) {
        this.white = white;
    }

    /**
     * Gets a piece's status
     * @return True if killed and false if alive
     */
    public boolean isKilled() {
        return this.killed;
    }

    /**
     * Sets a piece's status
     * @param killed True if killed and false if alive
     */
    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    /**
     * Decides wether a move is valid or not
     * @param board The board we are playing on
     * @param start The starting point of the piece
     * @param end The end point of the piece
     * @return True if input is a possible move and false otherwise
     */
    public abstract boolean canMove(Board board, Square start, Square end);
}

