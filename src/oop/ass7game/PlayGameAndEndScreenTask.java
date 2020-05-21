package oop.ass7game;

import biuoop.DialogManager;


import java.util.List;

/**
 * class PlayGameAndEndScreenTask implements Task.
 */
public class PlayGameAndEndScreenTask implements Task {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * @param gameFlow .
     * @param levels   .
     */
    public PlayGameAndEndScreenTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameFlow = gameFlow;
        this.levels = levels;
    }

    @Override
    public Void run() {
        //we get the levels here and eun them
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gameFlow);

            level.initialize();
            while (level.getBlocksCounter().getValue() > 0 && gameFlow.getNumberOfLives().getValue() > 0) {
                level.playOneTurn();
            }

            if (gameFlow.getNumberOfLives().getValue() == 0) {
                break;
            }
        }

        boolean won = true;

        if (gameFlow.getNumberOfLives().getValue() == 0) {
            won = false;
        }


        //after we finished we show the end screen.
        gameFlow.getRunner().run(new KeyPressStoppableAnimation(gameFlow.getGui().getKeyboardSensor(),
                "space", new EndScreen(gameFlow.getScoreTrackingListener().getCurrentScore().getValue(), won)));
        DialogManager dialog = gameFlow.getGui().getDialogManager();

        String name = dialog.showQuestionDialog("Name", "What is your name?", "");

        gameFlow.getTable().add(new ScoreInfo(name,
                gameFlow.getScoreTrackingListener().getCurrentScore().getValue()));

        gameFlow.getRunner().run(new KeyPressStoppableAnimation(gameFlow.getGui().getKeyboardSensor(),
                "space", new HighScoresAnimation(gameFlow.getTable())));

        gameFlow.zeroScoreAndLives();
        return null;
    }
}
