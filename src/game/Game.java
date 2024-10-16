package game;
import board.*;
import java.util.Scanner;

/**
 * Represent the chess game, includes main method
 */
public class Game {
    private final Board board;
    private Player p1;
    private Player p2;

    /**
     * Creates a new game object, which creates a new board object
     */
    public Game() {
        board = new Board();
    }
    
    /**
     * Assigns player varaible and calls for the board to be initialized
     * @param p1 Player one
     * @param p2 Player two
     */
    private void enterPlayer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        board.initialize(p1, p2);
    }

    /**
     * Used in main game loop, intakes player moves.
     * @param p Player whose turn it is.
     */
    public void processTurn(Player p) {
        Scanner scnr = new Scanner(System.in);
        String color = "black";
        if (p.white) color = "white";
        
        board.display();
        scnr.nextLine();

        // //System input
        // do {
        //     Move mv = new Move(/*input*/);
        //     p.addMove(mv);
        // } while(!board.executeMove(p));
        scnr.close();
    }

    /**
     * Main game loop
     */
    public void startGame() {
        p1 = new Player("Player 1", true);
        p2 = new Player("Player 2", false);
        enterPlayer(p1, p2);

        System.out.println("\n---" + p1.playerName + " vs. " + p2.playerName + "---/n");
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