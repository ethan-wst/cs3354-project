package game;

import GUI.*;
import board.*;

/**
 * The Game class represents a two-player board game where players take turns
 * making moves until one
 * player wins.
 */
public class Game {
    private final Board board;
    private ChessBoardGUI gui;
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
     * 
     * @param p1 Player one
     * @param p2 Player two
     */
    private void enterPlayer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        board.initialize(p1, p2);
        gui = new ChessBoardGUI(board);
    }

    /**
     * The `processTurn` function in Java processes a player's turn by taking user
     * input, identifying
     * the piece to move, creating a move object, and executing the move on the
     * board until a valid
     * move is made.
     * 
     * @param p    Player object representing the current player taking the turn. It
     *             contains information
     *             about the player's name, color, and pieces on the board.
     * @param scnr The `scnr` parameter in the `processTurn` method is of type
     *             `Scanner`. It is used to
     *             read input from the user during the player's turn in the game.
     *             The `Scanner` class in Java is
     *             used for obtaining input of primitive types like int, double,
     *             etc., and
     */
    public void processTurn(Player p) {
        Move mv = gui.getMove();
        String color = "Black";
        if (p.white) color = "White";

        gui.updateGUI(board);

        do {
            System.out.print(color + "'s turn: ");
            while (true) { 
                mv = gui.getMove();
                //Wait for a short period to avoid busy-waiting
                gui.waitForMove();
                if (mv != null && mv.getEndX() != -1 && mv.getEndY() != -1) {
                    break;
                }
            }
            p.addMove(mv);
            gui.resetMove();
        } while (!board.executeMove(p));
        

    }

    /**
     * The `startGame` function initializes two players, takes turns for each player
     * until a player
     * wins, and prints the winner's name.
     */
    public void startGame() {
        p1 = new Player("Player 1", true);
        p2 = new Player("Player 2", false);
        enterPlayer(p1, p2);

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

    public Player getOpponent(Player p) {
        return (p == p1) ? p2 : p1;
    }

    // public void setTurn(Player p) {
    //     this.board.setTurn(p);
    // }
}