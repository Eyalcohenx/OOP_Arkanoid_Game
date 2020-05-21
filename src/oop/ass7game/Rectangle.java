package oop.ass7game;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle Class.
 */
class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperL .
     * @param wid    .
     * @param hei    .
     */
    public Rectangle(Point upperL, double wid, double hei) {
        upperLeft = upperL;
        width = wid;
        height = hei;
    }

    /**
     * empty constructor.
     */
    public Rectangle() {
    }

    /**
     * @param other - copy constructor.
     */
    public Rectangle(Rectangle other) {
        this.upperLeft = other.upperLeft;
        this.width = other.width;
        this.height = other.height;
    }

    /**
     * method to check we dont get out of the screen.
     *
     * @param movement .
     */
    public void moveUpperLeftX(int movement) {
        upperLeft.setX(upperLeft.getX() + movement);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line .
     * @return - inter
     */
    public java.util.List intersectionPoints(Line line) {

        //initializing lines that define the rectangle.
        Line top = getTopLine();
        Line bottom = getBottomLine();
        Line left = getLeftLine();
        Line right = getRightLine();

        List<Point> intercections = new ArrayList<Point>();

        //checking for intercections and add to the list.
        if (line.isIntersecting(top)) {
            intercections.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(bottom)) {
            intercections.add(line.intersectionWith(bottom));
        }
        if (line.isIntersecting(left)) {
            intercections.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            intercections.add(line.intersectionWith(right));
        }

        return intercections;
    }

    /**
     * @return left line.
     */
    public Line getLeftLine() {

        return new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * @return right line.
     */
    public Line getRightLine() {
        return new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * @return top line.
     */
    public Line getTopLine() {
        return new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * @return bottom line.
     */
    public Line getBottomLine() {
        return new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * @return - Return the width and height of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width1 .
     */
    public void setWidth(double width1) {
        this.width = width1;
    }

    /**
     * @return height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height1 .
     */
    public void setHeight(double height1) {
        this.height = height1;
    }

    /**
     * @return - Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @param upperLeft1 .
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }
}
