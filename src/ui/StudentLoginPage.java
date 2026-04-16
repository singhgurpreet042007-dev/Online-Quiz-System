package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentLoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public StudentLoginPage() {
        setTitle("Student Login");
        setSize(950, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(480, 390));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(35, 35, 35, 35));

        JLabel title = new JLabel("Student Login");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Login to start the quiz");
        subtitle.setFont(Theme.SUBTITLE_FONT);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(28));

        card.add(createLabel("Email"));
        emailField = createTextField();
        card.add(emailField);

        card.add(Box.createVerticalStrut(14));
        card.add(createLabel("Password"));
        passwordField = createPasswordField();
        card.add(passwordField);

        card.add(Box.createVerticalStrut(30));

        RoundedButton loginBtn = new RoundedButton("Login");
        loginBtn.setMaximumSize(new Dimension(230, 46));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton backBtn = new RoundedButton("Back");
        backBtn.setMaximumSize(new Dimension(230, 46));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> loginStudent());
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        card.add(loginBtn);
        card.add(Box.createVerticalStrut(15));
        card.add(backBtn);

        background.add(card);
        add(background);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Theme.TEXT);
        label.setFont(Theme.LABEL_FONT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setBackground(Theme.INPUT_BG);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setBackground(Theme.INPUT_BG);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return field;
    }

    private void loginStudent() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        StudentDAO dao = new StudentDAO();
        Student student = dao.loginStudent(email, password);

        if (student != null) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            dispose();
            new QuizPage(student).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password");
        }
    }
}