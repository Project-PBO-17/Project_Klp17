package util;

import gui.GameArea;
import gui.GameForm;

public class GameThread extends Thread {
    private GameArea gameArea;
    private GameForm gameForm;
    private int score, level = 1;
    private int scoreUpdateLevel = 500;
    private int speed = 1000;
    private int updateSpeed = 100;


    public GameThread(GameArea gameArea, GameForm gameForm){
        this.gameArea = gameArea;
        this.gameForm = gameForm;
    }
    @Override
    public void run(){
        while(true){
            gameArea.spawnBlock();
            while(gameArea.MoveBlockDown()){
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.err.println("Error inside gameThread!!");
                }
            }
            if(gameArea.isBlockOutOfBound()){
                System.out.println("done");
                break;
            }
            gameArea.setBlockToBackground();
            score += gameArea.clearLines();
            gameForm.UpdateScore(score);
            int lvl = score / scoreUpdateLevel + 1;
            if(lvl > level){
                scoreUpdateLevel = (scoreUpdateLevel * level) + scoreUpdateLevel;
                level = lvl;
                gameForm.UpdateLevel(level);
                if(speed != 100){
                    speed -= updateSpeed;
                }
            }
        }
        }
}
