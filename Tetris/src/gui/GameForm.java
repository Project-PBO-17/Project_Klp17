package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import util.GameThread;

public class GameForm extends JFrame {
    private GameArea gameArea;
    private NextBlockPanel nextBlockPanel;
    private ScorePanel scorePanel;
    private LevelPanel levelPanel;
    private MenuPanel menuPanel;
    private GameThread gameThread;
    private int score;

    public GameForm() {
        initComponent();
        initControls();
        gameArea = new GameArea(10, 245, 15, 400, 680);

        nextBlockPanel = new NextBlockPanel();
        scorePanel = new ScorePanel();
        levelPanel = new LevelPanel();
        menuPanel = new MenuPanel();
        gameArea.setNextBlockPanel(nextBlockPanel);

        // leaderBoardGameThread = new LeaderBoardGameThread(scorePanel);
        // leaderBoardGameThread.start();

        nextBlockPanel.setBackground(Color.BLACK); // Atur warna latar belakang pada nextBlockPanel
        scorePanel.setBackground(Color.BLACK); // Atur warna latar belakang pada scorePanel
        levelPanel.setBackground(Color.BLACK); // Atur warna latar belakang pada levelPanel
        menuPanel.setBackground(Color.BLACK);

        this.add(gameArea);
        this.add(nextBlockPanel);
        this.add(scorePanel);
        this.add(levelPanel);
        this.add(menuPanel);
        StartGame();
    }

    public void StartGame() {
        gameThread = new GameThread(gameArea, this);
        gameThread.start();
    }
    public ScorePanel getScorePanel(){
        return scorePanel;
    }

    public void UpdateScore(int score) {
        this.score = score;
        scorePanel.setScoreText(String.valueOf(score));
    }

    public void UpdateLevel(int level) {
        levelPanel.setLevelText(String.valueOf(level));
    }
    public int getScore(){
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
        input.put(KeyStroke.getKeyStroke("SPACE"), "space");
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
