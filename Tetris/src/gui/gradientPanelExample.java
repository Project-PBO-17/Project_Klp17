package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class gradientPanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gradient Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel gradientPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    // Define the start and end points for the gradient
                    Point2D start = new Point2D.Float(0, 0);
                    Point2D end = new Point2D.Float(getWidth(), getHeight());

                    // Define the colors for the gradient (startColor and endColor)
                    Color startColor = Color.decode("#ECE3CE");
                    Color endColor = Color.decode("#3498DB");

                    // Create the gradient paint
                    GradientPaint gradient = new GradientPaint(start, startColor, end, endColor);

                    // Set the paint to the gradient and fill the panel
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };

            frame.add(gradientPanel);
            frame.setVisible(true);
        });
    }
}
