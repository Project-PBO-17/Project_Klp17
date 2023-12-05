package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread{
    
    private GameArea ga;
    
    public GameThread(GameArea ga){
        
        this.ga = ga;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                ga.moveBlockDown();
                
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
