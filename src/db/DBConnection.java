package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/online_quiz_db";
            String user = "root";
            String password = "Guri@2007"; // apna actual password likho

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return con;
    }
}