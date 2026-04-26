package ui;

import javax.swing.*;
import java.awt.*;

public class LeaderboardPage extends JFrame {

    public LeaderboardPage() {

        setTitle("Leaderboard");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Theme.BG);
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Leaderboard", JLabel.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);

        JLabel msg = new JLabel("Leaderboard Coming Soon", JLabel.CENTER);
        msg.setForeground(Theme.MUTED);
        msg.setFont(Theme.SUBTITLE_FONT);

        panel.add(title, BorderLayout.NORTH);
        panel.add(msg, BorderLayout.CENTER);

        add(panel);
    }
}