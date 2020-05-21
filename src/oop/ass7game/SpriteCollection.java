package oop.ass7game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection Class.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * @param s to be added.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesC = new ArrayList<Sprite>(sprites);
        for (Sprite s : spritesC) {
            s.timePassed();
        }
    }

    /**
     * @param d - call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesC = new ArrayList<Sprite>(sprites);
        for (Sprite s : spritesC) {
            s.drawOn(d);
        }
    }

    /**
     * @param tbRemoved - removing object from list.
     */
    public void remove(Sprite tbRemoved) {
        sprites.remove(tbRemoved);
    }
}