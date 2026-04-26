package ui;

import dao.QuestionDAO;
import model.Question;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizPage extends JFrame {

    private List<Question> questions;
    private int index = 0;
    private int score = 0;

    private Student student;
    private String category;

    private JLabel questionLabel, timerLabel, progressLabel;
    private JRadioButton optA, optB, optC, optD;
    private ButtonGroup group;
    private JButton nextBtn;
    private JProgressBar progressBar;

    private Timer timer;
    private int timeLeft = 15;

    public QuizPage(Student student, String category) {

        this.student = student;
        this.category = category;

        QuestionDAO dao = new QuestionDAO();
        questions = dao.getQuestionsByCategory(category);

        setTitle("Quiz - " + category);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(null);
        main.setBackground(new Color(18, 18, 32));

        JLabel title = new JLabel(category + " Quiz");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(400, 20, 300, 40);

        JPanel card = new JPanel(null);
        card.setBackground(new Color(28, 28, 50));
        card.setBounds(120, 90, 750, 420);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        timerLabel = new JLabel("Time: 15");
        timerLabel.setForeground(Color.RED);
        timerLabel.setBounds(620, 10, 120, 25);

        progressLabel = new JLabel();
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setBounds(20, 10, 200, 25);

        progressBar = new JProgressBar();
        progressBar.setBounds(20, 40, 700, 8);

        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(20, 60, 700, 60);

        optA = createOption();
        optB = createOption();
        optC = createOption();
        optD = createOption();

        optA.setBounds(40, 140, 650, 40);
        optB.setBounds(40, 190, 650, 40);
        optC.setBounds(40, 240, 650, 40);
        optD.setBounds(40, 290, 650, 40);

        group = new ButtonGroup();
        group.add(optA);
        group.add(optB);
        group.add(optC);
        group.add(optD);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(300, 350, 150, 40);
        nextBtn.setBackground(new Color(90, 140, 255));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        nextBtn.addActionListener(e -> nextQuestion());

        card.add(timerLabel);
        card.add(progressLabel);
        card.add(progressBar);
        card.add(questionLabel);
        card.add(optA);
        card.add(optB);
        card.add(optC);
        card.add(optD);
        card.add(nextBtn);

        main.add(title);
        main.add(card);

        add(main);

        loadQuestion();
    }

    private JRadioButton createOption() {
        JRadioButton btn = new JRadioButton();
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(28, 28, 50));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return btn;
    }

    private void loadQuestion() {

        if (questions == null || questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions found!");
            dispose();
            new CategorySelectionPage(student).setVisible(true);
            return;
        }

        if (index >= questions.size()) {
            showResult();
            return;
        }

        Question q = questions.get(index);

        questionLabel.setText("<html><b>" + (index + 1) + ". " + q.getQuestionText() + "</b></html>");

        optA.setText("A. " + q.getOptionA());
        optB.setText("B. " + q.getOptionB());
        optC.setText("C. " + q.getOptionC());
        optD.setText("D. " + q.getOptionD());

        group.clearSelection();

        progressLabel.setText("Question " + (index + 1) + "/" + questions.size());
        progressBar.setValue((index + 1) * 100 / questions.size());

        if (index == questions.size() - 1) {
            nextBtn.setText("Submit");
        } else {
            nextBtn.setText("Next");
        }

        startTimer();
    }

    private void startTimer() {

        timeLeft = 15;
        timerLabel.setText("Time: 15");

        if (timer != null) timer.stop();

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                timer.stop();
                nextQuestion();
            }
        });

        timer.start();
    }

    private void nextQuestion() {

        if (timer != null) timer.stop();

        String selected = "";

        if (optA.isSelected()) selected = "A";
        else if (optB.isSelected()) selected = "B";
        else if (optC.isSelected()) selected = "C";
        else if (optD.isSelected()) selected = "D";

        if (selected.equals(questions.get(index).getCorrectAnswer())) {
            score++;
        }

        index++;
        loadQuestion();
    }

    private void showResult() {

        this.dispose();

        new ResultPage(
                student,
                score,
                questions.size(),
                category
        ).setVisible(true);
    }
}