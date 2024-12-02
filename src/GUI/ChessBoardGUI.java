package GUI;

import board.*;
import game.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.*;
import pieces.*;

/**
 * The ChessBoardGUI class represents the graphical user interface for the chess board.
 */
public class ChessBoardGUI {
    int BOARD_SIZE = 8;
    private JPanel boardPanel;
    private JFrame frame;
    private final JButton[][] squareButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private Move mv;
    private JSplitPane splitPane;
    private Color whiteSquareColor = Color.lightGray;
    private Color blackSquareColor = Color.darkGray;
    private Color whitePieceColor = Color.WHITE;
    private Color blackPieceColor = Color.BLACK;
    private int FontSize = 32;
    private Piece movingPiece = null;
    private final JLabel dragLabel = new JLabel();
    int endX = 0;
    int endY = 0;

    
    /**
     * Creates a new ChessBoardGUI object
     * 
     * @param board The board object to display in the GUI
     * @param p1 The first player object
     * @param p2 The second player object
     */
    public ChessBoardGUI(Board board, Player p1, Player p2) {
        initializeGUI(board);
    }


    /**
     * Initializes the graphical user interface for the chess board.
     * 
     * @param board The board object to display in the GUI
     */
    private void initializeGUI(Board board) {
        // Create the main frame
        frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 600); 

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the settings menu item
        JMenuItem settingsMenuItem = new JMenuItem("Settings");
        settingsMenuItem.addActionListener((ActionEvent e) -> {
            openSettingsWindow(board);
        });

        // Add the settings menu item directly to the menu bar
        menuBar.add(settingsMenuItem);
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

        // Create the output panel
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);

        // Redirect System.out to the output area
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputArea.append(String.valueOf((char) b));
                outputArea.setCaretPosition(outputArea.getDocument().getLength());
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);

        // Create a split pane to hold the board panel and the right pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, outputScrollPane);
        splitPane.setDividerLocation(550); // Adjust the divider location as needed

        frame.add(splitPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }


    /**
     * Handles the mouse pressed event on the chess board.
     * 
     * @param board The board object to display in the GUI
     * @param x The x-coordinate of the square
     * @param y The y-coordinate of the square
     * @param e The mouse event
     */
    public void handleMousePressed(Board board, int x, int y, MouseEvent e) {
        if (movingPiece == null) {
            movingPiece = board.getSquare(x, y).getPiece();
            if (movingPiece != null) {
                mv = new Move(movingPiece, x, y, -1, -1);

                char xchar = (char) (x + 65);
                System.out.print(xchar + "" + (y + 1) + " --> ");

                dragLabel.setVisible(true);
                dragLabel.setText(movingPiece.getSymbol());
                dragLabel.setForeground(movingPiece.isWhite() ? whitePieceColor : blackPieceColor);
                dragLabel.setSize(dragLabel.getPreferredSize());

                // Get the mouse position relative to the screen
                Point mousePoint = e.getLocationOnScreen();
                // Get the parent component's position relative to the screen
                Point parentPoint = dragLabel.getParent().getLocationOnScreen();
                // Calculate the position of the dragLabel relative to its parent
                int xPosition = mousePoint.x - parentPoint.x - dragLabel.getWidth() / 2;
                int yPosition = mousePoint.y - parentPoint.y - dragLabel.getHeight() / 2;
                dragLabel.setLocation(xPosition, yPosition);
            }
        } else {
            movingPiece = null;
            mv.setEndX(x);
            mv.setEndY(y);
            char xchar = (char) (x + 65);
            System.out.println(xchar + "" + (y + 1));
        }
    }


    /**
     * Handles the mouse released event on the chess board.
     * 
     * @param board The board object to display in the GUI
     * @param x The x-coordinate of the square
     * @param y The y-coordinate of the square
     * @param e The mouse event
     */
    public void handleMouseReleased(Board board, int x, int y, MouseEvent e) {
        Point boardPosition = boardPanel.getLocationOnScreen();
        endX = (e.getXOnScreen() - boardPosition.x) / squareButtons[0][0].getWidth();
        endY = BOARD_SIZE - 1 - (e.getYOnScreen() - boardPosition.y) / squareButtons[0][0].getHeight();
        dragLabel.setVisible(false);
        if ((x != endX || y != endY) && mv != null) {
            movingPiece = null;
            mv.setEndX(endX);
            mv.setEndY(endY);
            char xchar = (char) (endX + 65);
            System.out.println(xchar + "" + (endY + 1));
        }
    }


    /**
     * Handles the mouse dragged event on the chess board.
     * 
     * @param e The mouse event
     */
    public void handleMouseDragged(MouseEvent e) {
        if(movingPiece != null) {
            // Get the mouse position relative to the screen
            Point mousePoint = e.getLocationOnScreen();
            // Get the parent component's position relative to the screen
            Point parentPoint = dragLabel.getParent().getLocationOnScreen();
            // Calculate the position of the dragLabel relative to its parent
            int xPosition = mousePoint.x - parentPoint.x - dragLabel.getWidth() / 2;
            int yPosition = mousePoint.y - parentPoint.y - dragLabel.getHeight() / 2;
            dragLabel.setLocation(xPosition, yPosition);
        }
    }


    /**
     * Updates the graphical user interface to display the current state of the board.
     * 
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
                    squareButton.setFont(new Font("SansSerif", Font.PLAIN, FontSize));
                    squareButton.setForeground(piece.isWhite() ? whitePieceColor : blackPieceColor);
                } else {
                    squareButton.setText("");
                }

                if ((x + y) % 2 == 0) {
                    squareButton.setBackground(whiteSquareColor);
                } else {
                    squareButton.setBackground(blackSquareColor);
                }
            }
        }
    boardPanel.revalidate();
    boardPanel.repaint();
    }


    /**
     * Returns the move object that was made by the player.
     * 
     * @return The move object
     */
    public Move getMove() {
        return mv;
    }


    /**
     * Resets the move object to null.
     */
    public void resetMove() {
        mv = null;
    }


    /**
     * Waits for the player to make a move.
     */
    public void waitForMove() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted, Failed to complete operation");
        }
    }


    /**
     * Displays a pop-up message to indicate that the game is over.
     * 
     * @param p The player object that won the game
     */
    public void winnerPopUp(Player p) {
        JOptionPane.showMessageDialog(null, p.getName() + " (" + (p.isWhite() ? "White" : "Black") + ") wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Opens the settings window to allow the user to customize the appearance of the GUI.
     * 
     * @param board The board object to display in the GUI
     */
    private void openSettingsWindow(Board board) {
        // Create the settings frame
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(500, 300);

        // Square Color section
        JButton whiteSquareColorButton = new JButton("Set White Square Color");
        JButton blackSquareColorButton = new JButton("Set Black Square Color");

        whiteSquareColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose White Square Color", whiteSquareColor);
            if (newColor != null) {
                whiteSquareColor = newColor;
                updateGUI(board);
            }
        });

        blackSquareColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose Black Square Color", blackSquareColor);
            if (newColor != null) {
                blackSquareColor = newColor;
                updateGUI(board);
            }
        });

        // Piece Color section
        JButton whitePieceColorButton = new JButton("Set White Piece Color");
        JButton blackPieceColorButton = new JButton("Set Black Piece Color");

        whitePieceColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose White Piece Color", Color.WHITE);
            if (newColor != null) {
                whitePieceColor = newColor;
                updateGUI(board);
            }
        });

        blackPieceColorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose Black Piece Color", Color.BLACK);
            if (newColor != null) {
                blackPieceColor = newColor;
                updateGUI(board);
            }
        });

        // Board Size section
        JLabel boardSizeLabel = new JLabel("Board Size");
        String[] sizeOptions = {"Small", "Medium", "Large"};
        JComboBox<String> boardSizeComboBox = new JComboBox<>(sizeOptions);

        boardSizeComboBox.addActionListener(e -> {
            String selectedSize = (String) boardSizeComboBox.getSelectedItem();
            switch (selectedSize) {
            case "Small" -> {
            FontSize = 15; 
            frame.setSize(667, 400);
            updateGUI(board);
            SwingUtilities.invokeLater(() -> {
                splitPane.setDividerLocation(380);
                frame.setLocationRelativeTo(null); // Center the window
            });
            }
            case "Medium" -> {
            FontSize = 32;
            frame.setSize(1000, 600);
            updateGUI(board);
            SwingUtilities.invokeLater(() -> {
                splitPane.setDividerLocation(550);
                frame.setLocationRelativeTo(null); // Center the window
            });
            }
            case "Large" -> {
            FontSize = 43;
            frame.setSize(1500, 900);
            updateGUI(board);
            SwingUtilities.invokeLater(() -> {
                splitPane.setDividerLocation(825);
                frame.setLocationRelativeTo(null); // Center the window
            });
            }
            }
        });

        // Create a panel for the board size components
        JPanel boardSizePanel = new JPanel();
        boardSizePanel.add(boardSizeLabel);
        boardSizePanel.add(boardSizeComboBox);

        // Add components to the settings frame
        settingsFrame.setLayout(new BorderLayout());
        JPanel settingsPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Adjust the grid layout as needed
        settingsPanel.add(whiteSquareColorButton);
        settingsPanel.add(blackSquareColorButton);
        settingsPanel.add(whitePieceColorButton);
        settingsPanel.add(blackPieceColorButton);

        settingsFrame.add(settingsPanel, BorderLayout.CENTER);
        settingsFrame.add(boardSizePanel, BorderLayout.SOUTH);

        settingsFrame.setLocationRelativeTo(null); // Center the window
        settingsFrame.setVisible(true);
    }
}