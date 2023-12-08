package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import api.JDBC;
import api.UserData;

import java.awt.*;


public class Profil extends JFrame{
    public Profil(){

        JLabel profilText = new JLabel("Profil Anda");
        profilText.setPreferredSize(new Dimension(100,20));
        profilText.setFont(new Font("Poppins", Font.BOLD, 18));

        JLabel username = new JLabel("Username: ");
        username.setPreferredSize(new Dimension(240, 20));
        username.setFont(new Font("Poppins", Font.PLAIN, 15));

        JLabel highScore = new JLabel("Best Score: ");
        highScore.setPreferredSize(new Dimension(240, 20));
        highScore.setFont(new Font("Poppins", Font.PLAIN, 15));

        JLabel numTry = new JLabel("Number of Try: ");
        numTry.setPreferredSize(new Dimension(240, 20));
        numTry.setFont(new Font("Poppins", Font.PLAIN, 15));

        JLabel password = new JLabel("Password: ");
        password.setPreferredSize(new Dimension(240, 20));
        password.setFont(new Font("Poppins", Font.PLAIN, 15));

        JLabel usernameValue = new JLabel(JDBC.username);
        usernameValue.setPreferredSize(new Dimension(240, 20));
        usernameValue.setFont(new Font("Poppins", Font.PLAIN, 15));


        String highScoreString = String.valueOf(UserData.highScore);
        JLabel highScoreValue = new JLabel(highScoreString);
        highScoreValue.setPreferredSize(new Dimension(240, 20));
        highScoreValue.setFont(new Font("Poppins", Font.PLAIN, 15));

        String numTryString = String.valueOf(UserData.tryNumber);
        JLabel numTryValue = new JLabel(numTryString);
        numTryValue.setPreferredSize(new Dimension(240, 20));
        numTryValue.setFont(new Font("Poppins", Font.PLAIN, 15));
        
        JLabel passwordValue = new JLabel(JDBC.password);
        passwordValue.setPreferredSize(new Dimension(240, 20));
        passwordValue.setFont(new Font("Poppins", Font.PLAIN, 15));
        
        JPanel profilTextPanel = new JPanel();
        profilTextPanel.setBorder(new EmptyBorder(0, 100, 0, 100));
        profilTextPanel.setSize(new Dimension(300, 30));
        profilTextPanel.setBackground(Color.gray);
        profilTextPanel.add(profilText);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setSize(new Dimension(300, 30));
        usernamePanel.add(usernameValue);

        JPanel highScorePanel = new JPanel();
        highScorePanel.setSize(new Dimension(300, 30));
        highScorePanel.add(highScoreValue);

        JPanel numTryPanel = new JPanel();
        numTryPanel.setSize(new Dimension(300, 30));
        numTryPanel.add(numTryValue);
        
        JPanel passwordPanel = new JPanel();
        passwordPanel.setSize(new Dimension(300, 30));
        passwordPanel.add(passwordValue);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300,300));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBorder(new EmptyBorder(20,0,0,0));
        mainPanel.setBackground(Color.decode("#739072"));
        mainPanel.add(profilTextPanel);
        mainPanel.add(username);
        mainPanel.add(usernamePanel);
        mainPanel.add(password);
        mainPanel.add(passwordPanel);
        mainPanel.add(highScore);
        mainPanel.add(highScorePanel);
        mainPanel.add(numTry);
        mainPanel.add(numTryPanel);

        setTitle("Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400,450);
        setResizable(false);
        setLocationRelativeTo(this);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        
    }
    public static void main(String[] args) {
        new Profil();
    }
}