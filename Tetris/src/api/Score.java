package api;

import java.sql.*;

public class Score {
    public void uploadScore(String username, int score) {
        try (Connection conn = JDBC.connect()) {
            String sql = "INSERT INTO user_data (username, score) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, score);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Score uploaded.");
                } else {
                    System.out.println("Score upload failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
