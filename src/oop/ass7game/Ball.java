package oop.ass7game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball class.
 */
public class Ball implements Sprite {

    private static final int WIDTH = GameFlow.WIDTH;
    private static final int HEIGHT = GameFlow.HEIGHT + 20;

    private int radius;
    private Point point;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironmentnv;

    /**
     * constructor with point.
     *
     * @param center - middle point.
     * @param r      - radius.
     * @param color  - color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.point = center;
        this.color = color;
    }

    /**
     * constructor with point coordinates.
     *
     * @param x     - x for the point.
     * @param y     - y for the point.
     * @param r     - radius.
     * @param color - color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.radius = r;
        this.point = new Point(x, y);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @return - x.
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * @return - y.
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * @return - radius.
     */
    public int getSize() {
        return radius;
    }

    /**
     * @param gameEnvironmentn - sets the game env.
     */
    public void setGameEnvironmentnv(GameEnvironment gameEnvironmentn) {
        this.gameEnvironmentnv = gameEnvironmentn;
    }

    /**
     * @return - ball color.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) point.getX(), (int) point.getY(), radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) point.getX(), (int) point.getY(), radius);
    }

    /**
     * setVelocity.
     *
     * @param dx - dx.
     * @param dy - dy.
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * @return velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * @param v - velocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * moves the ball one step at a time.
     *
     * @param width     - width of my space.
     * @param height    - height of my space.
     * @param zeroPoint starting point of my allowed space.
     */
    public void moveOneStep2(int width, int height, Point zeroPoint) {
        int zeroX = (int) zeroPoint.getX();
        int zeroY = (int) zeroPoint.getY();

        //checking if we hit the side walls.
        if (point.getX() + radius >= width || point.getX() - radius <= zeroX) {
            setVelocity(-1 * velocity.getDx(), velocity.getDy());
        }

        //checking if we hit the horizontal walls.
        if (point.getY() + radius >= height || point.getY() - radius <= zeroY) {
            setVelocity(velocity.getDx(), -1 * velocity.getDy());
        }
        //appling the velocity.
        this.point = this.getVelocity().applyToPoint1(this.point, width, height, radius);
    }

    /**
     * special case just for the grey surface on the last mission.
     *
     * @param width             - width of my space.
     * @param height            - height of my space.
     * @param zeroPoint         starting point of my allowed space.
     * @param otherRecZeroPoint - the zero point of the yellow surface.
     */
    public void moveOneStepSpecialCase(int width, int height, Point zeroPoint, Point otherRecZeroPoint) {
        int zeroX = (int) zeroPoint.getX();
        int zeroY = (int) zeroPoint.getY();

        //checking if we hit the yellow side wall.
        if (point.getX() + radius >= otherRecZeroPoint.getX() && point.getY() >= otherRecZeroPoint.getY()) {
            setVelocity(-1 * velocity.getDx(), velocity.getDy());
        }
        if (point.getX() >= otherRecZeroPoint.getX() && point.getY() + radius >= otherRecZeroPoint.getY()) {
            setVelocity(velocity.getDx(), -1 * velocity.getDy());
        }
        //checking if we hit the side walls.
        if (point.getX() + radius >= width || point.getX() - radius <= zeroX) {
            setVelocity(-1 * velocity.getDx(), velocity.getDy());
        } else if ((int) point.distance(otherRecZeroPoint) <= radius) {
            //special case for the corner.
            setVelocity(-1 * velocity.getDx(), -1 * velocity.getDy());
        }
        if (point.getY() + radius >= height || point.getY() - radius <= zeroY) {
            //checking if we hit the horizontal walls.
            setVelocity(velocity.getDx(), -1 * velocity.getDy());
        } else if ((int) point.distance(otherRecZeroPoint) + 1 <= radius) {
            //special case for the corner.
            if (!(velocity.getDy() < 0.0 && velocity.getDx() < 0.0)) {
                setVelocity(-1 * velocity.getDx(), -1 * velocity.getDy());
            }
        }
        //appling the velocity.
        this.point = this.getVelocity().applyToPoint1(this.point, width, height, radius);
    }

    /**
     * moves the ball one step at a time.
     */
    public void moveOneStep() {
        //creating trajectory
        Line trajectory = new Line(point, velocity.applyToPoint(point));
        //checking if the ball is about to go out of bounds and fixing it
        if (point.getX() < 0) {
            point.setX(point.getX() + 30);
            if (velocity.getDx() < 0) {
                velocity.setDx(velocity.getDx() * -1);
            }
        } else if (point.getX() > WIDTH) {
            point.setX(point.getX() - 30);
            if (velocity.getDx() > 0) {
                velocity.setDx(velocity.getDx() * -1);
            }
        } else if (point.getY() < 0) {
            point.setY(point.getY() + 30);
            if (velocity.getDy() < 0) {
                velocity.setDy(velocity.getDy() * -1);
            }
        } else if (point.getY() > HEIGHT) {
            point.setY(point.getY() - 30);
            if (velocity.getDy() > 0) {
                velocity.setDy(velocity.getDy() * -1);
            }
        } else if (gameEnvironmentnv.getClosestCollision(trajectory) != null) {
            //if its not out we will modify velocity with the hit method
            point = gameEnvironmentnv.getClosestCollision(trajectory).collisionPoint();
            velocity = gameEnvironmentnv.getClosestCollision(trajectory).collisionObject()
                    .hit(this, gameEnvironmentnv.getClosestCollision(trajectory).collisionPoint(), velocity);
        }
        point = velocity.applyToPoint(point);


    }

    /**
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
