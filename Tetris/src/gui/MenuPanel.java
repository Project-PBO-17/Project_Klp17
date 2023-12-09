package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MenuPanel extends JPanel{
    private JLabel menu;
    private JButton exitButton, restartButton, pauseButton;
    public MenuPanel(){
        menu = new JLabel("MENU");
        menu.setForeground(Color.white);
        menu.setPreferredSize(new Dimension(170,30));
        menu.setFont(new Font("Poppins", Font.BOLD, 25));

        exitButton = new JButton("Exit Game");
        exitButton.setPreferredSize(new Dimension(170, 30));
        exitButton.setFocusable(false);
        //exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Poppins", Font.PLAIN, 12));

        restartButton = new JButton("Restart Game");
        restartButton.setPreferredSize(new Dimension(170, 30));
        restartButton.setFocusable(false);
       // restartButton.setForeground(Color.white);
        restartButton.setFont(new Font("Poppins", Font.PLAIN, 12));

        pauseButton = new JButton("Pause Game");
       pauseButton.setPreferredSize(new Dimension(170, 30));
       pauseButton.setFocusable(false);
       //pauseButton.setForeground(Color.white);
       pauseButton.setFont(new Font("Poppins", Font.PLAIN, 12));

        setLayout(new FlowLayout());
        setBounds(25, 10, 200, 160);
        setBorder(new LineBorder(Color.WHITE));
        add(menu);
        add(restartButton);
        add(pauseButton);
        add(exitButton);
    }
}
