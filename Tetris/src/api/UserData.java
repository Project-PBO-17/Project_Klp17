package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserData {
    public static int highScore = 0;
    public static int tryNumber = 0;
    public static ArrayList<User> topUsers = new ArrayList<>();

    public static class User {
        public static String userName;
        public static int wpm;

        public User(String userName, int wpm) {
            this.userName = userName;
            this.wpm = wpm;
        }

        public static String getUserName() {
            return userName;
        }

        public int getWpm() {
            return wpm;
        }
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public void setTryNumber(int tryNumber) {
        this.tryNumber = tryNumber;
    }


    public static void getTop5Users() {
        System.out.println("aaa");
        String query = "SELECT awp_user.WPM, user.Username\r\n" + //
                "FROM awp_user\r\n" + //
                "JOIN user ON awp_user.id_user = user.id_user\r\n" + //
                "ORDER BY awp_user.WPM DESC\r\n" + //
                "LIMIT 5";

        try (Connection conn = JDBC.connect()) {
            try (PreparedStatement stmt = JDBC.conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("Username");
                    int wpm = rs.getInt("WPM");

                    User user = new User(userName, wpm);
                    //System.out.println("nama: "+ userName);
                    topUsers.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getHighestWPM() {
        String query = "SELECT MAX(WPM) AS highestWPM FROM awp_user WHERE id_user = ?";

        try(Connection conn = JDBC.connect()){
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(query)) {
                preparedStatement.setInt(1, JDBC.id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int highestWPM = resultSet.getInt("highestWPM");
                        System.out.println("Highest WPM: " + highestWPM);
                        setHighScore(highestWPM);
                        return highestWPM;
                    } else {
                        System.out.println("No data found for user with ID " + JDBC.id);
                    }
                }
            } 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public int getNumberOfAttempts() {
        String query = "SELECT COUNT(*) AS attemptCount FROM awp_user WHERE id_user = ?";
    
        try(Connection conn = JDBC.connect()){
            try (PreparedStatement preparedStatement = JDBC.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, JDBC.id);
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int attemptCount = resultSet.getInt("attemptCount");
                        System.out.println("Number of Attempts: " + attemptCount);
                        setTryNumber(attemptCount);
                        return attemptCount;
                    } else {
                        System.out.println("No data found for user with ID " + JDBC.id);
                    }
                }
            } 
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        return -1;
    }
}
