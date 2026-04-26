package dao;

import db.DBConnection;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM questions WHERE LOWER(category) = LOWER(?) ORDER BY RAND() LIMIT 5";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, category);

            System.out.println("Category Selected: " + category);

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

            System.out.println("Total Questions Fetched: " + questions.size());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return questions;
    }
}