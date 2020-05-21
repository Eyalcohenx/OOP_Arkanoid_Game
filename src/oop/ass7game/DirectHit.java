package oop.ass7game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * class DirectHit implements LevelInformation.
 */
public class DirectHit implements LevelInformation {

    private static Background background = new Background("background_images/bullsEye.PNG");

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(0, -5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block singleBlock = new Block(new Rectangle(new Point(WIDTH / 2 + 10, HEIGHT / 4 + 15),
                20, 20));
        singleBlock.setColors(Color.RED);
        blocks.add(singleBlock);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<Ball>();
        //balls.
        Ball ball1 = new Ball(WIDTH / 2 + 20, 355, 5, Color.WHITE);
        balls.add(ball1);
        return balls;
    }
}
