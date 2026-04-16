package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewResultsPage extends JFrame {

    public ViewResultsPage() {
        setTitle("View Results");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(860, 500));
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("Student Results", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);

        String[] columns = {"Student Name", "Email", "Score", "Total Questions", "Quiz Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT s.name, s.email, r.score, r.total_questions, r.quiz_date " +
                    "FROM results r JOIN students s ON r.student_id = s.id ORDER BY r.quiz_date DESC";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("score"),
                        rs.getInt("total_questions"),
                        rs.getString("quiz_date")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        table.setBackground(new Color(25, 35, 55));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(60, 70, 90));
        table.setSelectionBackground(new Color(0, 173, 181));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(25, 35, 55));

        RoundedButton backBtn = new RoundedButton("Back");
        backBtn.setPreferredSize(new Dimension(160, 45));
        backBtn.addActionListener(e -> {
            dispose();
            new AdminDashboard().setVisible(true);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backBtn);

        card.add(title, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        background.add(card);
        add(background);
    }
}