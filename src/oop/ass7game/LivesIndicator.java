package oop.ass7game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LivesIndicator.
 */
public class LivesIndicator implements Sprite {
    private Counter numberOfLives;
    private Point locationOnScreen;

    /**
     * @param numberOfLives    .
     * @param locationOnScreen .
     */
    public LivesIndicator(Counter numberOfLives, Point locationOnScreen) {
        this.numberOfLives = numberOfLives;
        this.locationOnScreen = locationOnScreen;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //drawing the number (or x) on the block.
        d.setColor(Color.RED);
        Point temp = locationOnScreen;
        d.drawText((int) temp.getX() - 15,
                (int) temp.getY() - 2, "Lives: " + numberOfLives.getValue(), 18);
    }

    /**
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    @Override
    public void timePassed() {
    }
}
