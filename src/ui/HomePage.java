package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Online Quiz System");
        setSize(1180, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        JPanel wrapper = new JPanel(new GridLayout(1, 2, 30, 0));
        wrapper.setOpaque(false);
        wrapper.setPreferredSize(new Dimension(1000, 520));

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(60, 20, 60, 20));

        JLabel smallTag = new JLabel("JAVA + MYSQL + SWING");
        smallTag.setForeground(Theme.ACCENT_HOVER);
        smallTag.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel bigTitle1 = new JLabel("Build Your");
        bigTitle1.setForeground(Theme.TEXT);
        bigTitle1.setFont(Theme.HERO_FONT);

        JLabel bigTitle2 = new JLabel("Quiz Experience");
        bigTitle2.setForeground(Theme.ACCENT_HOVER);
        bigTitle2.setFont(Theme.HERO_FONT);

        JLabel desc = new JLabel("<html><div style='width:380px;'>A premium desktop-based online quiz system with secure login, smooth navigation, modern visuals, and MySQL-backed result storage.</div></html>");
        desc.setForeground(Theme.MUTED);
        desc.setFont(Theme.SUBTITLE_FONT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(smallTag);
        leftPanel.add(Box.createVerticalStrut(18));
        leftPanel.add(bigTitle1);
        leftPanel.add(Box.createVerticalStrut(6));
        leftPanel.add(bigTitle2);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(desc);
        leftPanel.add(Box.createVerticalGlue());

        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 45, 40, 45));

        JLabel title = new JLabel("Online Quiz System");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Choose your next action");
        subtitle.setFont(Theme.SUBTITLE_FONT);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton registerBtn = new RoundedButton("Student Register");
        RoundedButton loginBtn = new RoundedButton("Student Login");
        RoundedButton adminBtn = new RoundedButton("Admin Login");
        RoundedButton leaderboardBtn = new RoundedButton("Leaderboard");
        RoundedButton exitBtn = new RoundedButton("Exit");

        Dimension btnSize = new Dimension(270, 50);

        registerBtn.setMaximumSize(btnSize);
        loginBtn.setMaximumSize(btnSize);
        adminBtn.setMaximumSize(btnSize);
        leaderboardBtn.setMaximumSize(btnSize);
        exitBtn.setMaximumSize(btnSize);

        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerBtn.addActionListener(e -> {
            dispose();
            new StudentRegisterPage().setVisible(true);
        });

        loginBtn.addActionListener(e -> {
            dispose();
            new StudentLoginPage().setVisible(true);
        });

        adminBtn.addActionListener(e -> {
    dispose();
    new AdminLoginPage().setVisible(true);
});

        leaderboardBtn.addActionListener(e -> {
            dispose();
            new LeaderboardPage().setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        card.add(Box.createVerticalStrut(15));
        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(45));
        card.add(registerBtn);
        card.add(Box.createVerticalStrut(18));
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(18));
card.add(adminBtn);
        card.add(Box.createVerticalStrut(18));
        card.add(leaderboardBtn);
        card.add(Box.createVerticalStrut(18));
        card.add(exitBtn);

        wrapper.add(leftPanel);
        wrapper.add(card);

        background.add(wrapper);
        add(background, BorderLayout.CENTER);
    }
}