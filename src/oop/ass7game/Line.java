package oop.ass7game;

import java.util.List;

/**
 * Line class.
 */
public class Line {
    private Point start;
    private Point end;

    // constructors.

    /**
     * initialization by points.
     *
     * @param start - starting point.
     * @param end   - end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * initalization by coordinates.
     *
     * @param x1 - point 1 x.
     * @param y1 - point 1 y.
     * @param x2 - point 2 x.
     * @param y2 - point 2 y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculating det for the intersection algorithm.
     *
     * @param a - a.
     * @param b - b.
     * @param c - c.
     * @param d - d.
     * @return returns the det.
     */
    public static double det(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    /**
     * @param a   first num.
     * @param b   second num.
     * @param eps - epsilon
     * @return if the two numbers are almost equal (up to epsilon).
     */
    public static boolean almostEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

    /**
     * @return returns the length of the line using the distacne method.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return using basic math to cehck for the middle point and returns it.
     */
    public Point middle() {
        double y1 = start.getY();
        double x1 = start.getX();
        double y2 = end.getY();
        double x2 = end.getX();
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the starting point.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point.
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other - other Line.
     * @return - returns if they intercet usuing the intercetion point method.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * @param other - other line to check intersection with.
     * @return - the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {

        boolean returnNullFlag = false;
        //Line 1 start.
        double x1 = start.getX();
        double y1 = start.getY();

        //Line 1 end.
        double x2 = end.getX();
        double y2 = end.getY();

        //Line 2 start.
        double x3 = other.start().getX();
        double y3 = other.start().getY();

        //Line 2 end.
        double x4 = other.end().getX();
        double y4 = other.end().getY();

        //calculating dets and verticality.
        double detL1 = det(x1, y1, x2, y2);
        double detL2 = det(x3, y3, x4, y4);
        double x1mx2 = x1 - x2;
        double x3mx4 = x3 - x4;
        double y1my2 = y1 - y2;
        double y3my4 = y3 - y4;

        //getting dets for the equasions.
        double xnom = det(detL1, x1mx2, detL2, x3mx4);
        double ynom = det(detL1, y1my2, detL2, y3my4);
        double denom = det(x1mx2, y1my2, x3mx4, y3my4);

        //intersection point x and y.
        double ix = 0, iy = 0;

        //Lines don't seem to cross.
        if (denom == 0.0) {
            returnNullFlag = true;
        } else {
            try {
                ix = xnom / denom;
                iy = ynom / denom;

                //check if the x is in the range of one of the lines.
                if (!((onTheLine(new Point(ix, iy))) && (other.onTheLine(new Point(ix, iy))))) {
                    returnNullFlag = true;
                }
                //if we divide by zero it means they dont cross so we need to return null.
            } catch (ArithmeticException e) {
                returnNullFlag = true;
            }
        }

        //last check of the flag to check what to return.
        if (returnNullFlag) {
            return null;
        } else {
            return new Point(ix, iy);
        }
    }

    /**
     * @param other - other line to compare to.
     * @return - true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (start == other.start() && end == other.end());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect - rect.
     * @return - closest point.
     */
    //
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        @SuppressWarnings("unchecked")
        List<Point> intersections = rect.intersectionPoints(this);
        //System.out.println("intersections: " + intersections.size());
        double min = Double.MAX_VALUE;
        Point closest = null;
        for (Point p : intersections) {
            //checking if its the new min and if so we make it the min.
            if (start.distance(p) < min) {
                closest = p;
                min = start.distance(p);
            }
        }
        return closest;
    }

    /**
     * @param p - point.
     * @return if the point is on the line or not.
     */
    public boolean onTheLine(Point p) {
        return almostEqual(start.distance(p) + p.distance(end), start.distance(end), 0.2);
    }

}
