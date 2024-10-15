package game;
import board.*;

/**
 * Represent the chess game, includes main method
 */
public class Game {
    private Board board;
    private Player p1;
    private Player p2;

    public Game() {
        board = new Board();
    }
    
    private boolean enterPlayer(Player p) {
        if (p1 == null) this.p1 = p;
        else if (p2 == null) this.p2 = p;
        else return false;

        board.initialize(p);
        return true;
    }

    public void processTurn(Player p) {
        //System input
        do {
            Move mv = new Move(input);
            p.addMove(mv);
        } while(!board.executeMove(p));
    }

    public void startGame() {
        enterPlayer(new Player("Ethan"));
        enterPlayer(new Player("Miles"));

        while (true) { 
            processTurn(p1);
            if (this.board.getWin()) {
                System.out.println("P1 win!");
                break;
            }
            processTurn(p2);
            if (this.board.getWin()) {
                System.out.println("P2 win!");
                break;
            }
        }
    }
}