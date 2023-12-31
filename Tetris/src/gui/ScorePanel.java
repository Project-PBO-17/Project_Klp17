package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import api.UserData;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel, scoreValue, leaderboardLabel,userLabel;
    private JPanel leaderboardPanel;

    public ScorePanel() {
        scoreLabel = new JLabel("Current Score: ");
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
        leaderboardPanel.setLayout(new FlowLayout());
        leaderboardPanel.setPreferredSize(new Dimension(160, 120));
        leaderboardPanel.setBackground(Color.BLACK);

        updateLeaderboard(); // Mengisi data leaderboard awal

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
    }


    public void updateLeaderboard() {
        leaderboardPanel.removeAll();
        UserData.getUserData();
        for (int i = 0; i < UserData.sortedUsers.size(); i++) {
            userLabel = new JLabel((i + 1) + ". " + UserData.sortedUsers.get(i).getUserName() + ": " + UserData.sortedUsers.get(i).getScore());
            userLabel.setForeground(Color.white);
            userLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
            userLabel.setPreferredSize(new Dimension(160, 12));
            leaderboardPanel.add(userLabel);
        }

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        repaint();
    }
}
