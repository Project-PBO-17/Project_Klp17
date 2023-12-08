package gui;

import util.Calculate;
import util.ReadCSV;
import util.TimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import api.Score;
import api.UserData;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TypingForm extends JFrame {
    private final ReadCSV readCSV = new ReadCSV();
    private final ArrayList<String> wordListData = readCSV.getCsvDataList();
    private final ArrayList<String> wordList = new ArrayList<>();
    private final JPanel wordContainer = new JPanel();
    private final int time = 60;
    private int currentTime = 0;
    private int totalChar = 0;
    private final JLabel timeLabel = new JLabel(TimeFormatter.formatSeconds(time));
    JTextField inputField = new JTextField();
    JLabel scoreLabel = new JLabel("Score: 0 WPM");
    private boolean isTyping = false;
    private Thread timeThread;
    private int WPM = 0;

    public TypingForm() {
        setTitle("TypingForm");

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(700, 30));
        topPanel.setBackground(null);
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        JButton leaderButton = new JButton();
        leaderButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        leaderButton.setBorder(null);
        leaderButton.setBackground(null);
        leaderButton.setFocusable(false);

        JLabel leaderText = new JLabel("leaderboard");
        leaderText.setFont(new Font("Poppins", Font.PLAIN, 16));
        leaderText.setForeground(Color.decode("#f5edb3"));
        leaderButton.add(leaderText);

        JButton profileButton = new JButton();
        profileButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        profileButton.setBorder(null);
        profileButton.setBackground(null);
        profileButton.setFocusable(false);

        profileButton.addActionListener((event) -> {
            UserData userdata = new UserData();
            userdata.getHighestWPM();
            userdata.getNumberOfAttempts();
            Profil profile = new Profil();
        });

        leaderButton.addActionListener((event) -> {
            UserData userdata = new UserData();
            userdata.getTop5Users();
            try {
                LeaderboardForm leader = new LeaderboardForm();
            } catch (SQLException e) {

            }
        });

        JLabel profileText = new JLabel("profile");
        profileText.setFont(new Font("Poppins", Font.PLAIN, 16));
        profileText.setForeground(Color.decode("#f5edb3"));
        profileButton.add(profileText);

        topPanel.add(leaderButton);
        topPanel.add(profileButton);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setPreferredSize(new Dimension(700, 100));

        scoreLabel.setForeground(Color.decode("#f5edb3"));

        scoreLabel.setVerticalAlignment(JLabel.BOTTOM);
        scoreLabel.setFont(new Font("Poppins", Font.BOLD, 16));

        timeLabel.setForeground(Color.decode("#f5edb3"));
        timeLabel.setVerticalAlignment(JLabel.BOTTOM);
        timeLabel.setFont(new Font("Poppins", Font.BOLD, 16));

        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(timeLabel, BorderLayout.EAST);
        scorePanel.setBackground(null);

        wordContainer.setPreferredSize(new Dimension(695, 100));
        wordContainer.setBackground(Color.decode("#739072"));
        wordContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        loadWordList();
        repaintWordList();

        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(700, 100));
        inputPanel.setBackground(null);
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        inputField.setCaretColor(Color.decode("#f5edb3"));
        inputField.setBorder(new MatteBorder(0, 10, 0, 0, Color.WHITE));
        inputField.setPreferredSize(new Dimension(610, 50));
        inputField.setBackground(Color.decode("#739072"));
        inputField.setForeground(Color.decode("#f5edb3"));
        inputField.setFont(new Font("Poppins", Font.BOLD, 16));
        inputField.setBorder(null);

        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(80, 50));
        restartButton.setBackground(Color.yellow);
        restartButton.setForeground(Color.BLACK);
        restartButton.setFont(new Font("Poppins", Font.BOLD, 16));
        restartButton.setBorder(null);
        restartButton.setFocusable(false);

        inputPanel.add(inputField);
        inputPanel.add(restartButton);

        add(topPanel);
        add(scorePanel);
        add(wordContainer);
        add(inputPanel);

        inputField.addActionListener(e -> {
            if (inputField.getText().equals(wordList.get(0))) {
                repaintWordList();
            }
            inputField.setText("");
        });

        inputField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (!isTyping) {
                    start();
                }
                if (inputField.getText().equals(wordList.get(0))) {
                    totalChar += wordList.get(0).length();
                    if (totalChar < 0)
                        totalChar = 0;
                    WPM = Calculate.WPM(totalChar, currentTime);
                    scoreLabel.setText("Score: " + WPM + " WPM");
                    repaintWordList();
                    inputField.setText("");
                }
                if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                    totalChar -= wordList.get(0).length();
                    if (totalChar < 0)
                        totalChar = 0;
                    WPM = Calculate.WPM(totalChar, currentTime);
                    scoreLabel.setText("Score: " + WPM + " WPM");
                    inputField.setText("");
                }
            }
        });

        restartButton.addActionListener(e -> {
            restart();
            timeLabel.setText(TimeFormatter.formatSeconds(time));
            timeLabel.revalidate();
            timeLabel.repaint();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        getContentPane().setBackground(Color.decode("#3A4D39"));
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void loadWordList() {
        Random generator = new Random();
        int wordListSize = wordListData.size();
        for (int i = 0; i < 25; i++) {
            int randomIndex = generator.nextInt(wordListSize);
            wordList.add(wordListData.get(randomIndex));
        }
    }

    private void repaintWordList() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(wordListData.size());
        wordList.add(wordListData.get(randomIndex));
        wordList.remove(0);
        wordContainer.removeAll();
        int index = 0;
        for (String word : wordList) {
            if (index == 0) {
                JLabel wordListLabel = new JLabel(word);
                wordListLabel.setBackground(Color.decode("#739072"));
                wordListLabel.setForeground(Color.YELLOW);
                wordListLabel.setFont(new Font("Poppins", Font.BOLD, 16));
                wordContainer.add(wordListLabel);

                JPanel margin = new JPanel();
                margin.setBackground(Color.decode("#739072"));

                margin.setPreferredSize(new Dimension(25, 50));
                wordContainer.add(margin);

                index++;
                continue;
            }
            JLabel wordListLabel = new JLabel(word);
            wordListLabel.setBackground(Color.decode("#739072"));//background ijo tua
            wordListLabel.setForeground(Color.decode("#f5edb3"));
            wordListLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            wordListLabel.setBorder(null);
            wordContainer.add(wordListLabel);

            JPanel margin = new JPanel();
            margin.setBackground(Color.decode("#739072"));

            margin.setPreferredSize(new Dimension(25, 50));
            wordContainer.add(margin);
        }
        wordContainer.revalidate();
        wordContainer.repaint();
    }

    public void start() {
        Runnable runnable = () -> {
            for (int i = time; i >= 0; i--) {
                timeLabel.setText(TimeFormatter.formatSeconds(i));
                timeLabel.revalidate();
                timeLabel.repaint();
                currentTime = time - i;
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            inputField.setEnabled(false);
            WPM = Calculate.WPM(totalChar, time);
            scoreLabel.setText("Score: " + WPM + " WPM");
            Score.uploadScore(WPM);
            WPM = 0;
        };
        timeThread = new Thread(runnable);
        timeThread.start();
        isTyping = true;
        totalChar = 0;
    }

    public void restart() {
        timeThread.interrupt();
        wordList.clear();
        inputField.setEnabled(true);
        loadWordList();
        repaintWordList();
        isTyping = false;
    }
}
