package game;

import board.*;

/**
 * The Move class represents a move made by a piece on a game board in Java.
 */
public class Move {
    private final Piece piece;
    public int startX, startY, endX, endY;

    public Move(Piece piece, int startX, int startY, int endX, int endY) {
        this.piece = piece;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public Piece getPiece() {
        return this.piece;
    }
}