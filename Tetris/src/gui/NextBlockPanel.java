package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class NextBlockPanel extends JPanel {
    private JLabel next;
    public NextBlockPanel(){
        next = new JLabel("NEXT");
        next.setPreferredSize(new Dimension(80,30));
        next.setFont(new Font("Poppins", Font.BOLD, 25));

        setLayout(new FlowLayout());
        setBounds(665, 10, 200, 310);
        setBorder(new LineBorder(Color.BLACK));
        add(next);
    }
}
