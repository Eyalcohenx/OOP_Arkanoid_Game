package oop.ass7game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * class Green3 implements LevelInformation.
 */
public class Green3 implements LevelInformation {
    private static Background background = new Background("greenTower.PNG");
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(-2, -4));
        velocities.add(new Velocity(-4, -2));
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
        return "Green 3";
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
        for (int i = 100; i < WIDTH - 40; i += 40) {
            for (int j = 70; j < HEIGHT - 250; j += 15) {
                if (i > j * 2.5 - 50) {
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
                            block.setColors(Color.BLUE);
                            break;
                        case 4:
                            block.setColors(Color.WHITE);
                            break;
                        case 5:
                            block.setColors(Color.GREEN);
                            break;
                        default:
                            block.setColors(Color.CYAN);
                            break;
                    }
                    blocks.add(block);
                }
                counter++;
            }
            //reset counter.
            counter = 0;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 51;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<Ball>();
        //balls.
        Ball ball1 = new Ball(WIDTH / 2 + 100, 300, 5, Color.WHITE);
        balls.add(ball1);
        Ball ball2 = new Ball(WIDTH / 2 - 100, 300, 5, Color.WHITE);
        balls.add(ball2);
        return balls;
    }
}
