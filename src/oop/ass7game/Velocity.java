package oop.ass7game;

import static oop.ass7game.GameFlow.NORM_VALUE;

/**
 * Velocity class.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Velocity constructor.
     *
     * @param dx .
     * @param dy .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param vel - copy constructor.
     */
    public Velocity(Velocity vel) {
        dx = vel.getDx();
        dy = vel.getDy();
    }

    /**
     * @param angle .
     * @param speed .
     * @return velocity it created after converting speed and angle to dx,dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (speed > 10) {
            speed = speed / NORM_VALUE;
        }
        double dx = 1 * speed * Math.cos(Math.toRadians(angle));
        double dy = -1 * speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @param dX .
     */
    public void setDx(double dX) {
        dx = dX;
    }

    /**
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param dY .
     */
    public void setDy(double dY) {
        dy = dY;
    }

    /**
     * @param p - point.
     * @return new Point(p.getX() + dx, p.getY() + dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * @param p      point.
     * @param width  .
     * @param height .
     * @param radius .
     * @return Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     * with a check for the borders to check we are not going out.
     */
    public Point applyToPoint1(Point p, int width, int height, int radius) {
        double newx, newy, dradius = (double) radius;
        //checking if we pass the vertical walls.
        if (dx < 0 && p.getX() + dx - dradius < 0.0) {
            newx = 0.0 + dradius;
        } else if (p.getX() + dx + dradius > width) {
            newx = width - dradius;
        } else {
            newx = p.getX() + dx;
        }
        //checking if we pass the horizontal walls.
        if (dy < 0 && p.getY() + dy - dradius < 0.0) {
            newy = 0.0 + dradius;
        } else if (p.getY() + dy + dradius > height) {
            newy = height - dradius;
        } else {
            newy = p.getY() + dy;
        }
        return new Point(newx, newy);
    }
}
