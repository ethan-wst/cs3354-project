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

    private Piece movingPiece = null;
    private final JLabel dragLabel = new JLabel();
    int endX = 0;
    int endY = 0;

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

        dragLabel.setVisible(false);
        dragLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        frame.getLayeredPane().add(dragLabel, JLayeredPane.DRAG_LAYER);

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
                        handleMousePressed(board, X, Y, e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        handleMouseReleased(board, X, Y, e);
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
        if (movingPiece == null) {
            movingPiece = board.getSquare(x, y).getPiece();
            if (movingPiece != null) {
                mv = new Move(movingPiece, x, y, -1, -1);
                char xchar = (char) (x + 65);
                System.out.print(xchar + "" + (y+1) + " --> ");

                dragLabel.setText(movingPiece.getSymbol());
                dragLabel.setForeground(movingPiece.isWhite() ? Color.WHITE : Color.BLACK);
                dragLabel.setSize(dragLabel.getPreferredSize());
                dragLabel.setLocation(e.getXOnScreen() - 22, e.getYOnScreen() - 45);
                dragLabel.setVisible(true);
            }
        } else {
            movingPiece = null;
            mv.setEndX(x);
            mv.setEndY(y);
            
            char xchar = (char) (x + 65);
            System.out.println(xchar + "" + (y+1));
        }
    }

    public void handleMouseReleased(Board board, int x, int y, MouseEvent e) {
        Point boardPosition = boardPanel.getLocationOnScreen();
        endX = (e.getXOnScreen() - boardPosition.x) / squareButtons[0][0].getWidth();
        endY = BOARD_SIZE - 1 - (e.getYOnScreen() - boardPosition.y) / squareButtons[0][0].getHeight();
        dragLabel.setVisible(false);
        if (x != endX || y != endY) {
            movingPiece = null;
            
            mv.setEndX(endX);
            mv.setEndY(endY);
            char xchar = (char) (endX + 65);
            System.out.println(xchar + "" + (endY+1));

        }
    }

    public void handleMouseDragged(MouseEvent e) {
        if(movingPiece != null) {
            dragLabel.setLocation(e.getXOnScreen() - 22, e.getYOnScreen() - 45);
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

    public void resetMove() {
        mv = null;
    }

    public void waitForMove() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }

    public void winnerPopUp(Player p) {
        JOptionPane.showMessageDialog(boardPanel, "Game Over! " + (p.isWhite() ? "Black" : "White") + " wins!");
                Window window = SwingUtilities.getWindowAncestor(boardPanel);
                if (window != null) {
                    window.dispose();
                }
    }


}