package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmationDialog extends JDialog {
    private boolean result;

    public ConfirmationDialog(JFrame parent, String message) {
        super(parent, "Confirmation", true);
        initialize(message);
    }

    private void initialize(String message) {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(message);
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = true;
                dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = false;
                dispose();
            }
        });

        panel.add(label);
        panel.add(yesButton);
        panel.add(noButton);

        setContentPane(panel);
        setSize(new Dimension(300, 120));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public boolean getResult() {
        return result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Frame");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton showDialogButton = new JButton("Show Dialog");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmationDialog dialog = new ConfirmationDialog(frame, "Continue the game?");
                dialog.setVisible(true);

                if (dialog.getResult()) {
                    JOptionPane.showMessageDialog(frame, "User chose Yes. Continue the game...");
                    // Add logic to continue the game
                } else {
                    JOptionPane.showMessageDialog(frame, "User chose No. Game paused or exited...");
                    // Add logic to pause or exit the game
                }
            }
        });

        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(showDialogButton);

        frame.setVisible(true);
    }
}
