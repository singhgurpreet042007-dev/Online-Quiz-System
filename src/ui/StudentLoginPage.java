package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class StudentLoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public StudentLoginPage() {

        setTitle("Student Login");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Student Login");
        title.setBounds(200, 40, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(120, 120, 100, 25);

        emailField = new JTextField();
        emailField.setBounds(200, 120, 200, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 160, 100, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 160, 200, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(230, 220, 120, 35);

        loginBtn.addActionListener(e -> loginStudent());

        panel.add(title);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginBtn);

        add(panel);
    }

    private void loginStudent() {

        String email = emailField.getText();
        String pass = new String(passwordField.getPassword());

        StudentDAO dao = new StudentDAO();
        Student student = dao.loginStudent(email, pass);

        if (student != null) {
            JOptionPane.showMessageDialog(this, "Login Successful");

            this.dispose();

            // 👉 FIXED FLOW
            new CategorySelectionPage(student).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }
}