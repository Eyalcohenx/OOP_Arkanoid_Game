package oop.ass7game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class NameIndicator implements Sprite.
 */
public class NameIndicator implements Sprite {
    private Point locationOnScreen;
    private String levelName;

    /**
     * @param locationOnScreen .
     * @param ln               .
     */
    public NameIndicator(String ln, Point locationOnScreen) {
        this.locationOnScreen = locationOnScreen;
        levelName = ln;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //drawing the number (or x) on the block.
        d.setColor(Color.black);
        Point temp = locationOnScreen;
        d.drawText((int) temp.getX() - 15,
                (int) temp.getY() - 2, "Level Name: " + levelName, 13);
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
