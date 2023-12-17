package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import api.TetrisBlock;

public class NextBlockPanel extends JPanel {
    private JLabel next;
    private JPanel panel;
    private TetrisBlock nextBlock;
    private int gridRows;
    private int gridColumns = 6;
    private int gridCellSize;

    public NextBlockPanel() {
        next = new JLabel("NEXT");
        next.setForeground(Color.white);
        next.setPreferredSize(new Dimension(80, 30));
        next.setFont(new Font("Poppins", Font.BOLD, 25));

        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gridCellSize = panel.getBounds().width / gridColumns;
                gridRows = panel.getBounds().height / gridCellSize;
                for (int row = 0; row < gridRows; row++) {
                    for (int col = 0; col < gridColumns; col++) {
                        g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
                    }
                }
                if (nextBlock != null) {
                    drawBlock(g, nextBlock);
                }
            }

        };
        panel.setPreferredSize(new Dimension(180, 240));
        panel.setBorder(new LineBorder(Color.cyan, 2));

        setLayout(new FlowLayout());
        setBounds(665, 10, 200, 310);
        setBorder(new LineBorder(Color.white));
        add(next);
        add(panel);
    }

    public void setNextBlock(TetrisBlock block) {
        nextBlock = block;
        repaint();
    }

    private void drawBlock(Graphics g, TetrisBlock block) {
        int[][] shape = block.getShape();
        int blockWidth = block.getWidth();
        int blockHeight = block.getHeight();

        int startX = (gridColumns - blockWidth) / 2 * gridCellSize;
        int startY = (gridRows - blockHeight) / 2 * gridCellSize;       

        for (int row = 0; row < blockHeight; row++) {
            for (int col = 0; col < blockWidth; col++) {
                if (shape[row][col] == 1) {
                    int x = startX + col * gridCellSize;
                    int y = startY + row * gridCellSize;
                    drawGrid(g, x, y, block.getColor());
                }
            }
        }
    }

    private void drawGrid(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

}
