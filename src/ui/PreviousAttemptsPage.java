package ui;

import dao.ResultDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class PreviousAttemptsPage extends JFrame {

    public PreviousAttemptsPage(int studentId) {

        setTitle("Previous Attempts");
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 50));

        JLabel title = new JLabel("Previous Attempts", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        // Table columns
        String[] columns = {"Category", "Score", "Total", "Date"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        try {
            ResultDAO dao = new ResultDAO();
            ResultSet rs = dao.getResults(studentId);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("category"),
                        rs.getInt("score"),
                        rs.getInt("total"),
                        rs.getTimestamp("date")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }
}