package GUI;
import board.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import pieces.*;

public class ChessBoardGUI {
    int BOARD_SIZE = 8;
    private JPanel boardPanel;
    private final JButton[][] squareButtons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private final OptionsPanel optionsPanel;

    public ChessBoardGUI(Board board) {
        optionsPanel = new OptionsPanel();
        initializeGUI(board);
    }


    // Create and display the GUI
    private void initializeGUI(Board board) {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem changeColorItem = new JMenuItem("Change Square Colors");

        changeColorItem.addActionListener((ActionEvent e) -> {
            optionsPanel.chooseSquareColors();
            System.out.println("hi");
            updateGUI(board);
        });

        optionsMenu.add(changeColorItem);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        for (int y = BOARD_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JButton squareButton = new JButton();
                squareButton.setFont(new Font("SansSerif", Font.PLAIN, 32)); // Set font for pieces

                System.out.println(x+ " " + y);
                // Display piece if the square is occupied
                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null) {
                    squareButton.setText(piece.getSymbol());
                    squareButton.setForeground(piece.isWhite() ? Color.WHITE : Color.BLACK);
                }

                // Set the button background for the chessboard pattern
                

                squareButtons[x][y] = squareButton;
                squareButton.setPreferredSize(new Dimension(75, 75));
                boardPanel.add(squareButton);
            }
        }

        frame.add(boardPanel);
        frame.setVisible(true);
    }

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
}
