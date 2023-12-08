package util;

import gui.GameArea;

public class GameThread extends Thread {
    private GameArea gameArea;
    public GameThread(GameArea gameArea){
        this.gameArea = gameArea;
    }
    @Override
    public void run(){
        while(true){
            gameArea.spawnBlock();
            while(gameArea.MoveBlockDown()){
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error inside gameThread!!");
                }
            }
        }
        }
}
