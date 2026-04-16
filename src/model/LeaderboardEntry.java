package model;

public class LeaderboardEntry {
    private String studentName;
    private int score;
    private int totalQuestions;

    public LeaderboardEntry(String studentName, int score, int totalQuestions) {
        this.studentName = studentName;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }
}