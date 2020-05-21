package oop.ass7game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener listener;
    private Rectangle rect;

    /**
     * @param listener .
     * @param rect     .
     */
    public ScoreIndicator(ScoreTrackingListener listener, Rectangle rect) {
        this.listener = listener;
        this.rect = rect;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //setting color.
        d.setColor(Color.WHITE);
        //filling rectangle.
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) (rect.getWidth()),
                (int) (rect.getHeight()));
        //change to black.
        d.setColor(Color.BLACK);
        //drawing edges.
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) (rect.getWidth()),
                (int) (rect.getHeight()));
        //drawing the number (or x) on the block.
        d.setColor(Color.BLACK);
        Point temp = rect.getBottomLine().middle();
        d.drawText((int) temp.getX() - 15, (int) temp.getY() - 2
                , "Score: " + listener.getCurrentScore().getValue(), 18);
    }

    /**
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param num .
     */
    public void addScore(int num) {
        listener.getCurrentScore().increase(num);
    }

    @Override
    public void timePassed() {
    }

    /**
     * @param list .
     */
    public void setListener(ScoreTrackingListener list) {
        this.listener = list;
    }
}
