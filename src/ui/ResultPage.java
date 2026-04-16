package ui;

import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultPage extends JFrame {

    public ResultPage(Student student, int score, int total) {
        setTitle("Quiz Result");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(560, 440));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel title = new JLabel("Quiz Completed");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel studentLabel = new JLabel("Student: " + student.getName());
        studentLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        studentLabel.setForeground(Theme.TEXT);
        studentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel(score + " / " + total);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 52));
        scoreLabel.setForeground(Theme.ACCENT_HOVER);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JProgressBar resultBar = new JProgressBar(0, total);
        resultBar.setValue(score);
        resultBar.setStringPainted(true);
        resultBar.setString("Performance Score");
        resultBar.setForeground(Theme.ACCENT);
        resultBar.setBackground(new Color(35, 45, 65));
        resultBar.setMaximumSize(new Dimension(420, 28));
        resultBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        String performance;
        if (score >= total * 0.8) performance = "Excellent Performance";
        else if (score >= total * 0.5) performance = "Good Performance";
        else performance = "Keep Practicing";

        JLabel performanceLabel = new JLabel(performance);
        performanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        performanceLabel.setForeground(Theme.MUTED);
        performanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton homeBtn = new RoundedButton("Back to Home");
        RoundedButton historyBtn = new RoundedButton("Previous Attempts");
        RoundedButton exitBtn = new RoundedButton("Exit");

        homeBtn.setMaximumSize(new Dimension(230, 46));
        historyBtn.setMaximumSize(new Dimension(230, 46));
        exitBtn.setMaximumSize(new Dimension(230, 46));

        homeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        historyBtn.addActionListener(e -> {
            dispose();
            new HistoryPage(student).setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(Box.createVerticalStrut(25));
        card.add(studentLabel);
        card.add(Box.createVerticalStrut(25));
        card.add(scoreLabel);
        card.add(Box.createVerticalStrut(20));
        card.add(resultBar);
        card.add(Box.createVerticalStrut(18));
        card.add(performanceLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(homeBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(historyBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(exitBtn);

        background.add(card);
        add(background);
    }
}