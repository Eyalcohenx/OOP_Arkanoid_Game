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
 * MultipleBouncingBallsAnimation class.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * method to check if randomally generated ball is out od bounds.
     *
     * @param ball   .
     * @param width  .
     * @param height .
     * @return if its generated out of bounds or not, true if its out of bounds.
     */
    public static boolean outOfBounds(Ball ball, int width, int height) {

        int radius = ball.getSize();
        if (ball.getX() + radius >= width - 5 || ball.getX() - radius <= 5) {
            return true;
        } else if (ball.getY() + radius >= height - 5 || ball.getY() - radius <= 5) {
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
        //creating the gui snd sleeper and randomer and the zero point.
        GUI gui = new GUI("title", 500, 500);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random(); // create a random-number generator
        Point zeroPoint = new Point(0, 0);
        //converting radius in string form to int.
        int[] raduises = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            raduises[i] = Integer.parseInt(args[i]) * 5;
        }

        //tempball to be used later.
        Ball tempBall = new Ball(0, 0, 0, Color.BLUE);
        Vector<Ball> balls = new Vector<Ball>(raduises.length);

        //generating random balls.
        for (int radius : raduises) {
            do {
                int x = rand.nextInt(500) + 1; // get integer in range 1-500
                int y = rand.nextInt(500) + 1; // get integer in range 1-500
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                tempBall = new Ball(x, y, radius, new Color(r, g, b));
            } while (outOfBounds(tempBall, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight()));
            balls.add(tempBall);
        }

        // generating random valocity for the ball with speed according to size.
        for (Ball ball : balls) {
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
            for (Ball ball : balls) {
                ball.moveOneStep2(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight(), zeroPoint);
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
