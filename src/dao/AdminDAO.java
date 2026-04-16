package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public boolean loginAdmin(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return false;

            String query = "SELECT * FROM admins WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Admin Login Error: " + e.getMessage());
            return false;
        }
    }
}