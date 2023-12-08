package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/modul4?autoReconnect=true";
    private final static String Username = "root";
    private final static String Password = "";

    public static int id = 0;
    public static String username = null;
    public static String password = null;
    static Connection conn = null;

    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(DB_URL, Username, Password);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void updateUser(String username, String newPassword) {
        try (Connection conn = connect()) {
            String sql = "UPDATE User SET Password=? WHERE Username=?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
