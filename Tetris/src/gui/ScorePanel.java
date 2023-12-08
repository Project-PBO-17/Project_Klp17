package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel, scoreValue;

    public ScorePanel() {
        scoreLabel = new JLabel("SCORE");
        scoreLabel.setPreferredSize(new Dimension(100, 30));
        scoreLabel.setFont(new Font("Poppins", Font.BOLD, 25));

        scoreValue = new JLabel("0");
        scoreValue.setPreferredSize(new Dimension(100, 60));
        scoreValue.setFont(new Font("Poppins", Font.BOLD, 30));

        setLayout(new FlowLayout());
        setBounds(665, 595, 200, 100);
        setBorder(new LineBorder(Color.BLACK));
        add(scoreLabel);
        add(scoreValue);
    }
    public void setScoreText(String text) {
        scoreValue.setText(text);
    }
}
