package game;

import board.*;
import java.util.Scanner;
import pieces.Piece;
import util.*;

/**
 * The Game class represents a two-player board game where players take turns
 * making moves until one
 * player wins.
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
     * 
     * @param p1 Player one
     * @param p2 Player two
     */
    private void enterPlayer(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        board.initialize(p1, p2);
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
    public void processTurn(Player p, Scanner scnr, boolean isChecked) {
        String userInput;
        int[] cords;
        Piece pieceToMove;

        String color = "Black";
        if (p.white)
            color = "White";
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
        } while (!board.executeMove(p));
        

        System.out.println("-------------------------");
    }



    public boolean CheckMate(Player p1, Player p2) {
        for(Piece p1Piece : p1.getPieces()) {
            if (p1Piece.getClass().getName().equals("pieces.King")) {
                for(Piece p2Piece : p2.getPieces()) {
                    for(int x = 0, x < 8, x++) {
                        for (int y =)
                    }
                    if (p2Piece.validMove(board, p2Piece.getX(), p2Piece.getY(), p1Piece.getX(), p1Piece.getY())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * The `startGame` function initializes two players, takes turns for each player
     * until a player
     * wins, and prints the winner's name.
     */
    public void startGame() {
        p1 = new Player("Player 1", true);
        p2 = new Player("Player 2", false);
        Scanner scnr = new Scanner(System.in);
        enterPlayer(p1, p2);
        boolean p1Check = false;
        boolean p2Check = false;

        while (true) {
            processTurn(p1, scnr, p1Check);
            if(Check(p2, p1)) {
                System.out.println ("P2 is in check");
                p2Check = true;
            }
            if (this.board.getWin()) {
                System.out.println("P1 win!");
                break;
            }
            processTurn(p2, scnr, p2Check);
            if(Check(p1, p2)) {
                System.out.println ("P1 is in check");
                p1Check = true;
            }
            if (this.board.getWin()) {
                System.out.println("P2 win!");
                break;
            }
        }
    }
}