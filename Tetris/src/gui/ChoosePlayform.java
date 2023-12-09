package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChoosePlayform extends JFrame {
    JPanel panel;

    public ChoosePlayform() {
        initComponent();
        panelComponent();
    }

    private void initComponent() {
        setTitle("Play Games");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void panelComponent() {
        JButton singleButton, vsButton;
        panel = new JPanel();

        singleButton = new JButton("Play Single");
        singleButton.setFocusable(false);
        singleButton.setBounds(200, 200, 100, 30);

        vsButton = new JButton("Play Vs");
        vsButton.setFocusable(false);
        vsButton.setBounds(200, 240, 100, 30);

        panel.setBounds(20, 15, 545, 435);
        panel.setBorder(new LineBorder(Color.BLACK));
        panel.add(singleButton);
        panel.add(vsButton);
        getContentPane().setLayout(null);
        add(panel);
    }

    public static void main(String[] args) {
        new ChoosePlayform();
    }
}
