package GUI;

import board.*;
import pieces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ChessBoardGUI {
    int BOARD_SIZE = 8;
    private JPanel boardPanel;
    private final JButton[][] squareButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private final OptionsPanel optionsPanel;
    private Piece selectedPiece = null;
    private int initialX = -1, initialY = -1;
    private Piece draggedPiece = null;
    private int dragStartX, dragStartY;
    private JLabel dragLabel = new JLabel();

    public ChessBoardGUI(Board board) {
        optionsPanel = new OptionsPanel();
        initializeGUI(board);
        initializeClickListeners(board);
    }

    private void initializeGUI(Board board) {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem changeColorItem = new JMenuItem("Change Square Colors");

        changeColorItem.addActionListener((ActionEvent e) -> {
            optionsPanel.chooseSquareColors();
            updateGUI(board);
        });

        optionsMenu.add(changeColorItem);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        for (int y = BOARD_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JButton squareButton = new JButton();
                squareButton.setFont(new Font("SansSerif", Font.PLAIN, 32));

                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null) {
                    squareButton.setText(piece.getSymbol());
                    squareButton.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK);
                }

                int finalX = x;
                int finalY = y;

                squareButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleMousePressed(board, finalX, finalY, e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        handleMouseReleased(board, finalX, finalY, e);
                    }
                });

                squareButton.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        handleMouseDragged(e);
                    }
                });

                squareButtons[x][y] = squareButton;
                squareButton.setPreferredSize(new Dimension(75, 75));
                boardPanel.add(squareButton);
            }
        }

        dragLabel.setVisible(false);
        dragLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        frame.getLayeredPane().add(dragLabel, JLayeredPane.DRAG_LAYER);

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeClickListeners(Board board) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                final int destX = x;
                final int destY = y;

                squareButtons[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(board, destX, destY);
                    }
                });
            }
        }
    }

    private void handleSquareClick(Board board, int x, int y) {
        if (selectedPiece == null) {
            selectedPiece = board.getSquare(x, y).getPiece();
            if (selectedPiece != null) {
                initialX = x;
                initialY = y;
            }
        } else {
            board.movePiece(initialX, initialY, x, y);
            selectedPiece = null;
            updateGUI(board);
        }
    }

    private void handleMousePressed(Board board, int x, int y, MouseEvent e) {
        draggedPiece = board.getSquare(x, y).getPiece();
        if (draggedPiece != null) {
            dragStartX = x;
            dragStartY = y;
            dragLabel.setText(draggedPiece.getSymbol());
            dragLabel.setForeground(draggedPiece.isWhite() ? Color.WHITE : Color.BLACK);
            dragLabel.setSize(dragLabel.getPreferredSize());
            dragLabel.setLocation(e.getXOnScreen() - dragLabel.getWidth() / 2, e.getYOnScreen() - dragLabel.getHeight() / 2);
            dragLabel.setVisible(true);
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (draggedPiece != null) {
            dragLabel.setLocation(e.getXOnScreen() - dragLabel.getWidth() / 2, e.getYOnScreen() - dragLabel.getHeight() / 2);
        }
    }

    private void handleMouseReleased(Board board, int x, int y, MouseEvent e) {
        if (draggedPiece != null) {
            Point boardPosition = boardPanel.getLocationOnScreen();
            int boardX = (e.getXOnScreen() - boardPosition.x) / squareButtons[0][0].getWidth();
            int boardY = BOARD_SIZE - 1 - (e.getYOnScreen() - boardPosition.y) / squareButtons[0][0].getHeight();
    
            System.out.println("Moving piece from (" + dragStartX + ", " + dragStartY + ") to (" + boardX + ", " + boardY + ")");
    
            // Move the dragged piece to the new square without validating the move
            board.movePiece(dragStartX, dragStartY, boardX, boardY);
            draggedPiece = null;
            updateGUI(board);
            dragLabel.setVisible(false);
        }
    }

    public void updateGUI(Board board) {
        for (int y = BOARD_SIZE - 1; y > -1; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JButton squareButton = squareButtons[x][y];

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
}
