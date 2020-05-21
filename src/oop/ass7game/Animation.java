package oop.ass7game;

import biuoop.DrawSurface;

/**
 * interface oop.ass7game.Animation.
 */
public interface Animation {
    /**
     * @param d .
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return .
     */
    boolean shouldStop();
}