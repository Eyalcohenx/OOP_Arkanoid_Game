package oop.ass2;

import biuoop.DrawSurface;
import biuoop.GUI;
import oop.ass7game.Line;
import oop.ass7game.Point;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

/**
 * AbstractArtDrawing class.
 */
public class AbstractArtDrawing {

    /**
     * activating the drawRandomLines function.
     *
     * @param args - empty.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }

    /**
     * drawing lines and indicating where they meet with red dots,
     * and where the middle point of each one with blue points.
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator.
        // Create a window with the title "Random Lines Example".
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 300);

        //creating new surface.
        DrawSurface d = gui.getDrawSurface();
        //vector of lines.
        Vector<Line> lines = new Vector<Line>(10);

        for (int i = 0; i < 10; ++i) {
            //creating random line.
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20
            d.setColor(Color.BLACK);
            Line newL = new Line(x1, y1, x2, y2);
            lines.add(newL);

            //drawing the line.
            d.drawLine(x1, y1, x2, y2);

            //drawing the middle point.
            d.setColor(Color.BLUE);
            d.fillCircle((int) newL.middle().getX(), (int) newL.middle().getY(), 3);
        }

        // finding the meeting point of and two lines in the vector and drawing
        // the red dot indicating the meeting point.
        for (Line line : lines) {
            for (Line otherLine : lines) {
                if (line.isIntersecting(otherLine)) {
                    Point intercection = line.intersectionWith(otherLine);
                    d.setColor(Color.RED);
                    d.fillCircle((int) intercection.getX(), (int) intercection.getY(), 3);
                }
            }
        }


        gui.show(d);
    }

}