package board;
import game.Move;
import game.Player;
import pieces.*;

/**
 * Represents the board
 */
public class Board {
    private Square[][] chessBoard;
    private boolean win;

    /**
     * Creates a new board object that is in its opening state
     */
    public Board() {
        win = false;
        chessBoard = new Square[8][8];
    }

    public void initialize(Player p) {
        for (int i = 0; i < p.getPieces().size(); i++) {
            chessBoard[p.getPieces().get(i).getX()][p.getPieces().get(i).getY()].occupySpot(p.getPieces().get(i));
        }
    }

    public boolean executeMove(Player p) {
        Move mv = p.getCurrentMove();
        Piece piece = mv.getPiece();

        //// check the move step is valid for piece
        if(!piece.validMove(this, cmd.curX, cmd.curY, cmd.desX, cmd.desY)) {
            // if not valid cmd remove the command and return false
            p.removeCurrentCmd();
            return false;
        }

        // check the two pieces side
        if(spot[cmd.desX][cmd.desY] != null && spot[cmd.desX][cmd.desY].color == piece.color)
            return false;

        // check and change the state on spot
        Piece taken = spot[cmd.desX][cmd.desY].occupySpot(piece);
        if(taken != null &&taken.getClass().getName().equals("King"))
            board.win = true;
        spot[cmd.curX][cmd.curY].releaseSpot;
        return true;
    }

    public boolean getWin() {
        return win;
    }

    /**
     * Prints the current state of the board object in the terminal
     */
    public void display() {
        for (int y = 8; y >= 0; y--) {
            if (y == 8) System.out.print("   ");
            else System.out.print((y+1) + "  ");
            for (int x = 0; x < 8; x++) {
                if (y == 8) System.out.print((char) (x+65) + "  ");
                else {
                    
                    if (this.getSquare(x, y).getPiece() == null) {
                        if ((x+y)%2 != 0) System.out.print("   ");
                        else System.out.print("## ");
                    } else {
                        String colorPre = "w";
                        if (!this.getSquare(x, y).getPiece().isWhite()) colorPre = "b";
                        if (this.getSquare(x, y).getPiece() instanceof Rook) System.out.print(colorPre + "R ");
                        else if (this.getSquare(x, y).getPiece() instanceof Knight) System.out.print(colorPre + "N ");
                        else if (this.getSquare(x, y).getPiece() instanceof Bishop) System.out.print(colorPre + "B ");
                        else if (this.getSquare(x, y).getPiece() instanceof Queen) System.out.print(colorPre + "Q ");
                        else if (this.getSquare(x, y).getPiece() instanceof King) System.out.print(colorPre + "K ");
                        else if (this.getSquare(x, y).getPiece() instanceof Pawn) System.out.print(colorPre + "p ");
                    }
                }
            }
            System.out.println();
        }
        
    }
}