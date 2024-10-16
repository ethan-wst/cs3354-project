package board;
/**
 * Represents a chess piece
 */

public abstract class Piece {
    private int x;
    private int y;

    private boolean alive;
    private boolean white;
    private boolean hasMoved;

    /**
     * Creates a piece object
     * @param x X cord of piece on the board
     * @param y Y cord of piece on the board
     * @param alive True if alive, false if not
     * @param white True if white and false if black
     */
    public Piece(int x, int y, boolean alive, boolean white) {
        super();
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.white = white;
        this.hasMoved = false;
    }
    
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isAlive() {
        return this.alive;
    }
 
    /**
     * Gets piece's color
     * @return True if white and false if black
     */
    public boolean isWhite() {
        return this.white;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Sets a piece's color
     * @param white True if white and false if black
     */
    public final void setWhite(boolean white) {
        this.white = white;
    }

    /**
     * Decides wether a move is valid or not
     * @param board The board we are playing on
     * @param startX The starting X point of the piece
     * @param startY The starting Y point of the piece
     * @param endX The end X point of the piece
     * @param endY The end Y point of the piece
     * @return True if input is a possible move and false otherwise
     */
    public abstract boolean validMove(Board board, int startX, int startY, int endX, int endY);
}

