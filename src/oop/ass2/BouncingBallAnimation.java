package oop.ass2;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import oop.ass7game.Ball;

/**
 * BouncingBallAnimation - class.
 */
public class BouncingBallAnimation {
    /**
     * creating a ball with black color at a random place in the map and random velocity.
     *
     * @param args - none.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
