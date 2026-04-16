package model;

public class ResultHistoryEntry {
    private int score;
    private int totalQuestions;
    private String quizDate;

    public ResultHistoryEntry(int score, int totalQuestions, String quizDate) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.quizDate = quizDate;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getQuizDate() {
        return quizDate;
    }
}