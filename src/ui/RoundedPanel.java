package ui;

import javax.swing.JPanel;
import java.awt.*;

public class RoundedPanel extends JPanel {

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0, 0, 0, 55));
        g2.fillRoundRect(8, 10, getWidth() - 16, getHeight() - 16, 40, 40);

        GradientPaint gp = new GradientPaint(
                0, 0, Theme.CARD,
                getWidth(), getHeight(), new Color(28, 36, 58, 225)
        );
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        g2.setColor(Theme.CARD_BORDER);
        g2.setStroke(new BasicStroke(1.4f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);

        g2.dispose();
        super.paintComponent(g);
    }
}