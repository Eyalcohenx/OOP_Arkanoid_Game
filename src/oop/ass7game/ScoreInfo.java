package oop.ass7game;

import java.io.Serializable;

/**
 * class ScoreInfo implements Serializable.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * @param name  .
     * @param score .
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return name;
     */
    public String getName() {
        return name;
    }

    /**
     * @return score.
     */
    public int getScore() {
        return score;
    }

}