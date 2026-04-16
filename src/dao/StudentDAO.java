package dao;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    public boolean registerStudent(String name, String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return false;

            String query = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Register Error: " + e.getMessage());
            return false;
        }
    }

    public Student loginStudent(String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return null;

            String query = "SELECT * FROM students WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
        }

        return null;
    }
}