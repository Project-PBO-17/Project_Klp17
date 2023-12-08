package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import util.GameThread;

public class GameForm extends JFrame{
    private GameArea gameArea;
    public GameForm(){
        initComponent();
        initControls();
        gameArea = new GameArea(10);
        this.add(gameArea);
        StartGame();
    }

    public void StartGame(){
        new GameThread(gameArea).start();
    }

    private void initComponent(){
        setTitle("Tetris");
        setSize(900,740);
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(null);
    }
    private void initControls(){
        InputMap input = this.getRootPane().getInputMap();
        ActionMap action = this.getRootPane().getActionMap();

        input.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        input.put(KeyStroke.getKeyStroke("LEFT"), "left");
        input.put(KeyStroke.getKeyStroke("DOWN"), "down");
        input.put(KeyStroke.getKeyStroke("UP"), "up");
        action.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.MoveBlockRight();
            }
        });
    
        action.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.MoveBlockLeft();
            }
        });
    
        action.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.MoveBlockQuick();
            }
        });
    
        action.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotationBock();
            }
        });
    }   

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm();
            }
        });
    }
}
