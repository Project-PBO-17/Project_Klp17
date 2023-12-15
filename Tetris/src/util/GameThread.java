package util;

import javax.swing.JOptionPane;

import api.Score;
import api.UserData;
import gui.GameArea;
import gui.GameForm;
import gui.ScorePanel;

public class GameThread extends Thread {
    private GameArea gameArea;
    private GameForm gameForm;
    private int score = 0, level = 1;
    private int scoreUpdateLevel = 500;
    private int speed = 1000;
    private int updateSpeed = 100;
    private boolean running = true;

    private ScorePanel scorePanel;
    private LeaderBoardGameThread leaderBoardThread;

    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
    }

    @Override
    public void run() {
        while (getRunning()) {
            gameArea.spawnBlock();

            //leaderBoardThread = new LeaderBoardGameThread(gameForm.getScorePanel(), gameForm);
            //leaderBoardThread.start();

            while (gameArea.MoveBlockDown()) {
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    // System.err.println("Error inside gameThread!!");
                }
            }
            if (gameArea.isBlockOutOfBound()) {
               // leaderBoardThread.interrupt();
                String username = JOptionPane.showInputDialog(gameForm, "Game Over! Enter your name:");

                // Mengatasi null jika pengguna membatalkan dialog
                if (username != null && !username.isEmpty()) {
                    Score userData = new Score();
                    userData.uploadScore(username, getScore());

                    // Tampilkan pesan berhasil jika pengguna menekan "OK"
                    JOptionPane.showMessageDialog(gameForm, "Data successfully submitted!");
                } else {
                    // Tampilkan pesan terima kasih jika pengguna membatalkan input
                    JOptionPane.showMessageDialog(gameForm, "Data will not be submitted");
                }
                break;
            }
            
            //UserData.getTop5Users();
            
            gameArea.setBlockToBackground();
            score += gameArea.clearLines();
            gameForm.UpdateScore(score);
            //leaderBoardThread.setScore(score);
            if (leaderBoardThread != null) {
                leaderBoardThread.setScore(score);
            }
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

    public int getScore() {
        return score;
    }
}
