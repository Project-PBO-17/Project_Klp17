package util;

import javax.swing.JOptionPane;

import api.Score;
import gui.GameArea;
import gui.GameForm;
import gui.TetrisMain;

public class GameThread extends Thread {
    private GameArea gameArea;
    private GameForm gameForm;
    private int score = 0, level = 1;
    private int scoreUpdateLevel = 500;
    private int speed = 1000;
    private int updateSpeed = 100;
    private boolean running = true;
    private LeaderBoardGameThread leaderBoardGameThread;

    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
    }
    public void setLeaderBoardThread(LeaderBoardGameThread leaderBoardGameThread){
        this.leaderBoardGameThread = leaderBoardGameThread;
    }

    @Override
    public void run() {
        while (getRunning()) {
            gameArea.spawnBlock();
            while (gameArea.MoveBlockDown()) {
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                }
            }
            if (gameArea.isBlockOutOfBound()) {
               TetrisMain.stopBackground();
               TetrisMain.playGameover();
                String username = JOptionPane.showInputDialog(gameForm, "Game Over! Enter your name:");
                if (username != null && !username.isEmpty()) {
                    leaderBoardGameThread.interrupt();
                    Score userData = new Score();
                    userData.uploadScore(username, getScore());
                    JOptionPane.showMessageDialog(gameForm, "Data successfully submitted!");
                } else {
                    JOptionPane.showMessageDialog(gameForm, "Data will not be submitted");
                }
                gameForm = new GameForm();
                break;
            }
            
            gameArea.setBlockToBackground();
            score += gameArea.clearLines();
            gameForm.UpdateScore(score);
            int lvl = score / scoreUpdateLevel + 1;
            if (lvl > level) {
                scoreUpdateLevel = (scoreUpdateLevel * level) + (scoreUpdateLevel / 2);
                level = lvl;
                gameForm.UpdateLevel(level);
                if (speed != 100) {
                    speed -= updateSpeed;
                }
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean getRunning() {
        return running;
    }

    private int getScore() {
        return score;
    }
}
