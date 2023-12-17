package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserData {
    //public static ArrayList<User> topUsers = new ArrayList<>();
    public static List<User> topUsers = Collections.synchronizedList(new ArrayList<>());
    public static List<User> sortedUsers;

    public static class User {
        private String userName;
        private int score;

        public User(String userName, int score) {
            this.userName = userName;
            this.score = score;
        }

        public String getUserName() {
            return userName;
        }
        public void setScore(int score){
            this.score = score;
        }
        public int getScore() {
            return score;
        }

    }

    public static List<User> getUserData() {
        sortedUsers = new ArrayList<>(topUsers);
        sortedUsers.sort(Comparator.comparingInt(User::getScore).reversed());
        return sortedUsers.subList(0, Math.min(6, sortedUsers.size()));
    }

    public static void getTop5Users() {
        //System.out.println("aaaa");
        String query = "SELECT score, username " +
                "FROM user_data " +
                "ORDER BY score DESC " +
                "LIMIT 5";

        try (Connection conn = JDBC.connect()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    int score = rs.getInt("score");

                    User user = new User(username, score);
                    topUsers.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(String username, int score) {
        User newUser = new User(username, score);
        topUsers.add(newUser);
    }
    public static void setUsetScore(String username, int score){
        if (username.equals("Your Score:")) {
            User currentUser = getUserByUsername(username);
            if (currentUser == null) {
                currentUser = new User(username, score);
                topUsers.add(currentUser);
            } else {
                currentUser.setScore(score);
            }
        } else {
            User currentUser = getUserByUsername(username);
            if (currentUser != null) {
                currentUser.setScore(score);
            }
        }
    }
        
    private static User getUserByUsername(String username) {
        for (User user : topUsers) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
}
