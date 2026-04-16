package ui;

import dao.ResultDAO;
import model.ResultHistoryEntry;
import model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryPage extends JFrame {

    public HistoryPage(Student student) {
        setTitle("Previous Attempts");
        setSize(980, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        RoundedPanel card = new RoundedPanel();
        card.setPreferredSize(new Dimension(780, 500));
        card.setLayout(new BorderLayout(15, 15));
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("Previous Attempts - " + student.getName(), SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);
        title.setForeground(Theme.TEXT);

        String[] columns = {"Score", "Total Questions", "Quiz Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        ResultDAO dao = new ResultDAO();
        List<ResultHistoryEntry> list = dao.getStudentHistory(student.getId());

        for (ResultHistoryEntry entry : list) {
            model.addRow(new Object[]{
                    entry.getScore(),
                    entry.getTotalQuestions(),
                    entry.getQuizDate()
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