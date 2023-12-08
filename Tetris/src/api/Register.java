package api;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register {
    public static boolean insertUser(String username, String password) {
        try(Connection conn = JDBC.connect()){
            String sql = "INSERT INTO User (Username, Password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                return true;
    
            }    
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
