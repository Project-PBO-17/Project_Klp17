package api;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;

    private int[][][] shapes;
    private int currentRotation;

    private Color[] blockClolor = {Color.RED, Color.YELLOW, Color.BLUE, Color.green};

    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        initShape();
    }

    public void Spawn(int gridWidth) {
        Random random = new Random();
        currentRotation = random.nextInt(shapes.length);
        shape = shapes[currentRotation];
        y = -getHeight();
        x = random.nextInt(gridWidth - getWidth());
        color = blockClolor[random.nextInt(blockClolor.length)];
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
    public void setX(int newX){
        x = newX;
    }
    public void setY(int newY){
        y = newY;
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

    public void rotateBlock() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }
    public int[][] getRotatedShape() {
        int[][] rotatedShape = new int[getWidth()][getHeight()];

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                rotatedShape[col][getHeight() - 1 - row] = shape[row][col];
            }
        }

        return rotatedShape;
    }
    
}
