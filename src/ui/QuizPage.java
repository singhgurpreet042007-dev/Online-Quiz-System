package ui;

import dao.QuestionDAO;
import dao.ResultDAO;
import model.Question;
import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuizPage extends JFrame {

    private final Student student;
    private final List<Question> questions;
    private final List<String> selectedAnswers = new ArrayList<>();

    private int currentIndex = 0;
    private int timeLeft = 300; // 5 minutes
    private Timer quizTimer;

    private JLabel studentLabel;
    private JLabel questionCountLabel;
    private JLabel timerLabel;
    private JProgressBar progressBar;

    private JTextArea questionArea;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;
    private ButtonGroup group;

    private RoundedButton prevButton;
    private RoundedButton nextButton;
    private RoundedButton submitButton;

    public QuizPage(Student student) {
        this.student = student;
        this.questions = new QuestionDAO().getAllQuestions();

        for (int i = 0; i < questions.size(); i++) {
            selectedAnswers.add("");
        }

        setTitle("Quiz Page");
        setSize(1180, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        RoundedPanel mainCard = new RoundedPanel();
        mainCard.setPreferredSize(new Dimension(980, 600));
        mainCard.setLayout(new BorderLayout());
        mainCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel topPanel = buildTopPanel();
        JPanel centerPanel = buildCenterPanel();
        JPanel bottomPanel = buildBottomPanel();

        mainCard.add(topPanel, BorderLayout.NORTH);
        mainCard.add(centerPanel, BorderLayout.CENTER);
        mainCard.add(bottomPanel, BorderLayout.SOUTH);

        background.add(mainCard);
        add(background);

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions found in database.");
        } else {
            loadQuestion();
            startTimer();
        }
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel infoRow = new JPanel(new BorderLayout());
        infoRow.setOpaque(false);

        studentLabel = new JLabel("Student: " + student.getName());
        studentLabel.setForeground(Theme.TEXT);
        studentLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        questionCountLabel = new JLabel("Question 1 / " + questions.size(), SwingConstants.CENTER);
        questionCountLabel.setForeground(Theme.ACCENT_HOVER);
        questionCountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        timerLabel = new JLabel("Time Left: 05:00");
        timerLabel.setForeground(new Color(255, 210, 120));
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        infoRow.add(studentLabel, BorderLayout.WEST);
        infoRow.add(questionCountLabel, BorderLayout.CENTER);
        infoRow.add(timerLabel, BorderLayout.EAST);

        progressBar = new JProgressBar(0, questions.size());
        progressBar.setValue(1);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Theme.ACCENT);
        progressBar.setBackground(new Color(35, 45, 65));
        progressBar.setBorder(BorderFactory.createEmptyBorder());

        topPanel.add(infoRow);
        topPanel.add(Box.createVerticalStrut(15));
        topPanel.add(progressBar);

        return topPanel;
    }

    private JPanel buildCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(25, 0, 25, 0));

        JPanel questionPanel = new JPanel();
        questionPanel.setOpaque(false);
        questionPanel.setLayout(new BorderLayout());

        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("SansSerif", Font.BOLD, 24));
        questionArea.setForeground(Theme.TEXT);
        questionArea.setOpaque(false);
        questionArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        questionPanel.add(questionArea, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new GridLayout(4, 1, 0, 15));

        optionA = createStyledOption();
        optionB = createStyledOption();
        optionC = createStyledOption();
        optionD = createStyledOption();

        group = new ButtonGroup();
        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        optionsPanel.add(wrapOption(optionA));
        optionsPanel.add(wrapOption(optionB));
        optionsPanel.add(wrapOption(optionC));
        optionsPanel.add(wrapOption(optionD));

        centerPanel.add(questionPanel, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel buildBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel tipLabel = new JLabel("Tip: Select one option and use Next / Previous to navigate.");
        tipLabel.setForeground(Theme.MUTED);
        tipLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        prevButton = new RoundedButton("Previous");
        nextButton = new RoundedButton("Next");
        submitButton = new RoundedButton("Submit Quiz");

        prevButton.setPreferredSize(new Dimension(150, 45));
        nextButton.setPreferredSize(new Dimension(150, 45));
        submitButton.setPreferredSize(new Dimension(180, 45));

        prevButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex > 0) {
                currentIndex--;
                loadQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentIndex < questions.size() - 1) {
                currentIndex++;
                loadQuestion();
            }
        });

        submitButton.addActionListener(e -> submitQuiz());

        buttonPanel.add(prevButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(nextButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(submitButton);

        bottomPanel.add(tipLabel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JRadioButton createStyledOption() {
        JRadioButton rb = new JRadioButton();
        rb.setOpaque(false);
        rb.setForeground(Theme.TEXT);
        rb.setFont(new Font("SansSerif", Font.PLAIN, 18));
        rb.setFocusPainted(false);
        return rb;
    }

    private JPanel wrapOption(JRadioButton radioButton) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 30), 1),
                new EmptyBorder(12, 15, 12, 15)
        ));
        panel.add(radioButton, BorderLayout.CENTER);
        return panel;
    }

    private void loadQuestion() {
        Question q = questions.get(currentIndex);

        questionCountLabel.setText("Question " + (currentIndex + 1) + " / " + questions.size());
        progressBar.setValue(currentIndex + 1);
        progressBar.setString((currentIndex + 1) + " of " + questions.size());

        questionArea.setText(q.getQuestionText());

        optionA.setText("A. " + q.getOptionA());
        optionB.setText("B. " + q.getOptionB());
        optionC.setText("C. " + q.getOptionC());
        optionD.setText("D. " + q.getOptionD());

        group.clearSelection();

        String savedAnswer = selectedAnswers.get(currentIndex);
        if (savedAnswer.equals("A")) optionA.setSelected(true);
        if (savedAnswer.equals("B")) optionB.setSelected(true);
        if (savedAnswer.equals("C")) optionC.setSelected(true);
        if (savedAnswer.equals("D")) optionD.setSelected(true);

        prevButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < questions.size() - 1);
    }

    private void saveCurrentAnswer() {
        if (optionA.isSelected()) selectedAnswers.set(currentIndex, "A");
        else if (optionB.isSelected()) selectedAnswers.set(currentIndex, "B");
        else if (optionC.isSelected()) selectedAnswers.set(currentIndex, "C");
        else if (optionD.isSelected()) selectedAnswers.set(currentIndex, "D");
        else selectedAnswers.set(currentIndex, "");
    }

    private void startTimer() {
        quizTimer = new Timer(1000, e -> {
            timeLeft--;
            int minutes = timeLeft / 60;
            int seconds = timeLeft % 60;
            timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));

            if (timeLeft <= 60) {
                timerLabel.setForeground(new Color(255, 120, 120));
            }

            if (timeLeft <= 0) {
                quizTimer.stop();
                JOptionPane.showMessageDialog(this, "Time is up! Quiz will be submitted now.");
                submitQuizDirectly();
            }
        });
        quizTimer.start();
    }

    private void submitQuiz() {
        saveCurrentAnswer();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to submit the quiz?",
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            submitQuizDirectly();
        }
    }

    private void submitQuizDirectly() {
        if (quizTimer != null) {
            quizTimer.stop();
        }

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (selectedAnswers.get(i).equalsIgnoreCase(questions.get(i).getCorrectAnswer())) {
                score++;
            }
        }

        ResultDAO resultDAO = new ResultDAO();
        resultDAO.saveResult(student.getId(), score, questions.size());

        dispose();
        new ResultPage(student, score, questions.size()).setVisible(true);
    }
}