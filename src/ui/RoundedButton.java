package ui;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private Color currentColor;
    private final Color normalColor;
    private final Color hoverColor;

    public RoundedButton(String text) {
        super(text);
        normalColor = Theme.ACCENT;
        hoverColor = Theme.ACCENT_HOVER;
        currentColor = normalColor;

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setFont(Theme.BUTTON_FONT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                currentColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentColor = normalColor;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 70));
        g2.fillRoundRect(2, 6, getWidth() - 4, getHeight() - 2, 30, 30);

        g2.setColor(new Color(255, 255, 255, 35));
        g2.fillRoundRect(0, 0, getWidth(), getHeight() / 2, 30, 30);

        g2.setColor(currentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g2);
        g2.dispose();
    }
}