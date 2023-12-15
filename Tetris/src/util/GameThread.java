package util;

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
    private boolean isPaused = false;

    private ScorePanel scorePanel;
    private LeaderBoardGameThread leaderBoardThread;


    public GameThread(GameArea gameArea, GameForm gameForm){
        this.gameArea = gameArea;
        this.gameForm = gameForm;
    }
    @Override
    public void run(){
        while(true){
            gameArea.spawnBlock();
            leaderBoardThread = new LeaderBoardGameThread(gameForm.getScorePanel(), gameForm);
            leaderBoardThread.start();
            while(gameArea.MoveBlockDown()){
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.err.println("Error inside gameThread!!");
                }
            }
            if(gameArea.isBlockOutOfBound()){
                leaderBoardThread.interrupt();
                System.out.println("done");
                break;
            }
            gameArea.setBlockToBackground();
            score += gameArea.clearLines();
            gameForm.UpdateScore(score);
            leaderBoardThread.setScore(score);
            if (leaderBoardThread != null) {
                leaderBoardThread.setScore(score);
            }
            int lvl = score / scoreUpdateLevel + 1;
            if(lvl > level){
                scoreUpdateLevel = (scoreUpdateLevel * level) + (scoreUpdateLevel/2);
                level = lvl;
                gameForm.UpdateLevel(level);
                if(speed != 100){
                    speed -= updateSpeed;
                }
            }
        }
    }
    public void togglePause() {
        isPaused = !isPaused;
    }
    public int getScore(){
        return score;
    }
}
