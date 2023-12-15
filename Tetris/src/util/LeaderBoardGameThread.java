package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import api.UserData;
import api.UserData.User;
import gui.GameForm;
import gui.ScorePanel;

public class LeaderBoardGameThread extends Thread {
    private ScorePanel scorePanel;
    private GameForm gameForm;
    private int userScore;
    private GameThread gameThread;

    public LeaderBoardGameThread(ScorePanel scorePanel, GameForm gameForm) {
        this.scorePanel = scorePanel;
        this.gameForm = gameForm;
    }

    public void setScore(int userScore) {
        this.userScore = userScore;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // Dapatkan top 5 pengguna terbaru dari database atau sumber lainnya
            // Tambahkan skor user ke dalam topUsers (jika lebih tinggi dari yang terendah)
            if (UserData.getUserData().isEmpty()) {
                // System.out.println("ababi");
                UserData.getTop5Users();
                UserData.addUser("Your Score: ", userScore);
                //UserData.setData(UserData.getUserData());
            }
            UserData.getUserData();
            int userScore = gameForm.getScore();
            UserData.setUsetScore("Your Score: ", userScore);
            SwingUtilities.invokeLater(() -> scorePanel.updateLeaderboard());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
