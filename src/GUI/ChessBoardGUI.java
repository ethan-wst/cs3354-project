package GUI;

import board.*;
import game.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import pieces.*;

/**
 * The `ChessBoardGUI` class represents a graphical user interface for a chess board with interactive
 * functionalities for moving pieces.
 */
public class ChessBoardGUI {
    int BOARD_SIZE = 8;
    private JPanel boardPanel;
    private final JButton[][] squareButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private final OptionsPanel optionsPanel;
    private Move mv;

    /**
     * Creates a new `ChessBoardGUI` object with a given board.
     * @param board The board object to display in the GUI
     */
    public ChessBoardGUI(Board board) {
        optionsPanel = new OptionsPanel();
        initializeGUI(board);
    }

    /**
     * Initializes the graphical user interface for the chess board.
     * @param board The board object to display in the GUI
     */
    private void initializeGUI(Board board) {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem changeColorItem = new JMenuItem("Change Square Colors");

        // Add action listener for changing square colors
        changeColorItem.addActionListener((ActionEvent e) -> {
            optionsPanel.chooseSquareColors();
            updateGUI(board);
        });

        optionsMenu.add(changeColorItem);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);

        // Create the board panel with a grid layout
        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        // Initialize the board squares
        for (int y = BOARD_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JButton squareButton = new JButton();
                squareButton.setFont(new Font("SansSerif", Font.PLAIN, 32));
                final int X = x;
                final int Y = y;

                // Display piece if the square is occupied
                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null) {
                    squareButton.setText(piece.getSymbol());
                    squareButton.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK);
                }

                squareButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleMousePressed(board, X, X, e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        handleMouseReleased(board, X, Y, e);
                    }
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClicked(board, X, Y, e);
                    }
                });

                squareButton.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        handleMouseDragged(e);
                    }
                });

                // Set the button background for the chessboard pattern
                squareButtons[x][y] = squareButton;
                squareButton.setPreferredSize(new Dimension(75, 75));
                boardPanel.add(squareButton);
            }
        }

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    public void handleMousePressed(Board board, int x, int y, MouseEvent e) {
        
    }

    public void handleMouseReleased(Board board, int x, int y, MouseEvent e) {
        //System.out.println("Released at: " + x + ", " + y);
    }

    public void handleMouseDragged(MouseEvent e) {
        //System.out.println("Dragging...");
    }

    public void handleMouseClicked(Board board, int x, int y, MouseEvent e) {
        if (mv != null) {
            mv.setEndX(x);
            mv.setEndY(y);
            char xchar = (char) (x + 65);
            System.out.println(xchar + "" + (y+1));
        } else {
            Piece piece = board.getSquare(x, y).getPiece();
            if (piece != null) {
                mv = new Move(piece, x, y, -1, -1);
                char xchar = (char) (x + 65);
                System.out.print(xchar + "" + (y+1) + " --> ");
            } else {
                System.out.println("No piece at the selected Square");
            }
        }
        
        
    }

    /**
     * Updates the graphical user interface to display the current state of the board.
     * @param board The board object to display in the GUI
     */
    public void updateGUI(Board board) {
        for (int y = BOARD_SIZE - 1; y > -1; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JButton squareButton = squareButtons[x][y];

                // Display piece if the square is occupied
                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null) {
                    squareButton.setText(piece.getSymbol());
                    squareButton.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK);
                } else {
                    squareButton.setText("");
                }

                if ((x + y) % 2 == 0) {
                    squareButton.setBackground(optionsPanel.getLightSquareColor());
                } else {
                    squareButton.setBackground(optionsPanel.getDarkSquareColor());
                }
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public Move getMove() {
        return mv;
    }

    public void waitForMove() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }

    public void resetMove() {
        mv = null;
    }
}