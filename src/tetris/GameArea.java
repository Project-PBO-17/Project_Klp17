package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel {
    
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder, int columns){
        
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder (placeholder.getBorder());
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        
        spawnBlock();
    }
    
    public void spawnBlock(){
        block = new TetrisBlock (new int[][] {{1,0}, {1,0}, {1,1}}, Color.blue);
        block.spawn(gridColumns);
    }
    
    public void moveBlockDown(){
        
        block.moveDown();
        repaint();
    }
    
    private boolean checkBottom(){
        if(block.getBottomEdge() == gridRows){
        }
            return false;
    }
    
    private void drawBlock (Graphics g){
        
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        
        for (int row = 0; row < h; row++){
            for (int col = 0; col < w; col++){
                if(shape[row][col]==1){
                    
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    
                    g.setColor(c);
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect(x, y, gridCellSize, gridCellSize);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        drawBlock(g);
    }  
}
