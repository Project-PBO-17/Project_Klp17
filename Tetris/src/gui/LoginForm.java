package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import api.JDBC;
import api.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class LoginForm extends JFrame {

    public LoginForm() {

        setTitle("Login");

        JLabel loginText = new JLabel("Login");
        loginText.setPreferredSize(new Dimension(300, 25));
        loginText.setFont(new Font("Poppins", Font.BOLD, 20));
        loginText.setForeground(Color.decode("#ECE3CE"));

        JLabel detail = new JLabel("Please, enter your detail");
        detail.setPreferredSize(new Dimension(300, 15));
        detail.setFont(new Font("Poppins", Font.BOLD, 12));
        detail.setForeground(Color.WHITE);

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setPreferredSize(new Dimension(300, 15));
        emptyPanel1.setBackground(null);

        JLabel usernameText = new JLabel("Username");
        usernameText.setPreferredSize(new Dimension(300, 15));
        usernameText.setFont(new Font("Poppins", Font.BOLD, 13));
        usernameText.setForeground(Color.decode("#ECE3CE"));

        JLabel passwordText = new JLabel("Password");
        passwordText.setPreferredSize(new Dimension(300, 15));
        passwordText.setFont(new Font("Poppins", Font.BOLD, 13));
        passwordText.setForeground(Color.decode("#ECE3CE"));

        JTextField usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(300, 30));

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(new Dimension(300, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLUE);
        // loginButton.setBorder(new EmptyBorder(40, 0, 0, 0));
        loginButton.setPreferredSize(new Dimension(300, 30));
        loginButton.setFocusable(false);
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("Poppins", Font.PLAIN, 15));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(300, 20));
        emptyPanel.setBackground(null);

        JLabel accountText = new JLabel("No have an account?? ");
        accountText.setPreferredSize(new Dimension(200, 20));
        accountText.setFont(new Font("Poppins", Font.PLAIN, 13));

        JButton registerButton = new JButton("Register Now");
        registerButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        registerButton.setBorderPainted(false);
        registerButton.setBackground(null);
        registerButton.setFocusable(false);
        registerButton.setPreferredSize(new Dimension(90, 20));
        registerButton.setForeground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setSize(new Dimension(300, 300));
        panel.setBorder(new EmptyBorder(50, 0, 0, 0));
        panel.setBackground(Color.decode("#739072"));
        panel.add(loginText);
        panel.add(detail);
        panel.add(emptyPanel1);
        panel.add(usernameText);
        panel.add(usernameTextField);
        panel.add(passwordText);
        panel.add(passwordTextField);
        panel.add(emptyPanel);
        panel.add(loginButton);
        panel.add(accountText);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = usernameTextField.getText();
                String passwordInput = new String(passwordTextField.getPassword());
                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in both username and password.");
                } else {
                    loginAction(usernameInput, passwordInput);
                }

            }

        });
        usernameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e, usernameTextField, passwordTextField);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e, passwordTextField, null);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        registerButton.addActionListener(e -> {
            RegisterForm register = new RegisterForm();
            dispose();
        });

        setSize(400, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleKeyPress(KeyEvent e, JTextField currentField, JPasswordField nextField) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            nextField.requestFocusInWindow();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            nextField.requestFocusInWindow();
        }
    }

    private void loginAction(String username, String password) {
        try {
            if (Login.getAuthenticatedUser(username, password)) {
                TypingForm typing = new TypingForm();
                dispose();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }

}
