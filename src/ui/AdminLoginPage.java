package ui;

import dao.AdminDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminLoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginPage() {
        setTitle("Admin Login");
        setSize(950, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(480, 380));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(35, 35, 35, 35));

        JLabel title = new JLabel("Admin Login");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Manage quiz system securely");
        subtitle.setFont(Theme.SUBTITLE_FONT);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(28));

        card.add(createLabel("Username"));
        usernameField = createTextField();
        card.add(usernameField);

        card.add(Box.createVerticalStrut(14));
        card.add(createLabel("Password"));
        passwordField = createPasswordField();
        card.add(passwordField);

        card.add(Box.createVerticalStrut(30));

        RoundedButton loginBtn = new RoundedButton("Login");
        RoundedButton backBtn = new RoundedButton("Back");

        loginBtn.setMaximumSize(new Dimension(230, 46));
        backBtn.setMaximumSize(new Dimension(230, 46));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> loginAdmin());
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
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        field.setBackground(Theme.INPUT_BG);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 30), 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        field.setBackground(Theme.INPUT_BG);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 30), 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        return field;
    }

    private void loginAdmin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        AdminDAO dao = new AdminDAO();
        boolean ok = dao.loginAdmin(username, password);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Admin Login Successful");
            dispose();
            new AdminDashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Admin Credentials");
        }
    }
}