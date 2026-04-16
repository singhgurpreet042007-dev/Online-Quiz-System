package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnimatedBackgroundPanel extends JPanel {

    private final int[] x = new int[35];
    private final int[] y = new int[35];
    private final int[] size = new int[35];
    private final int[] speed = new int[35];
    private final Random random = new Random();

    public AnimatedBackgroundPanel() {
        setLayout(new GridBagLayout());

        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextInt(1400);
            y[i] = random.nextInt(900);
            size[i] = 8 + random.nextInt(22);
            speed[i] = 1 + random.nextInt(3);
        }

        Timer timer = new Timer(35, e -> {
            for (int i = 0; i < y.length; i++) {
                y[i] += speed[i];
                if (y[i] > getHeight() + 40) {
                    y[i] = -40;
                    x[i] = random.nextInt(Math.max(getWidth(), 1));
                }
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(
                0, 0, Theme.BACKGROUND_1,
                getWidth(), getHeight(), Theme.BACKGROUND_2
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());

        GradientPaint glow1 = new GradientPaint(
                0, 0, new Color(0, 200, 255, 70),
                300, 300, new Color(0, 200, 255, 0)
        );
        g2.setPaint(glow1);
        g2.fillOval(-80, -50, 320, 320);

        GradientPaint glow2 = new GradientPaint(
                0, 0, new Color(124, 77, 255, 0),
                300, 300, new Color(124, 77, 255, 90)
        );
        g2.setPaint(glow2);
        g2.fillOval(getWidth() - 320, getHeight() - 320, 360, 360);

        for (int i = 0; i < x.length; i++) {
            g2.setColor(new Color(255, 255, 255, 28));
            g2.fillOval(x[i], y[i], size[i], size[i]);
        }

        g2.dispose();
    }
}