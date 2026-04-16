package dao;

import db.DBConnection;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return questions;

            String query = "SELECT * FROM questions ORDER BY question_id ASC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("question_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_answer")
                );
                questions.add(q);
            }

        } catch (Exception e) {
            System.out.println("Question Fetch Error: " + e.getMessage());
        }

        return questions;
    }
}