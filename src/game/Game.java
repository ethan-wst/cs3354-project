package game;
import board.*;

public class Game {
    private static Board board;
    private Player white;
    private Player black;
    private boolean isWhitesTurn;

    public static void start() {
        board = new Board();
        board.display();
    }

    public void end() {

    }

    public static void main(String[] args) {
        start();
    }
}
        
        