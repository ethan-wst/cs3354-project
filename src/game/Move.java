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

    public Move() {}

    /**
     * Gets piece on the square
     * @return piece that currently occupies the square
     */
    public Piece getPiece() {
        return this.piece;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

}