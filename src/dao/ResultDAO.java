package dao;

import db.DBConnection;
import model.LeaderboardEntry;
import model.ResultHistoryEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public boolean saveResult(int studentId, int score, int totalQuestions) {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return false;

            String query = "INSERT INTO results (student_id, score, total_questions) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, score);
            ps.setInt(3, totalQuestions);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Result Save Error: " + e.getMessage());
            return false;
        }
    }

    public List<LeaderboardEntry> getTopResults() {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return leaderboard;

            String query = "SELECT s.name, r.score, r.total_questions " +
                    "FROM results r " +
                    "JOIN students s ON r.student_id = s.id " +
                    "ORDER BY r.score DESC, r.quiz_date ASC " +
                    "LIMIT 10";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                leaderboard.add(new LeaderboardEntry(
                        rs.getString("name"),
                        rs.getInt("score"),
                        rs.getInt("total_questions")
                ));
            }

        } catch (Exception e) {
            System.out.println("Leaderboard Error: " + e.getMessage());
        }

        return leaderboard;
    }

    public List<ResultHistoryEntry> getStudentHistory(int studentId) {
        List<ResultHistoryEntry> history = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return history;

            String query = "SELECT score, total_questions, quiz_date FROM results WHERE student_id = ? ORDER BY quiz_date DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                history.add(new ResultHistoryEntry(
                        rs.getInt("score"),
                        rs.getInt("total_questions"),
                        rs.getString("quiz_date")
                ));
            }

        } catch (Exception e) {
            System.out.println("History Error: " + e.getMessage());
        }

        return history;
    }
}