package oop.ass7game;

import biuoop.GUI;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * class GameFlow.
 */
public class GameFlow {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int NORM_VALUE = 70;

    private AnimationRunner runner;
    private GUI gui;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private Counter numberOfLives;
    private LivesIndicator livesIndicator;
    private File scoreFile;
    private HighScoresTable table;
    private MenuAnimation menu;

    /**
     * @param runner .
     * @param gui    .
     */
    public GameFlow(AnimationRunner runner, GUI gui) {
        this.runner = runner;
        this.gui = gui;
        scoreTrackingListener = new ScoreTrackingListener();
        scoreIndicator = new ScoreIndicator(scoreTrackingListener,
                new Rectangle(new Point((double) 0, (double) 0), WIDTH, 20));
        numberOfLives = new Counter(7);
        livesIndicator = new LivesIndicator(numberOfLives, new Point(40, 20));
        scoreFile = new File("Ass7Score.ser");
        table = new HighScoresTable(14);
        try {
            if (scoreFile.createNewFile()) {
                table.save(scoreFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            table.load(scoreFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu = new MenuAnimation(gui.getKeyboardSensor(), runner);
    }

    /**
     * @param reader .
     */
    public void runLevels(LineNumberReader reader) {

        GameFlow thisOne = this;
        LevelSetReader lsr = new LevelSetReader();
        Menu<Void> subMenu = lsr.createMenueSet(reader, this);

        MenuTask menuTask = new MenuTask(subMenu, runner);


        Task exitTask = new Task() {
            @Override
            public Object run() {
                //exiting game;
                try {
                    thisOne.getTable().save(thisOne.getScoreFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                thisOne.getGui().close();
                System.exit(0);
                return null;
            }
        };

        menu.addSelection("q", "Quit", exitTask);
        menu.addSelection("s", "Start Game", menuTask);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(runner,
                new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space", new HighScoresAnimation(table))));

        while (true) {
            runner.run(menu);
            // wait for user selection
            Task task = (Task) menu.getStatus();
            task.run();
        }
    }

    /**
     * @return .
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * @return .
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @return .
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    /**
     * @return .
     */
    public ScoreIndicator getScoreIndicator() {
        return scoreIndicator;
    }

    /**
     * @return .
     */
    public Counter getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * @return .
     */
    public LivesIndicator getLivesIndicator() {
        return livesIndicator;
    }

    /**
     * @return .
     */
    public HighScoresTable getTable() {
        return table;
    }

    /**
     * @return .
     */
    public File getScoreFile() {
        return scoreFile;
    }


    /**
     * zero life and score.
     */
    public void zeroScoreAndLives() {
        numberOfLives = new Counter(7);
        scoreTrackingListener = new ScoreTrackingListener();
        scoreIndicator.setListener(scoreTrackingListener);
    }
}
