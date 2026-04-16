package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddQuestionPage extends JFrame {

    private JTextField questionField;
    private JTextField optionAField;
    private JTextField optionBField;
    private JTextField optionCField;
    private JTextField optionDField;
    private JTextField correctAnswerField;

    public AddQuestionPage() {
        System.out.println("NEW ADD QUESTION PAGE LOADED"); 
        setTitle("Add Question");
        setSize(1150, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        RoundedPanel mainCard = new RoundedPanel();
        mainCard.setPreferredSize(new Dimension(920, 560));
        mainCard.setLayout(new BorderLayout(25, 25));
        mainCard.setBorder(new EmptyBorder(28, 28, 28, 28));

        JPanel leftPanel = buildLeftPanel();
        JPanel rightPanel = buildRightPanel();

        mainCard.add(leftPanel, BorderLayout.WEST);
        mainCard.add(rightPanel, BorderLayout.CENTER);

        background.add(mainCard);
        add(background);
    }

    private JPanel buildLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(300, 470));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel smallTag = new JLabel("ADMIN CONTROL");
        smallTag.setForeground(Theme.ACCENT_HOVER);
        smallTag.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel title1 = new JLabel("Create New");
        title1.setForeground(Theme.TEXT);
        title1.setFont(new Font("SansSerif", Font.BOLD, 34));

        JLabel title2 = new JLabel("Quiz Question");
        title2.setForeground(Theme.ACCENT_HOVER);
        title2.setFont(new Font("SansSerif", Font.BOLD, 34));

        JLabel desc = new JLabel(
                "<html><div style='width:240px;'>Add well-structured MCQ questions to expand the quiz bank and make the platform more dynamic and professional.</div></html>"
        );
        desc.setForeground(Theme.MUTED);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPanel infoCard = new JPanel();
        infoCard.setOpaque(false);
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS));
        infoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 25), 1),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel info1 = new JLabel("• Add new quiz questions");
        JLabel info2 = new JLabel("• Store directly in MySQL");
        JLabel info3 = new JLabel("• Correct answer validation");
        JLabel info4 = new JLabel("• Useful for admin module");

        JLabel[] infos = {info1, info2, info3, info4};
        for (JLabel label : infos) {
            label.setForeground(Theme.TEXT);
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoCard.add(label);
            infoCard.add(Box.createVerticalStrut(10));
        }

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(smallTag);
        leftPanel.add(Box.createVerticalStrut(18));
        leftPanel.add(title1);
        leftPanel.add(Box.createVerticalStrut(4));
        leftPanel.add(title2);
        leftPanel.add(Box.createVerticalStrut(18));
        leftPanel.add(desc);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(infoCard);
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    private JPanel buildRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout(15, 15));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Add New Question");
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Fill all fields and save question to database");
        subtitle.setFont(Theme.SUBTITLE_FONT);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(subtitle);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 18, 16));
        formPanel.setOpaque(false);

        questionField = createTextField();
        optionAField = createTextField();
        optionBField = createTextField();
        optionCField = createTextField();
        optionDField = createTextField();
        correctAnswerField = createTextField();

        formPanel.add(createFieldCard("Question", questionField));
        formPanel.add(createFieldCard("Option A", optionAField));
        formPanel.add(createFieldCard("Option B", optionBField));
        formPanel.add(createFieldCard("Option C", optionCField));
        formPanel.add(createFieldCard("Option D", optionDField));
        formPanel.add(createFieldCard("Correct Answer (A/B/C/D)", correctAnswerField));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 14, 0));

        RoundedButton backBtn = new RoundedButton("Back");
        RoundedButton addBtn = new RoundedButton("Add Question");

        backBtn.setPreferredSize(new Dimension(160, 46));
        addBtn.setPreferredSize(new Dimension(190, 46));

        backBtn.addActionListener(e -> {
            dispose();
            new AdminDashboard().setVisible(true);
        });

        addBtn.addActionListener(e -> addQuestion());

        bottomPanel.add(backBtn);
        bottomPanel.add(addBtn);

        rightPanel.add(titlePanel, BorderLayout.NORTH);
        rightPanel.add(formPanel, BorderLayout.CENTER);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        return rightPanel;
    }

    private JPanel createFieldCard(String labelText, JTextField field) {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 25), 1),
                new EmptyBorder(14, 14, 14, 14)
        ));

        JLabel label = new JLabel(labelText);
        label.setForeground(Theme.TEXT);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(label);
        card.add(Box.createVerticalStrut(10));
        card.add(field);

        return card;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setPreferredSize(new Dimension(260, 42));
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

    private void addQuestion() {
      System.out.println("ADD BUTTON CLICKED");  
        String question = questionField.getText().trim();
        String optionA = optionAField.getText().trim();
        String optionB = optionBField.getText().trim();
        String optionC = optionCField.getText().trim();
        String optionD = optionDField.getText().trim();
        String correct = correctAnswerField.getText().trim().toUpperCase();

        if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() ||
                optionC.isEmpty() || optionD.isEmpty() || correct.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (!correct.equals("A") && !correct.equals("B") &&
                !correct.equals("C") && !correct.equals("D")) {
            JOptionPane.showMessageDialog(this, "Correct Answer must be only A, B, C or D.");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed.");
                return;
            }

            String query = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, question);
            ps.setString(2, optionA);
            ps.setString(3, optionB);
            ps.setString(4, optionC);
            ps.setString(5, optionD);
            ps.setString(6, correct);

            System.out.println("Trying to insert question...");
            System.out.println("Question = " + question);
            System.out.println("Correct Answer = " + correct);

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted = " + rows);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Question Added Successfully");

                questionField.setText("");
                optionAField.setText("");
                optionBField.setText("");
                optionCField.setText("");
                optionDField.setText("");
                correctAnswerField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Question not added.");
            }

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while adding question:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}