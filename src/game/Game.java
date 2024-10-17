package game;
import board.*;
import java.util.Scanner;
import util.*;

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
    public void processTurn(Player p, Scanner scnr) {
        
        String userInput;
        int[] cords;
        Piece pieceToMove;
        
        String color = "Black";
        if (p.white) color = "White";
        board.display();
        
        do {
            pieceToMove = null;
            System.out.print("\n" + p.playerName + " (" + color + "): ");
            userInput = scnr.nextLine();
            
            cords = new UserInput(userInput).getIntInput();
            for (Piece piece : p.getPieces()) {
                if (piece.getX() == cords[0] && piece.getY() == cords[1]) {
                    pieceToMove = piece;
                    break;
                }
            }
                Move mv = new Move(pieceToMove, cords[0], cords[1], cords[2], cords[3]);
                p.addMove(mv);
        } while(!board.executeMove(p));
        System.out.println("-------------------------");
        //scnr.close();
    }
    

    /**
     * Main game loop
     */
    public void startGame() {
        p1 = new Player("Player 1", true);
        p2 = new Player("Player 2", false);
        Scanner scnr = new Scanner(System.in);
        enterPlayer(p1, p2);

        while (true) {
            processTurn(p1, scnr);
            if (this.board.getWin()) {
                System.out.println("P1 win!");
                break;
            }
            processTurn(p2, scnr);
            if (this.board.getWin()) {
                System.out.println("P2 win!");
                break;
            }
        }
    }
}