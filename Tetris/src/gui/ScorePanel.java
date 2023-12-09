package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import api.UserData;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel, scoreValue, leaderboardLabel;
    private JPanel leaderboardPanel;
    ArrayList<String> user = new ArrayList<>(Arrays.asList("aldar: 10000", "Hifric: 9000", "Ramadhan: 5000", "aldarr: 4500", "ramadhann: 3000"));

    public ScorePanel() {
        scoreLabel = new JLabel("Current Score: 0");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setPreferredSize(new Dimension(160, 30));
        scoreLabel.setFont(new Font("Poppins", Font.BOLD, 12));

        scoreValue = new JLabel("0");
        scoreValue.setForeground(Color.white);
        scoreValue.setPreferredSize(new Dimension(160, 60));
        scoreValue.setFont(new Font("Poppins", Font.BOLD, 30));

        leaderboardLabel = new JLabel("Leaderboard");
        leaderboardLabel.setForeground(Color.white);
        leaderboardLabel.setFont(new Font("Poppins", Font.BOLD, 16));

        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leaderboardPanel.setPreferredSize(new Dimension(160, 100));
        leaderboardPanel.setBackground(Color.BLACK);

        for (int i = 0; i < user.size(); i++) {
            JLabel userLabel = new JLabel((i + 1) + ". " + user.get(i));
            userLabel.setForeground(Color.white);
            userLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
            userLabel.setPreferredSize(new Dimension(160,12));
            leaderboardPanel.add(userLabel);
        }

        setLayout(new FlowLayout());
        setBounds(665, 385, 200, 300);
        setBorder(new LineBorder(Color.white));

        add(leaderboardLabel);
        add(leaderboardPanel);
        add(scoreLabel);
        add(scoreValue);
    }

    public void setScoreText(String text) {
        scoreValue.setText(text);
        scoreLabel.setText("Current Score: "+ text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        // g.drawArc(30, 0, 100, 60, 0, 180);
        repaint();
    }
}
