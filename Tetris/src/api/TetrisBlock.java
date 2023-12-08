package api;

import java.awt.Color;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;

    private int[][][] shapes;
    private int currentRotation;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        initShape();
    }

    public void Spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;
    }

    public void initShape() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int row = shape[0].length;
            int col = shape.length;
            shapes[i] = new int[row][col];
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < col; y++) {
                    shapes[i][x][y] = shape[col - y - 1][x];
                }
            }
            shape = shapes[i];
        }
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftSide() {
        return x;
    }

    public int getRightSide() {
        return x + getWidth();
    }

    public void rotateBlock(int gridRows, int gridColumns) {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }

        // Get the shape of the next rotation
        int[][] nextShape = shapes[currentRotation];

        // Calculate the dimensions of the next shape
        int nextWidth = nextShape[0].length;
        int nextHeight = nextShape.length;

        // Calculate the position of the next rotation
        int nextX = x;
        int nextY = y;

        // Check if the next rotation is within the bounds of the panel
        if (nextX >= 0 && nextX + nextWidth <= gridColumns && nextY >= 0 && nextY + nextHeight <= gridRows) {
            shape = shapes[currentRotation];
        }
    }
    
}
