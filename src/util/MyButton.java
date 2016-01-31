package util;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Bleno Vale
 */
public class MyButton {

    private Color backgroundColor;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private final JButton jButton;

    public MyButton(JButton jButton) {
        this.jButton = jButton;
    }

    public void repaint() {
        if (jButton.getModel().isPressed()) {
            jButton.setBackground(pressedBackgroundColor);
        } else if (jButton.getModel().isRollover()) {
            jButton.setBackground(hoverBackgroundColor);
        } else {
            jButton.setBackground(backgroundColor);
        }
        
        jButton.setContentAreaFilled(false);
        jButton.setOpaque(true);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

}
