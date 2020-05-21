package oop.ass2;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import oop.ass7game.Ball;
import oop.ass7game.Point;
import oop.ass7game.Velocity;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

/**
 * MultipleFramesBouncingBallsAnimation class.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * method to check if randomally generated ball is out od bounds.
     *
     * @param ball      .
     * @param width     .
     * @param height    .
     * @param zeroPoint - zero point for the surface .
     * @return if its generated out of bounds or not, true if its out of bounds.
     */
    public static boolean outOfBounds(Ball ball, int width, int height, Point zeroPoint) {

        int radius = ball.getSize();
        if (ball.getX() + radius >= width - 5 || ball.getX() - radius <= zeroPoint.getX() + 5) {
            return true;
        } else if (ball.getY() + radius >= height - 5 || ball.getY() - radius <= zeroPoint.getY() + 5) {
            return true;
        }
        return false;
    }

    /**
     * creating balls at random places on the map with random colors.
     *
     * @param args raduise of the balls.
     */
    public static void main(String[] args) {
        //initializing variables.
        GUI gui = new GUI("title", 600, 600);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random(); // create a random-number generator
        int[] raduises = new int[args.length];
        Ball tempBall;

        //balls for grey rectangle.
        Vector<Ball> balls1 = new Vector<Ball>();
        Point greyZeroPoint = new Point(50, 50);
        //balls for yello rectangle
        Vector<Ball> balls2 = new Vector<Ball>();
        Point yelloZeroPoint = new Point(450, 450);

        //converting even placed strings (raduises) to ints
        for (int i = 0; i < args.length; i += 2) {
            raduises[i] = Integer.parseInt(args[i]) * 3;
        }

        // creating first half of the balls for grey surface.
        for (int radius : raduises) {
            do {
                int x = rand.nextInt(150) + 450; // get integer in range 450-600
                int y = rand.nextInt(150) + 450; // get integer in range 450-600

                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                tempBall = new Ball(x, y, radius, new Color(r, g, b));

            }
            while (outOfBounds(tempBall, gui.getDrawSurface().getWidth(),
                    gui.getDrawSurface().getHeight(), new Point(450, 450)));
            balls2.add(tempBall);
        }

        //converting odd placed strings (raduises) to ints
        for (int i = 1; i < args.length; i += 2) {
            raduises[i] = Integer.parseInt(args[i]) * 3;
        }

        // creating first half of the balls for yellow surface.
        for (int radius : raduises) {
            do {
                int x = rand.nextInt(450) + 50; // get integer in range 50-500
                int y = rand.nextInt(450) + 50; // get integer in range 50-500
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                tempBall = new Ball(x, y, radius, new Color(r, g, b));
            } while (outOfBounds(tempBall, 450, 450, new Point(50, 50)));
            balls1.add(tempBall);
        }

        // generating random valocity for the ball in yellow with speed according to size.
        for (Ball ball : balls2) {
            int angle = rand.nextInt(360) + 1;
            int speed;
            if (ball.getSize() > 50) {
                speed = 1;
            } else {
                speed = (50 - ball.getSize()) / 8;
            }
            ball.setVelocity(Velocity.fromAngleAndSpeed((double) angle, (double) speed));
        }

        // generating random valocity for the ball in grey with speed according to size.
        for (Ball ball : balls1) {
            int angle = rand.nextInt(360) + 1;
            int speed;
            if (ball.getSize() > 50) {
                speed = 1;
            } else {
                speed = (50 - ball.getSize()) / 5;
            }
            ball.setVelocity(Velocity.fromAngleAndSpeed((double) angle, (double) speed));
        }

        //here it all comes together, drawing the balls and surface and running the gui.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.fillRectangle(50, 50, 500, 500);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 600, 600);

            //handling the balls in yellow surface.
            for (Ball ball : balls2) {
                ball.moveOneStep2(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight(), yelloZeroPoint);
                ball.drawOn(d);
            }

            //handling the balls in grey surface.
            for (Ball ball : balls1) {
                ball.moveOneStepSpecialCase(550, 550, greyZeroPoint, yelloZeroPoint);
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }

    }
}
