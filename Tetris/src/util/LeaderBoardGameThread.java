package util;


import javax.swing.SwingUtilities;

import api.UserData;
import gui.GameArea;
import gui.GameForm;
import gui.ScorePanel;

public class LeaderBoardGameThread extends Thread {
    private ScorePanel scorePanel;
    private GameForm gameForm;
    private int userScore;
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
        if (UserData.getUserData().isEmpty()) {
            UserData.getTop5Users();
            UserData.addUser("Your Score:", userScore);
            SwingUtilities.invokeLater(() -> scorePanel.updateLeaderboard());
        }
        while (true) {
            System.out.println("cek");
            UserData.getUserData();
            int userScore = gameForm.getScore();
            if(gameArea.getCheckLines()==true){
                UserData.setUsetScore("Your Score:", userScore);
            }
            SwingUtilities.invokeLater(() -> scorePanel.updateLeaderboard());
  
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }
}
