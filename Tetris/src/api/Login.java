package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Login {
    public static boolean getAuthenticatedUser(String username, String password) throws SQLException {

        try(Connection conn = JDBC.connect()){
            String sql = "SELECT * FROM User WHERE Username=? AND Password=?";
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login success");
                        // JDBC.username = resultSet.getString("Username");
                        JDBC.id = resultSet.getInt("id_user");
                        JDBC.username = resultSet.getString("Username");
                        JDBC.password = resultSet.getString("Password");
                        return true;
                    } else {
                        System.out.println("Login failed. Username or password might be incorrect.");
                        JOptionPane.showMessageDialog(null, "Login failed. Username or password might be incorrect.");
                    }
                }
    
            } 
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
