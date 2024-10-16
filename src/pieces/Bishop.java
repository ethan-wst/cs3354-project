package pieces;
import board.*;

public class Bishop extends Piece { 
    public Bishop(int x, int y, boolean alive, boolean white) {
        super(x, y, alive, white);
    } 
  
    @Override
    public boolean validMove(Board board, int startX, int startY, int endX, int endY) {
        // // End square is occupied by same color piece
        // if (end.getPiece().isWhite() == this.isWhite()) return false;

        // // Move not diagnol
        // if (start.getX() == end.getX() || start.getY() == end.getY()) return false;

        // // Move is not equally diagnol
        // if(Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY())) return false;

        // int xOffset = 1, yOffset = 1;

        // if (start.getX() > end.getX()) xOffset = -1;
        // if (start.getY() > end.getY()) yOffset = -1;


        // int y = start.getY() + yOffset;
        // for (int x = start.getX() + xOffset; x != end.getX(); x+=xOffset) {
        //     if(board.getSquare(x,y).getPiece() != null) return false;
        //     y+=yOffset;
        // }

        return true;
    } 
} 
