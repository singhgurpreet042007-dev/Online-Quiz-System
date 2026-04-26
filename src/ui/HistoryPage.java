package ui;

import javax.swing.*;
import java.awt.*;

public class HistoryPage extends JFrame {

    public HistoryPage() {

        setTitle("History");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Theme.BG);
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("History", JLabel.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);

        JLabel msg = new JLabel("History Feature Coming Soon", JLabel.CENTER);
        msg.setForeground(Theme.MUTED);
        msg.setFont(Theme.SUBTITLE_FONT);

        panel.add(title, BorderLayout.NORTH);
        panel.add(msg, BorderLayout.CENTER);

        add(panel);
    }
}