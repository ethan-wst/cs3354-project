package game;

import pieces.Piece;

/**
 * The Move class represents a move made by a piece on a game board in Java.
 */
public class Move {
    private Piece piece;
    private int startX, startY, endX, endY;

    /**
     * Creates a new move object
     * 
     * @param piece Piece to be moved
     * @param startX Starting x-coordinates
     * @param startY Starting y-coordinates
     * @param endX Ending x-coordinates
     * @param endY Ending y-coordinates
     */
    public Move(Piece piece, int startX, int startY, int endX, int endY) {
        this.piece = piece;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    /**
     * Gets piece on the square
     * 
     * @return piece that currently occupies the square
     */
    public Piece getPiece() {
        return this.piece;
    }

    
    /**
     * Gets the starting x-coordinate
     * 
     * @return starting x-coordinate
     */
    public int getStartX() {
        return startX;
    }


    /**
     * Gets the starting y-coordinate
     * 
     * @return starting y-coordinate
     */
    public int getStartY() {
        return startY;
    }


    /**
     * Gets the ending x-coordinate
     * 
     * @return ending x-coordinate
     */
    public int getEndX() {
        return endX;
    }


    /**
     * Gets the ending y-coordinate
     * 
     * @return ending y-coordinate
     */
    public int getEndY() {
        return endY;
    }


    /**
     * Sets the piece on the square
     * 
     * @param piece Piece to set on the square
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    /**
     * Sets the starting x-coordinate
     * 
     * @param startX Starting x-coordinate
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }


    /**
     * Sets the starting y-coordinate
     * 
     * @param startY Starting y-coordinate
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }


    /**
     * Sets the ending x-coordinate
     * 
     * @param endX Ending x-coordinate
     */
    public void setEndX(int endX) {
        this.endX = endX;
    }


    /**
     * Sets the ending y-coordinate
     * 
     * @param endY Ending y-coordinate
     */
    public void setEndY(int endY) {
        this.endY = endY;
    }


    /**
     * Returns a string representation of the move
     * 
     * @return string representation of the move
     */
    @Override
    public String toString() {
        return piece.getSymbol() + " " + (char)(startX+65) + startY + " -> " + (char)(endX+65) + endY;
    }

}