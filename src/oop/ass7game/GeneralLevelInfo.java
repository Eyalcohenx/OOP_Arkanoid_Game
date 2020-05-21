package oop.ass7game;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * class GeneralLevelInfo implements LevelInformation.
 */
public class GeneralLevelInfo implements LevelInformation {
    private String levelName;
    private List<Velocity> ballVelocities;
    private Background background;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blockDefinitions;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;
    private BlocksFromSymbolsFactory blocksFactory;


    @Override
    public int numberOfBalls() {
        return ballVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    /**
     * @param background1 .
     */
    public void setBackground(Background background1) {
        this.background = background1;
    }

    @Override
    public List<Block> blocks() {
        return blockDefinitions;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numBlocks;
    }

    @Override
    public List<Ball> balls() {
        double startx = WIDTH / 2;
        List<Ball> balls = new ArrayList<Ball>();
        for (Velocity v : ballVelocities) {
            balls.add(new Ball((int) startx, HEIGHT - 20, 5, Color.WHITE));
        }
        return balls;
    }

    /**
     * @param levelName1 .
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * @param ballVelocities1 .
     */
    public void setBallVelocities(List<Velocity> ballVelocities1) {
        this.ballVelocities = ballVelocities1;
    }

    /**
     * @param paddleSpeed1 .
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * @param paddleWidth1 .
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * @param blockDefinitions1 .
     */
    public void setBlockDefinitions(List<Block> blockDefinitions1) {
        this.blockDefinitions = blockDefinitions1;
    }

    /**
     * @param numBlocks1 .
     */
    public void setNumBlocks(int numBlocks1) {
        this.numBlocks = numBlocks1;
    }

    /**
     * @return .
     */
    public int getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * @param blocksStartX1 .
     */
    public void setBlocksStartX(int blocksStartX1) {
        this.blocksStartX = blocksStartX1;
    }

    /**
     * @return .
     */
    public int getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * @param blocksStartY1 .
     */
    public void setBlocksStartY(int blocksStartY1) {
        this.blocksStartY = blocksStartY1;
    }

    /**
     * @return .
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * @param rowHeight1 .
     */
    public void setRowHeight(int rowHeight1) {
        this.rowHeight = rowHeight1;
    }

    /**
     * @return .
     */
    public BlocksFromSymbolsFactory getBlocksFactory() {
        return blocksFactory;
    }

    /**
     * @param blocksFactory1 .
     */
    public void setBlocksFactory(BlocksFromSymbolsFactory blocksFactory1) {
        this.blocksFactory = blocksFactory1;
    }
}
