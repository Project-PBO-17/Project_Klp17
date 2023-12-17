package gui;

import javax.swing.*;

import util.ComponentUnit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StartForm extends JFrame implements ComponentUnit{
    private JPanel mainPanel;

    public StartForm() {
        initComponent();
        initControls();
        mainPanel.setBackground(Color.BLACK);
        TetrisMain.loopOpening();
    }
    @Override
    public void initComponent() {
        setTitle("Menu Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(this);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }
    @Override
    public void initControls() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(34, 40, 49));
        GridBagConstraints gbc = new GridBagConstraints();

        // Logo
        ImageIcon tetrisIcon = createScaledIcon("E:\\IT\\Code\\Project_Klp17\\Tetris" +
                "\\src\\assets\\logo tetris.png", 250, 200);
        JLabel logoLabel = new JLabel(tetrisIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 10, 3, 10);

        JButton playButton = createStyledButton("Play");
        JButton leaderboardButton = createStyledButton("Leaderboard");
        JButton quitButton = createStyledButton("Quit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TetrisMain.stopOpening();
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new GameForm();
                });
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    try {
                        new LeaderboardForm();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                });
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.exit(0);
            }
        });

        mainPanel.add(playButton, gbc);
        gbc.gridy++;
        mainPanel.add(leaderboardButton, gbc);
        gbc.gridy++;
        mainPanel.add(quitButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 82, 148));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    private ImageIcon createScaledIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
