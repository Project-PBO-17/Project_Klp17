import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Leaderboard extends JFrame {

    private JTable leaderboardTable;
    private Connection connection;

    public Leaderboard() {
        initComponents();
        establishConnection(); // Fungsi untuk mengatur koneksi ke database

        String[] columns = {"Rank", "Player", "Score"}; // Kolom tabel
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM leaderboard ORDER BY score DESC");

            int rank = 1;
            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int score = resultSet.getInt("score");

                Object[] rowData = {rank, playerName, score};
                model.addRow(rowData);
                rank++;
            }

            leaderboardTable.setModel(model); // Set model ke tabel

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        leaderboardTable = new JTable();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(leaderboardTable);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leaderboard");
        setSize(400, 300);
        getContentPane().add(scrollPane);
        setLocationRelativeTo(null);
    }

    private void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tetris";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Leaderboard().setVisible(true);
        });
    }
}
