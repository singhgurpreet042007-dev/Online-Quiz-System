package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(520, 400));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Manage Questions and Results");
        subtitle.setFont(Theme.SUBTITLE_FONT);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton addQuestionBtn = new RoundedButton("Add Question");
        RoundedButton viewResultsBtn = new RoundedButton("View Results");
        RoundedButton backBtn = new RoundedButton("Back to Home");

        Dimension btnSize = new Dimension(260, 48);
        addQuestionBtn.setMaximumSize(btnSize);
        viewResultsBtn.setMaximumSize(btnSize);
        backBtn.setMaximumSize(btnSize);

        addQuestionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewResultsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        addQuestionBtn.addActionListener(e -> {
            dispose();
            new AddQuestionPage().setVisible(true);
        });

        viewResultsBtn.addActionListener(e -> {
            dispose();
            new ViewResultsPage().setVisible(true);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        card.add(Box.createVerticalStrut(15));
        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(40));
        card.add(addQuestionBtn);
        card.add(Box.createVerticalStrut(18));
        card.add(viewResultsBtn);
        card.add(Box.createVerticalStrut(18));
        card.add(backBtn);

        background.add(card);
        add(background);
    }
}