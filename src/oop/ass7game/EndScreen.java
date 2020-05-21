package oop.ass7game;

import biuoop.DrawSurface;

/**
 * class EndScreen implements Animation.
 */
public class EndScreen implements Animation {
    private boolean won;
    private int score;

    /**
     * @param score1 - score.
     * @param w      - tells us if the player won or lost.
     */
    public EndScreen(int score1, boolean w) {
        won = w;
        score = score1;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // if we won we print you win else we print game over.
        if (won) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
