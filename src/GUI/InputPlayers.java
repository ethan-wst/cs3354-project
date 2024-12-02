package GUI;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 * Used to get the names of the players from the user. 
 */
public class InputPlayers {
    private String whitePlayerName = "Player 1";
    private String blackPlayerName = "Player 2";

    /**
     * Constructor for the InputPlayers class. 
     * Creates a JFrame to get the names of the players from the user. 
     */
    public InputPlayers() {
        JFrame frame = new JFrame("Player Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel whitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel whiteLabel = new JLabel("Enter Name (White Pieces):");
        JTextField whitePlayerField = new JTextField(10);
        whitePanel.add(whiteLabel);
        whitePanel.add(whitePlayerField);

        JPanel blackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel blackLabel = new JLabel("Enter Name (Black Pieces):");
        JTextField blackPlayerField = new JTextField(10);
        blackPanel.add(blackLabel);
        blackPanel.add(blackPlayerField);

        panel.add(whitePanel);
        panel.add(blackPanel);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Player Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            whitePlayerName = whitePlayerField.getText();
            blackPlayerName = blackPlayerField.getText();
        }

        frame.dispose(); // Dispose the frame after handling the input
    }

    /**
     * Returns the name of the player with the white pieces.
     * 
     * @return The name of the player with the white pieces. 
     */
    public String getWhitePlayerName() {  
        return whitePlayerName.equals("") ? "Player 1" : whitePlayerName;
    }

    /**
     * Returns the name of the player with the black pieces.
     * 
     * @return The name of the player with the black pieces. 
     */
    public String getBlackPlayerName() {
        return blackPlayerName.equals("") ? "Player 2" : blackPlayerName;
    }
}
