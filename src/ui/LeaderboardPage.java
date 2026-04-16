package ui;

import dao.ResultDAO;
import model.LeaderboardEntry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeaderboardPage extends JFrame {

    public LeaderboardPage() {
        setTitle("Leaderboard");
        setSize(950, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(760, 470));
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("Top 10 Leaderboard", SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);

        String[] columns = {"Rank", "Student Name", "Score", "Total Questions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        ResultDAO dao = new ResultDAO();
        List<LeaderboardEntry> list = dao.getTopResults();

        int rank = 1;
        for (LeaderboardEntry entry : list) {
            model.addRow(new Object[]{
                    rank++,
                    entry.getStudentName(),
                    entry.getScore(),
                    entry.getTotalQuestions()
            });
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

        RoundedButton backBtn = new RoundedButton("Back to Home");
        backBtn.setPreferredSize(new Dimension(200, 45));
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
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