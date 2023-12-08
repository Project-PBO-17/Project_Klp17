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
import api.Register;

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

public class RegisterForm extends JFrame {
    RegisterForm() {
        setTitle("Register Form");

        JLabel registerText = new JLabel("Register");
        registerText.setPreferredSize(new Dimension(300, 25));
        registerText.setFont(new Font("Poppins", Font.BOLD, 20));
        registerText.setForeground(Color.decode("#ECE3CE"));

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

        JLabel confirmPasswordText = new JLabel("Confirm Password");
        confirmPasswordText.setPreferredSize(new Dimension(300, 15));
        confirmPasswordText.setFont(new Font("Poppins", Font.BOLD, 13));
        confirmPasswordText.setForeground(Color.decode("#ECE3CE"));

        JTextField usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(300, 30));

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(new Dimension(300, 30));

        JPasswordField confirmpasswordTextField = new JPasswordField();
        confirmpasswordTextField.setPreferredSize(new Dimension(300, 30));

        JButton regisButton = new JButton("Register");
        regisButton.setBackground(Color.BLUE);
        // regisButton.setBorder(new EmptyBorder(40, 0, 0, 0));
        regisButton.setPreferredSize(new Dimension(300, 30));
        regisButton.setFocusable(false);
        regisButton.setForeground(Color.white);
        regisButton.setFont(new Font("Poppins", Font.PLAIN, 15));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(300, 20));
        emptyPanel.setBackground(null);

        JLabel accountText = new JLabel("Already have an account?? ");
        accountText.setPreferredSize(new Dimension(200, 20));
        accountText.setFont(new Font("Poppins", Font.PLAIN, 13));

        JButton logButton = new JButton("Login Now");
        logButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        logButton.setBorderPainted(false);
        logButton.setBackground(null);
        logButton.setFocusable(false);
        logButton.setPreferredSize(new Dimension(90, 20));
        logButton.setForeground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setSize(new Dimension(300, 300));
        panel.setBorder(new EmptyBorder(50, 0, 0, 0));
        panel.setBackground(Color.decode("#739072"));
        panel.add(registerText);
        panel.add(detail);
        panel.add(emptyPanel1);
        panel.add(usernameText);
        panel.add(usernameTextField);
        panel.add(passwordText);
        panel.add(passwordTextField);
        panel.add(confirmPasswordText);
        panel.add(confirmpasswordTextField);
        panel.add(emptyPanel);
        panel.add(regisButton);
        panel.add(accountText);
        panel.add(logButton);

        regisButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = usernameTextField.getText();
                String passwordInput = new String(passwordTextField.getPassword());
                String confirmPasswordInput = new String(confirmpasswordTextField.getPassword());

                if (usernameInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else if (!passwordInput.equals(confirmPasswordInput)) {
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match.");
                } else {
                    boolean success = Register.insertUser(usernameInput, passwordInput);
                    if (success==true) {
                       // JOptionPane.showMessageDialog(null, "Registration successful.");
                       int option = JOptionPane.showOptionDialog(null,"Registration successful.","Success",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,null,new Object[] { "OK" },"OK");
                        if (option == JOptionPane.OK_OPTION) {
                            LoginForm logg = new LoginForm();

                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed.");
                    }
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

        logButton.addActionListener(e -> {
            LoginForm login = new LoginForm();
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

    public static void main(String[] args) {
        new RegisterForm();
    }
}