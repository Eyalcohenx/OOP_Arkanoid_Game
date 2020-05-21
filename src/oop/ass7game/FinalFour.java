package oop.ass7game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * class FinalFour implements LevelInformation.
 */
public class FinalFour implements LevelInformation {
    private static Background background = new Background("clouds2.PNG");

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(140, 4));
        velocities.add(Velocity.fromAngleAndSpeed(90, 4));
        velocities.add(Velocity.fromAngleAndSpeed(40, 4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blockHeight = 15;
        int blockWidth = 40;

        //counter for the colors
        int counter = 0;
        //creating the blocks.
        for (int i = 20; i < WIDTH - 40; i += 40) {
            for (int j = 70; j < HEIGHT - 250; j += 15) {
                Block block = new Block(new Rectangle(new Point((double) i, (double) j), blockWidth, blockHeight));
                //switch case for the block colors.
                switch (counter) {
                    case 0:
                        block.setColors(Color.GRAY);
                        break;
                    case 1:
                        block.setColors(Color.RED);
                        break;
                    case 2:
                        block.setColors(Color.YELLOW);
                        break;
                    case 3:
                        block.setColors(Color.GREEN);
                        break;
                    case 4:
                        block.setColors(Color.WHITE);
                        break;
                    case 5:
                        block.setColors(Color.PINK);
                        break;
                    default:
                        block.setColors(Color.CYAN);
                        break;
                }
                blocks.add(block);
                counter++;
            }
            //reset counter.
            counter = 0;
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
        Ball ball1 = new Ball((int) (startx + space), (int) starty - 20, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 4.5 * space), (int) starty - 45, 5, Color.WHITE);
        balls.add(ball1);
        ball1 = new Ball((int) (startx + 8 * space), (int) starty - 20, 5, Color.WHITE);
        balls.add(ball1);
        return balls;
    }
}