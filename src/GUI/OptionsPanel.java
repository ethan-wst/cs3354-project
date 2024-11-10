package GUI;

import java.awt.*;
import javax.swing.*;

public class OptionsPanel {
    private Color lightSquareColor = Color.LIGHT_GRAY;
    private Color darkSquareColor = Color.DARK_GRAY;

    // Open color choosers to select colors for light and dark squares
    public void chooseSquareColors() {
        lightSquareColor = JColorChooser.showDialog(null, "Choose Light Square Color", lightSquareColor);
        darkSquareColor = JColorChooser.showDialog(null, "Choose Dark Square Color", darkSquareColor);

        // Set default colors if the user cancels the dialog
        if (lightSquareColor == null) lightSquareColor = Color.WHITE;
        if (darkSquareColor == null) darkSquareColor = Color.DARK_GRAY;
    }

    // Getters to access selected colors
    public Color getLightSquareColor() {
        return lightSquareColor;
    }

    public Color getDarkSquareColor() {
        return darkSquareColor;
    }
}
