package oop.ass7game;

import biuoop.DrawSurface;

import java.awt.Color;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * // The CountdownAnimation will display the given gameScreen,
 * // for numOfSeconds seconds, and on top of them it will show
 * // a countdown from countFrom back to 1, where each number will
 * // appear on the screen for (numOfSeconds / countFrom) secods, before
 * // it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numofseconds;
    private int countfrom;
    private SpriteCollection gamescreen;
    private int framesLeft;
    private int framesfornum;
    private int currentframesfornum;
    private int startsize;
    private double currentsize;

    /**
     * @param numOfSeconds .
     * @param countFrom    .
     * @param gameScreen   .
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        numofseconds = numOfSeconds;
        countfrom = countFrom;
        gamescreen = gameScreen;
        framesLeft = (int) numofseconds * 60;
        if (countfrom != 0) {
            framesfornum = framesLeft / (countfrom + 1);
        } else {
            framesfornum = framesLeft;
        }
        currentframesfornum = 0;
        startsize = 70;
        currentsize = startsize;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //in this function we are counting down from the number given in the time
        //we told to so in our case we count down from 3 to 0 so every count will have
        //half a second, plus we made the numbers grow each time.
        currentframesfornum++;
        currentsize += (1);
        gamescreen.drawAllOn(d);

        //checking if we finishd frames for the num and starts from 0 again
        if (currentframesfornum > framesfornum) {
            currentframesfornum = 0;
            countfrom--;
            currentsize = startsize;
        }
        //drawing the numbes on the screen
        d.setColor(Color.RED);
        if (countfrom != 0) {
            d.drawText(WIDTH / 2 - (int) currentsize + startsize / 2,
                    HEIGHT / 2 + (int) currentsize / 2, "" + countfrom + "...", (int) currentsize);
        } else {
            d.drawText(WIDTH / 2 - (int) currentsize,
                    HEIGHT / 2 + startsize / 2 + (int) (currentsize / 3), "GO!", (int) currentsize);
        }
        framesLeft--;
    }

    @Override
    public boolean shouldStop() {
        return framesLeft < 0;
    }
}