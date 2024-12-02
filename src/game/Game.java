package game;

import GUI.*;
import board.*;

/**
 * The Game class represents a game of chess in Java.
 */
public class Game {
    private final Board board;
    private ChessBoardGUI gui;
    private Player p1;
    private Player p2;

    /**
     * Main method to start the game
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    /**
     * Creates a new game object, which creates a new board object
     */
    public Game() {
        board = new Board();
    }


    /**
     * Assigns player varaible and calls for the board to be initialized
     * 
     * @param p1 Represents a 'Player' object
     * @param p2 Represents a 'Player' object
     */
    private void enterPlayer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        board.initialize(p1, p2);
        System.out.print("board initialized");
        gui = new ChessBoardGUI(board, p1, p2);
    }

    /**
     * Processes a player's turn by taking input from the user and executing the move, if valid, on the board.
     * 
     * @param p Represents the 'Player' object who's turn it is
     */
    public void processTurn(Player p) {
        Move mv = null;
        String color = (p.white ? "White" : "Black");
        gui.updateGUI(board);
        
        do {
            System.out.print(p.getName() + "'s turn (" + color+ "): ");
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

        System.out.println();
        gui.updateGUI(board);
    }


    /**
     * Prompts for player names and starts the game loop
     */
    public void startGame() {
        InputPlayers input = new InputPlayers();
        p1 = new Player(input.getWhitePlayerName(), true);
        p2 = new Player(input.getBlackPlayerName(), false);
        enterPlayer(p1, p2);

        while (true) {
            processTurn(p1);
            
            if (this.board.getWin()) {
                gui.winnerPopUp(p1);
                break;
            }
            processTurn(p2);
            if (this.board.getWin()) {
                gui.winnerPopUp(p2);
                break;
            }
        }
    }
}