package api;

import java.sql.*;

import api.JDBC;

public class Score {
    /*
     * public static void uploadScore(int score) {
     * String query = "UPDATE awp_user SET WPM = " + score + " WHERE id_user = '" +
     * JDBC.id + "'";
     * try (Statement stmt = JDBC.conn.createStatement()) {
     * ResultSet rs = stmt.executeQuery(query);
     * if (rs.next()) {
     * System.out.println("Score uploaded.");
     * } else {
     * System.out.println("Score upload failed.");
     * }
     * } catch (SQLException e) {
     * System.out.println(e.getMessage());
     * }
     * }
     */
    public static void uploadScore(int score) {
        try (Connection conn = JDBC.connect()) {
            String sql = "INSERT INTO awp_user (id_user, WPM) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, JDBC.id);
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
