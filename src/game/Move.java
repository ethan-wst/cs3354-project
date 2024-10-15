package game;
import board.*;

/**
 * Represents a chess move
 */
public class Move {
    Piece piece;
    int startX, startY, endX, endY;
    public Move(Piece piece, int startX, int startY, int endX, int endY) {
        this.piece = piece; 
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}