package oop.ass7game;

import static java.lang.Math.sqrt;

/**
 * Point class.
 */
public class Point {
    private double x;
    private double y;
    // constructor.

    /**
     * @param x - x.
     * @param y - y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - the other point.
     * @return the distance between the two points.
     */
    // distance -- return the distance of this point to the other point.
    public double distance(Point other) {
        //initializing variables for the algorithm.
        double y1 = y;
        double x1 = x;
        double y2 = other.getY();
        double x2 = other.getX();

        //simple algorithm to find distance.
        return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * @param other - other point to compare it to.
     * @return - returns true only if they match in both coordinates.
     */
    // equals -- return true is the points are equal, false otherwise.
    public boolean equals(Point other) {
        return (x == other.getX() && y == other.getY());
    }

    /**
     * @return - x.
     */
    // Return the x and y values of this point.
    public double getX() {
        return x;
    }

    /**
     * @param newX .
     */
    public void setX(double newX) {
        x = newX;
    }

    /**
     * @return - y.
     */
    public double getY() {
        return y;
    }

    /**
     * @param newY .
     */
    public void setY(double newY) {
        y = newY;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}