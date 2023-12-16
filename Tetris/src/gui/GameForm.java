package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import api.TetrisBlock;
import util.GameThread;
import util.LeaderBoardGameThread;

public class GameForm extends JFrame {
    private GameArea gameArea;
    private NextBlockPanel nextBlockPanel;
    private ScorePanel scorePanel;
    private LevelPanel levelPanel;
    private MenuPanel menuPanel;
    private GameThread gameThread;
    private LeaderBoardGameThread leaderThread;
    private int score;

    public GameForm() {
        TetrisMain.setBackgroundVolume(0.2f);
        TetrisMain.loopBackground();
        initComponent();
        initControls();
        gameArea = new GameArea(10);
        nextBlockPanel = new NextBlockPanel();
        scorePanel = new ScorePanel();
        levelPanel = new LevelPanel();
        menuPanel = new MenuPanel(this);
        gameArea.setNextBlockPanel(nextBlockPanel);

        nextBlockPanel.setBackground(Color.BLACK);
        scorePanel.setBackground(Color.BLACK);
        levelPanel.setBackground(Color.BLACK);
        menuPanel.setBackground(Color.BLACK);

        this.add(gameArea);
        this.add(nextBlockPanel);
        this.add(scorePanel);
        this.add(levelPanel);
        this.add(menuPanel);
        StartGame();
    }

    public void restartGame() {
        // Stop the existing game thread
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
        }

        // Remove existing components
        getContentPane().removeAll();

        new GameForm();
        
        revalidate();
        repaint();
    }

    public void pauseGame() {
        TetrisMain.stopBackground();
        TetrisMain.loopPause();
        gameArea.setPauseGame(!gameArea.isGamePaused());
        if (gameArea.isGamePaused()) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to continue the game?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                // User chose Yes, continue the game
                gameArea.setPauseGame(false); // Stop the pause
            } else {
                gameThread.setRunning(false);
                dispose(); 
            }
        }
    }
    public void setGameThread(GameThread gameThread) {
        this.gameThread = gameThread;
    }

    public void stopGameThread() {
        if (gameThread != null) {
            gameThread.setRunning(false);
        }
    }

    public void StartGame() {
        gameThread = new GameThread(gameArea, this);
        gameThread.start();
        leaderThread = new LeaderBoardGameThread(scorePanel, this, gameArea);
        leaderThread.start();
    }

    public void UpdateScore(int score) {
        this.score = score;
        scorePanel.setScoreText(String.valueOf(score));
        leaderThread.setScore(score);
    }

    public void UpdateLevel(int level) {
        levelPanel.setLevelText(String.valueOf(level));
    }

    public int getScore() {
        return score;
    }

    private void initComponent() {
        setTitle("Tetris");
        setSize(900, 740);
        setResizable(false);
        setLocationRelativeTo(this);
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(null);
    }

    private void initControls() {
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
