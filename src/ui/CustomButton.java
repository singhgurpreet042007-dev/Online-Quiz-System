package ui;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);

        setFocusPainted(false);
        setBackground(Theme.ACCENT);
        setForeground(Color.WHITE);
        setFont(Theme.BUTTON_FONT);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Theme.ACCENT_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Theme.ACCENT);
            }
        });
    }
}