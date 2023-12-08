package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import api.TetrisBlock;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;

    private TetrisBlock block;

    public GameArea(int columns) {
        setBounds(250, 15, 400, 680);
        setBackground(Color.decode("#e6e7ed"));
        setBorder(new LineBorder(Color.BLACK, 2));

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        background = new Color[gridRows][gridColumns];
        // background[3][0] = Color.green;
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][] { { 1, 0 }, { 1, 0 }, { 1, 1 } }, Color.GREEN);
        block.Spawn(gridColumns);
    }

    public boolean MoveBlockDown() {
        if (!checkBottom()) {
            setBlockToBackground();
            clearLines();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }

    public void MoveBlockLeft() {
        if (!checkLeft()) {
            return;
        }
        block.moveLeft();
        repaint();
    }

    public void MoveBlockRight() {
        if (!checkRight()) {
            return;
        }
        block.moveRight();
        repaint();
    }

    public void MoveBlockQuick() {
        if (checkBottom()) {
            block.moveDown();
            repaint();
        }
    }

    public void rotationBock() {
        block.rotateBlock(gridRows, gridColumns);
        repaint();
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false;
        }
        for (int col = 0; col < block.getWidth(); col++) {
            for (int row = block.getHeight() - 1; row >= 0; row--) {
                if (block.getShape()[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    // because of block start grom negatif value
                    if (y < 0)
                        break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkLeft() {
        if (block.getLeftSide() == 0) {
            return false;
        }
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    // because of block start grom negatif value
                    if (y < 0)
                        break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkRight() {
        if (block.getRightSide() == gridColumns) {
            return false;
        }
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = block.getWidth() - 1; col >= 0; col--) {
                if (block.getShape()[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    // because of block start grom negatif value
                    if (y < 0)
                        break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void clearLines() {
        boolean checkLines;
        for (int row = gridRows - 1; row >= 0; row--) {
            checkLines = false;
            for (int col = 0; col < gridColumns; col++) {
                if (background[row][col] == null) {
                    checkLines = true;
                    break;
                }
            }
            if (!checkLines) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }

        }
    }

    private void clearLine(int row) {
        for (int colDelete = 0; colDelete < gridColumns; colDelete++) {
            background[row][colDelete] = null;
        }
    }

    private void shiftDown(int rowMove) {
        for (int row = rowMove; row >0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row-1][col];
            }
        }
    }

    private void setBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    background[row + yPos][col + xPos] = color;
                }
            }

        }
    }

    private void drawBlock(Graphics g) {
        // build block
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {

                    int x = (xPos + col) * gridCellSize;
                    int y = (yPos + row) * gridCellSize;
                    drawGrid(g, x, y, block.getColor());
                }
            }

        }
    }

    private void drawBackground(Graphics g) {
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                color = background[row][col];

                if (color != null) {

                    int x = col * gridCellSize;
                    int y = row * gridCellSize;
                    drawGrid(g, x, y, color);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawBlock(g);

    }
}
