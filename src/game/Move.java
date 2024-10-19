package game;

import board.*;

/**
 * The Move class represents a move made by a piece on a game board in Java.
 */
public class Move {
    private final Piece piece;
    public int startX, startY, endX, endY;

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
     * @return piece that currently occupies the square
     */
    public Piece getPiece() {
        return this.piece;
    }
}