package oop.ass7game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * class WideEasy implements LevelInformation.
 */
public class WideEasy implements LevelInformation {
    private static Background background = new Background("sunRay.PNG");

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //velocities
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(160, 4));
        velocities.add(Velocity.fromAngleAndSpeed(140, 4));
        velocities.add(Velocity.fromAngleAndSpeed(120, 4));
        velocities.add(Velocity.fromAngleAndSpeed(100, 4));
        velocities.add(Velocity.fromAngleAndSpeed(90, 4));
        velocities.add(Velocity.fromAngleAndSpeed(90, 4));
        velocities.add(Velocity.fromAngleAndSpeed(80, 4));
        velocities.add(Velocity.fromAngleAndSpeed(60, 4));
        velocities.add(Velocity.fromAngleAndSpeed(40, 4));
        velocities.add(Velocity.fromAngleAndSpeed(20, 4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 450;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blockHeight = 15;
        int blockWidth = (WIDTH - 40) / 15;

        //counter for the colors
        int counter = 1;
        //creating the blocks.
        for (int i = 22; i < WIDTH - 30; i += (WIDTH - 40) / 15) {
            Block block = new Block(new Rectangle(new Point((double) i,
                    (double) HEIGHT / 2.5), blockWidth, blockHeight));
            //switch case for the block colors.
            if (counter >= 1 && counter <= 2) {
                block.setColors(Color.RED);
            } else if (counter >= 3 && counter <= 4) {
                block.setColors(Color.ORANGE);
            } else if (counter >= 5 && counter <= 6) {
                block.setColors(Color.YELLOW);
            } else if (counter >= 7 && counter <= 9) {
                block.setColors(Color.GREEN);
            } else if (counter >= 10 && counter <= 11) {
                block.setColors(Color.BLUE);
            } else if (counter >= 12 && counter <= 13) {
                block.setColors(Color.PINK);
            } else if (counter >= 14 && counter <= 15) {
                block.setColors(Color.CYAN);
            }
            counter++;
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public List<Ball> balls() {
        double space = WIDTH / 20;
        double startx = WIDTH / 4;
        double starty = HEIGHT * 2 / 3;
        List<Ball> balls = new ArrayList<Ball>();
        //balls.
        Ball ball1 = new Ball((int) startx, (int) starty, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + space), (int) starty - 20, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 2 * space), (int) starty - 35, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 3 * space), (int) starty - 45, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 4 * space), (int) starty - 45, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 5 * space), (int) starty - 45, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 6 * space), (int) starty - 45, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 7 * space), (int) starty - 35, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 8 * space), (int) starty - 20, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 9 * space), (int) starty, 5, Color.WHITE);
        balls.add(ball1);
        return balls;
    }
}
