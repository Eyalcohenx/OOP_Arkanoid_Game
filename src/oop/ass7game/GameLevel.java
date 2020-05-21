package oop.ass7game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * GameLevel class.
 */
public class GameLevel implements Animation {

    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private Counter numberOfLives;
    private LivesIndicator livesIndicator;
    private boolean running;
    private Paddle paddle;

    /**
     * @param levelInfo .
     * @param gf        .
     */
    public GameLevel(LevelInformation levelInfo, GameFlow gf) {
        levelInformation = levelInfo;
        runner = gf.getRunner();
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockRemover = new BlockRemover(this, blocksCounter);
        gui = gf.getGui();
        keyboard = gui.getKeyboardSensor();
        blocksCounter = new Counter();
        ballsCounter = new Counter();
        blockRemover = new BlockRemover(this, blocksCounter);
        ballRemover = new BallRemover(this, ballsCounter);
        scoreTrackingListener = gf.getScoreTrackingListener();
        scoreIndicator = gf.getScoreIndicator();
        numberOfLives = gf.getNumberOfLives();
        livesIndicator = gf.getLivesIndicator();
        paddle = new Paddle(keyboard, levelInfo.paddleWidth(), levelInfo.paddleSpeed());
    }

    /**
     * @return .
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    /**
     * @return opposite of running
     */
    public boolean shouldStop() {
        if (!(blocksCounter.getValue() != 0 && ballsCounter.getValue() != 0)) {
            this.running = false;
        }
        return !this.running;
    }

    /**
     * @param c - Collidable to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s - Sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        sprites.addSprite(levelInformation.getBackground());
        //initializing block height.

        //4 blocks for the sides.
        Block block1 = new Block(new Rectangle(new Point((double) 0, (double) 20), WIDTH, 20));
        block1.setCounter(Integer.MIN_VALUE);
        block1.setColors(Color.GRAY);
        block1.addToGame(this);

        Block block2 = new Block(new Rectangle(new Point((double) 0, (double) 0), 20, HEIGHT));
        block2.setCounter(Integer.MIN_VALUE);
        block2.setColors(Color.GRAY);
        block2.addToGame(this);

        Block block3 = new Block(new Rectangle(new Point((double) WIDTH - 20, (double) 0), 20, HEIGHT));
        block3.setCounter(Integer.MIN_VALUE);
        block3.setColors(Color.GRAY);
        block3.addToGame(this);

        Block deathBlock = new Block(new Rectangle(new Point((double) -100, (double) HEIGHT + 20), WIDTH + 200, 20));
        deathBlock.setCounter(Integer.MIN_VALUE);
        deathBlock.setColors(Color.GRAY);
        deathBlock.addHitListener(ballRemover); /* IMPORTANT : ballRemover for death Block !! */
        deathBlock.addToGame(this);

        //new paddle.
        paddle.addToGame(this);

        scoreIndicator.addToGame(this);
        livesIndicator.addToGame(this);
        new NameIndicator(levelInformation.levelName(), new Point(WIDTH * 3 / 4, 17)).addToGame(this);


        for (Block b : levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            blocksCounter.increase(1);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {

        this.createBallsOnTopOfPaddle();

        this.runner.run(new CountdownAnimation(2, 3, sprites)); //countdown before turn starts.
        this.running = true;

        runner.run(this);

        if (blocksCounter.getValue() == 0) {
            scoreIndicator.addScore(100);
        } else {
            numberOfLives.decrease(1);
        }

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }
    }

    /**
     * @param c - collidable to be removed.
     */
    void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * call playOneTurn().
     * Update the number of lives.
     * If there are more lives left, call playOneTurn again, else exit (return).
     */
    public void run() {
        while (numberOfLives.getValue() > 0) {
            playOneTurn();
        }
        gui.close();
    }

    /**
     * @param s - sprite to be removed.
     */
    void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * exactly as its name.
     */
    void createBallsOnTopOfPaddle() {

        //returns the paddale to the middle.
        paddle.backToMiddle();

        List<Velocity> velocities = levelInformation.initialBallVelocities();
        List<Ball> balls = levelInformation.balls();
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setVelocity(velocities.get(i));
            balls.get(i).setGameEnvironmentnv(environment);
            balls.get(i).addToGame(this);
        }

        //adding 2 to the balls counter
        ballsCounter.increase(levelInformation.numberOfBalls());

    }

}