package oop.ass7game;

import biuoop.DrawSurface;

import java.util.List;

/**
 * class HighScoresAnimation implements Animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;

    /**
     * @param table .
     */
    public HighScoresAnimation(HighScoresTable table) {
        this.table = table;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int space = 30;
        List<ScoreInfo> s = table.getHighScores();
        d.drawText(d.getWidth() / 3, 50, "High Score:", 50);
        int i = 1;
        for (ScoreInfo si : s) {
            String name;
            if (si.getName().equals("")) {
                name = "Unknown";
            } else {
                name = si.getName();
            }
            d.drawText(d.getWidth() / 3, 55 + (space * (i + 1)),
                    i + ". " + name + ": " + si.getScore(), 30);
            i++;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder returned = new StringBuilder();
        int counter = 1;
        List<ScoreInfo> scores = table.getHighScores();
        for (ScoreInfo si : scores) {
            returned.append(counter).append(". ").append(si.getName()).append(": ").append(si.getScore()).append("\n");
        }
        return returned.toString();
    }
}
