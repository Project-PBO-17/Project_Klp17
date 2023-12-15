import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Menu Start");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false); 
        frame.getContentPane().setBackground(new Color(34, 40, 49)); 

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(34, 40, 49)); 
        GridBagConstraints gbc = new GridBagConstraints();

        // Logo
        ImageIcon tetrisIcon = createScaledIcon("D:\\Juanri\\Game Tetris\\src\\image\\logo tetris.png", 250, 200);
        JLabel logoLabel = new JLabel(tetrisIcon);

        // Place the logo above the buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Span only one column
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(logoLabel, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 10, 3, 10);

        JButton singlePlayerButton = createStyledButton("Single Player");
        JButton multiPlayerButton = createStyledButton("Multiplayer");
        JButton leaderboardButton = createStyledButton("Leaderboard");
        JButton quitButton = createStyledButton("Quit");

        singlePlayerButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Single Player button clicked"));
        multiPlayerButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Multiplayer button clicked"));
        leaderboardButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Leaderboard button clicked"));
        quitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(singlePlayerButton, gbc);
        gbc.gridy++;
        mainPanel.add(multiPlayerButton, gbc);
        gbc.gridy++;
        mainPanel.add(leaderboardButton, gbc);
        gbc.gridy++;
        mainPanel.add(quitButton, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 82, 148)); 
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        return button;
    }

    private static ImageIcon createScaledIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
