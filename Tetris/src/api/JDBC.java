package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/dbtetris?autoReconnect=true";
    private final static String Username = "root";
    private final static String Password = "";

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

}
