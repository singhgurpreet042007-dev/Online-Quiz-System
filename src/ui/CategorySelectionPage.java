package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class CategorySelectionPage extends JFrame {

    private Student student;

    public CategorySelectionPage(Student student) {
        this.student = student;

        setTitle("Select Quiz Category");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(null);
        main.setBackground(new Color(18,18,32));

        JLabel title = new JLabel("Choose Your Quiz");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 40, 300, 40);

        JButton javaBtn = new JButton("Java Quiz");
        JButton reasoningBtn = new JButton("Reasoning Quiz");
        JButton compBtn = new JButton("Computer Quiz");

        javaBtn.setBounds(350, 150, 200, 40);
        reasoningBtn.setBounds(350, 210, 200, 40);
        compBtn.setBounds(350, 270, 200, 40);

        javaBtn.addActionListener(e -> openQuiz("Java"));
        reasoningBtn.addActionListener(e -> openQuiz("Reasoning"));
        compBtn.addActionListener(e -> openQuiz("Computer"));

        main.add(title);
        main.add(javaBtn);
        main.add(reasoningBtn);
        main.add(compBtn);

        add(main);
    }

    private void openQuiz(String category) {
        this.dispose();
        new QuizPage(student, category).setVisible(true);
    }
}