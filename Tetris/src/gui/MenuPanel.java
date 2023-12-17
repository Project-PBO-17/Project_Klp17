package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import util.ComponentUnit;
import util.LeaderBoardGameThread;

public class MenuPanel extends JPanel implements ActionListener,ComponentUnit {
    private JLabel menu;
    private JButton exitButton, restartButton, pauseButton;
    private GameForm gameForm;
    private boolean pauseGame = false;

    public MenuPanel(GameForm gameForm) {
        this.gameForm = gameForm;
        initComponent();
        initControls();
    }
    @Override
    public void initComponent(){
        menu = new JLabel("MENU");
        menu.setForeground(Color.white);
        menu.setPreferredSize(new Dimension(170, 30));
        menu.setFont(new Font("Poppins", Font.BOLD, 25));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBounds(25, 10, 200, 160);
        setBorder(new LineBorder(Color.WHITE));
        add(menu);
    }
    @Override
    public void initControls() {
        exitButton = new JButton("Exit Game");
        exitButton.setPreferredSize(new Dimension(170, 30));
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        exitButton.addActionListener(this);

        restartButton = new JButton("Restart Game");
        restartButton.setPreferredSize(new Dimension(170, 30));
        restartButton.setFocusable(false);
        restartButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        restartButton.addActionListener(this);

        pauseButton = new JButton("Pause Game");
        pauseButton.setPreferredSize(new Dimension(170, 30));
        pauseButton.setFocusable(false);
        pauseButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        pauseButton.addActionListener(this);

        add(restartButton);
        add(pauseButton);
        add(exitButton);
    }

    public void setPauseGame(boolean pauseGame) {
        this.pauseGame = pauseGame;
    }

    public boolean getPauseGame() {
        return pauseGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            TetrisMain.stopBackground();
            gameForm.stopGameThread();
            gameForm.dispose();
            new StartForm();
        } else if (e.getSource() == restartButton) {
            gameForm.restartGame();
            SwingUtilities.updateComponentTreeUI(gameForm);
        } else if (e.getSource() == pauseButton) {
            gameForm.pauseGame();

        }
    }
}
