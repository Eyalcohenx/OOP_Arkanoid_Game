package oop.ass7game;

import java.util.List;

/**
 * interface LevelInformation.
 */
public interface LevelInformation {
    /**
     * @return - num of balls.
     */
    int numberOfBalls();

    /**
     * @return -  // The initial velocity of each ball.
     * // Note that initialBallVelocities().size() == numberOfBalls().
     */
    List<Velocity> initialBallVelocities();


    /**
     * @return - paddle speed.
     */
    int paddleSpeed();

    /**
     * @return - paddle width.
     */
    int paddleWidth();


    /**
     * @return - the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return - Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return -     // The Blocks that make up this level, each block contains
     * // its size, color and location.
     */
    List<Block> blocks();


    /**
     * @return - // Number of levels that should be removed
     * // before the level is considered to be "cleared".
     * // This number should be <= blocks.size().
     */
    int numberOfBlocksToRemove();

    /**
     * @return balls list for the level.
     */
    List<Ball> balls();

    /**
     * @return level as string;
     */
    String toString();
}