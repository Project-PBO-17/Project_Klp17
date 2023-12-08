package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LevelPanel extends JPanel{
    private JLabel levelLabel, levelValue;
    public LevelPanel(){
        levelLabel = new JLabel("LEVEL");
        levelLabel.setPreferredSize(new Dimension(100, 30));
        levelLabel.setFont(new Font("Poppins", Font.BOLD, 25));

        levelValue = new JLabel("1");
        levelValue.setPreferredSize(new Dimension(100, 60));
        levelValue.setFont(new Font("Poppins", Font.BOLD, 50));

        setLayout(new FlowLayout());
        setBounds(25, 595, 200, 100);
        setBorder(new LineBorder(Color.BLACK));
        add(levelLabel);
        add(levelValue);
    }
    public void setLevelText(String text) {
        levelValue.setText(text);
    }
}
