package dao;

import db.DBConnection;

import java.sql.*;

public class ResultDAO {

    public void saveResult(int studentId, String category, int score, int total) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO results (student_id, category, score, total) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setString(2, category);
            ps.setInt(3, score);
            ps.setInt(4, total);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResults(int studentId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM results WHERE student_id = ? ORDER BY date DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}