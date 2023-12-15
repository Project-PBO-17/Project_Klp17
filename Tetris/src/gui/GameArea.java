package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import api.TetrisBlock;
import tetrisBlocks.*;
import util.GameThread;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;

    private TetrisBlock block;
    private TetrisBlock[] blocks;
    private NextBlockPanel nextBlockPanel;
    private TetrisBlock lastSpawnedBlock, newBlock;
    private ArrayList<TetrisBlock> arrayTet = new ArrayList<>();

    public GameArea(int columns, int x, int y, int widthh, int heightt) {
        setBounds(x, y, widthh, heightt);
        //setBounds(245, 15, 400, 680);
        // setBackground(Color.decode("#e6e7ed"));
        setBackground(Color.black);
        setBorder(new LineBorder(Color.white, 6));

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        background = new Color[gridRows][gridColumns];
        blocks = new TetrisBlock[] { new IShape(),
                new JShape(),
                new LShape(),
                new OShape(),
                new SShape(),
                new TShape(),
                new ZShape() };
    }

    public int getGridCellSize() {
        return gridCellSize;
    }

    public void setNextBlockPanel(NextBlockPanel nextBlockPanel) {
        this.nextBlockPanel = nextBlockPanel;
    }

    public TetrisBlock getBlock() {
        return lastSpawnedBlock;
    }

    public void setBlock(TetrisBlock block) {
        lastSpawnedBlock = block;
    }

    public void spawnBlock() {
        Random random = new Random();
        if (arrayTet.isEmpty()) {
            TetrisBlock data1 = blocks[random.nextInt(blocks.length)];
            TetrisBlock data2 = blocks[random.nextInt(blocks.length)];
            arrayTet.add(data1);
            arrayTet.add(data2);
        }
        newBlock = arrayTet.get(1);
        lastSpawnedBlock = arrayTet.get(0);
        nextBlockPanel.setNextBlock(newBlock);
        block = getBlock();
        int blockColor = random.nextInt(block.getBlockColor().length);
        int rotateNumber = random.nextInt(block.getShapes().length);
        
        block.Spawn(gridColumns,blockColor, rotateNumber);
    }

    public boolean isBlockOutOfBound() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    public boolean MoveBlockDown() {
        Random random = new Random();
        if (!checkBottom()) {
            TetrisBlock temp = blocks[random.nextInt(blocks.length)];
            arrayTet.remove(0);
            setBlock(newBlock);
            arrayTet.add(temp);
            nextBlockPanel.setNextBlock(arrayTet.get(1));

            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }

    public void MoveBlockLeft() {
        if (block == null) {
            return;
        }
        if (!checkLeft()) {
            return;
        }
        block.moveLeft();
        repaint();
    }

    public void MoveBlockRight() {
        if (block == null) {
            return;
        }
        if (!checkRight()) {
            return;
        }
        block.moveRight();
        repaint();
    }

    public void MoveBlockQuick() {
        if (block == null) {
            return;
        }
        if (checkBottom()) {
            block.moveDown();
            repaint();
        }
    }

    public void rotationBock() {
        if (block == null) {
            return;
        }
        block.rotateBlock();
        /*
         * if (!checkRotation()) {
         * return;
         * }
         */
        if (block.getLeftSide() < 0) {
            block.setX(0);
        }
        if (block.getRightSide() >= gridColumns) {
            block.setX(gridColumns - block.getWidth());
        }
        if (block.getBottomEdge() >= gridRows) {
            block.setY(gridRows - block.getHeight());
        }

        repaint();
    }

    /*
     * private boolean checkRotation() {
     * int[][] shape = block.getShape();
     * int h = block.getHeight();
     * int w = block.getWidth();
     * 
     * for (int row = 0; row < h; row++) {
     * for (int col = 0; col < w; col++) {
     * if (shape[row][col] != 0) {
     * int x = col + block.getX();
     * int y = row + block.getY();
     * 
     * // Pengecekan khusus untuk blok panjang (Ishape)
     * if (block instanceof IShape && col == 1) {
     * int leftX = x - 1;
     * int rightX = x + 1;
     * 
     * // Cek apakah rotasi akan menabrak sel yang sudah terisi
     * if (leftX >= 0 && background[y][leftX] != null) {
     * return false;
     * }
     * if (rightX < gridColumns && background[y][rightX] != null) {
     * return false;
     * }
     * }
     * 
     * // Pengecekan umum untuk sel lainnya
     * if (x < 0 || x >= gridColumns || y < 0 || y >= gridRows || background[y][x]
     * != null) {
     * return false;
     * }
     * }
     * }
     * }
     * return true;
     * }
     */

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

    public int clearLines() {
        boolean checkLines;
        int lineCleared = 0;
        int scoreGet = 0;
        for (int row = gridRows - 1; row >= 0; row--) {
            checkLines = false;
            for (int col = 0; col < gridColumns; col++) {
                if (background[row][col] == null) {
                    checkLines = true;
                    break;
                }
            }
            if (!checkLines) {
                lineCleared++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }
            scoreGet = ScorePoint(lineCleared);
        }
        return scoreGet;
    }

    private int ScorePoint(int line) {
        int scoreGet = 0;
        if (line == 1) {
            scoreGet = 100;
        } else if (line == 2) {
            scoreGet = 300;
        } else if (line == 3) {
            scoreGet = 500;
        }else if(line ==4){
            scoreGet = 800;
        }
        return scoreGet;
    }

    private void clearLine(int row) {
        for (int colDelete = 0; colDelete < gridColumns; colDelete++) {
            background[row][colDelete] = null;
        }
    }

    private void shiftDown(int rowMove) {
        for (int row = rowMove; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row - 1][col];
            }
        }
    }

    public void setBlockToBackground() {
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

    public void drawGrid(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    private void gridCell(Graphics g) {
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gridCell(g);

        drawBackground(g);
        if (block == null) {
            spawnBlock();
        }
        drawBlock(g);

    }
}
