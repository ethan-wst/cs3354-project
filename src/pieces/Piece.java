package pieces;

import board.Board;

/**
 * The Piece class represents a chess piece 
 */
public abstract class Piece {
    private int x;
    private int y;

    private boolean alive;
    private boolean white;
    private boolean hasMoved;

    /**
     * The constructor for the Piece class, initializes the x, y, alive, and white
     * 
     * @param x     X cord of piece on the board
     * @param y     Y cord of piece on the board
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


    /**
     * Accessor method for the 'x' attribute of an object.
     * 
     * @return The 'x' cord of the piece on the board
     */
    public int getX() {
        return this.x;
    }


    /**
     * Accessor method for the 'y' attribute of an object.
     * 
     * @return The 'y' cord of the piece on the board
     */
    public int getY() {
        return this.y;
    }

    /**
     * Accessor method for the 'alive' attribute of an object.
     * 
     * @return The 'alive' attribute of the object
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Accessor method for the 'white' attribute of an object.
     * 
     * @return The 'white' attribute of the object
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * Mutator method for the 'hasMoved' attribute of an object.
     * 
     * @param moved True if the piece has moved, false if not
     */
    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    /**
     * Accessor method for the 'hasMoved' attribute of an object.
     * 
     * @return The 'hasMoved' attribute of the object
     */
    public boolean hasMoved() {
        return this.hasMoved;
    }

    /**
     * Mutator method for the 'x' and 'y' attributes of an object.
     * 
     * @param x The 'x' cord of the piece on the board
     * @param y The 'y' cord of the piece on the board
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Mutator method for the 'alive' attribute of an object.
     * 
     * @param alive True if the piece is alive, false if not
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    /**
     * Mutator method for the 'white' attribute of an object.
     * 
     * @param white True if the piece is white, false if black
     */
    public final void setWhite(boolean white) {
        this.white = white;
    } 


    /**
     * This abstract method checks if a move on a chess board is valid for a piece
     * 
     * @param board  Represents the chess board
     * @param startX The starting 'x' cord of the piece on the board
     * @param startY The starting 'y' cord of the piece on the board
     * @param endX   The ending 'x' cord of the piece on the board
     * @param endY   The ending 'y' cord of the piece on the board
     * @return True if the move is valid, false if not
     */
    public abstract boolean validMove(Board board, int startX, int startY, int endX, int endY);

    /**
     * This abstract method returns the symbol of the piece
     * 
     * @return The symbol of the piece
     */
    public abstract String getSymbol();

    /**
     * This function returns the type of the piece
     * 
     * @return The type of the piece
     */
    public String getType() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
