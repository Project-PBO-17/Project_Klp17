package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import api.UserData;
import api.UserData.User;
import gui.GameArea;
import gui.GameForm;
import gui.ScorePanel;

public class LeaderBoardGameThread extends Thread {
    private ScorePanel scorePanel;
    private GameForm gameForm;
    private int userScore;
    private GameThread gameThread;
    private GameArea gameArea;

    public LeaderBoardGameThread(ScorePanel scorePanel, GameForm gameForm, GameArea gameArea) {
        this.scorePanel = scorePanel;
        this.gameForm = gameForm;
        this.gameArea = gameArea;
    }

    public void setScore(int userScore) {
        this.userScore = userScore;
    }

    @Override
    public void run() {
        System.out.println("aa");
        if (UserData.getUserData().isEmpty()) {
            System.out.println("aaa");
            UserData.getTop5Users();
            UserData.addUser("Your Score: ", userScore);
            SwingUtilities.invokeLater(() -> scorePanel.updateLeaderboard());
        }
        while (!gameArea.getCheckLines()) {
            System.out.println("cek");

            UserData.getUserData();
            int userScore = gameForm.getScore();
            UserData.setUsetScore("Your Score: ", userScore);
            SwingUtilities.invokeLater(() -> scorePanel.updateLeaderboard());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }
}
