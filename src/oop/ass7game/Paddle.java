package oop.ass7game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.NORM_VALUE;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * Paddle Class.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int paddlespeed;
    private int width;

    /**
     * Constructor.
     *
     * @param keyboard    .
     * @param paddleSpeed .
     * @param width1      .
     */
    public Paddle(KeyboardSensor keyboard, int width1, int paddleSpeed) {
        this.keyboard = keyboard;
        rect = new Rectangle(new Point(WIDTH / 2 - width1 / 2, HEIGHT - 15), width1, 15);
        width = width1;
        if (paddleSpeed > 10) {
            paddleSpeed = paddleSpeed / NORM_VALUE;
        }
        paddlespeed = paddleSpeed;
    }

    /**
     * puts the paddale bac to the middle.
     */
    public void backToMiddle() {
        rect = new Rectangle(new Point(WIDTH / 2 - width / 2, HEIGHT - 15), width, 15);
    }

    /**
     * moving 5 pixels to the left.
     */
    public void moveLeft() {
        int movement = paddlespeed * -1;
        if (rect.getUpperLeft().getX() + movement > 20 && rect.getUpperLeft().getX() + movement + width < WIDTH - 20) {
            rect.moveUpperLeftX(movement);
        }
    }

    /**
     * moving 5 pixels to the right.
     */
    public void moveRight() {
        int movement = paddlespeed;
        if (rect.getUpperLeft().getX() + movement > 20 && rect.getUpperLeft().getX() + movement + width < WIDTH - 20) {
            rect.moveUpperLeftX(movement);
        }
    }

    /**
     * if its triggered we check if one of the keys was pressed and moving accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @param d .
     */
    public void drawOn(DrawSurface d) {
        //changing color to orange.
        d.setColor(Color.ORANGE);
        //filling the rectangle.
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) (getCollisionRectangle().getWidth()),
                (int) (getCollisionRectangle().getHeight()));
        //changing to black for the edge and coloring the edge.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) (getCollisionRectangle().getWidth()),
                (int) (getCollisionRectangle().getHeight()));
    }

    /**
     * @return the rect.
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * @param collisionPoint  .
     * @param currentVelocity .
     * @param hitter          .
     * @return new velocity accordingly to the information we got.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //creating variables.
        double colPointX = collisionPoint.getX();
        double myStartX = rect.getUpperLeft().getX();
        double difference = colPointX - myStartX;

        //new velocity to be reurned.
        Velocity newVel = currentVelocity;

        //checking if we are on the top line.
        if (rect.getTopLine().onTheLine(collisionPoint)) {
            Point temp = new Point(0, 0);
            double speed = temp.distance(new Point(currentVelocity.getDx(), currentVelocity.getDy()));
            //moving accordingly to the where we hit with checking what area we hit.
            if (difference <= width / 5 && difference > 0) {
                newVel = Velocity.fromAngleAndSpeed(150, speed);
            } else if (difference <= width * 2 / 5 && difference > width / 5) {
                newVel = Velocity.fromAngleAndSpeed(120, speed);
            } else if (difference <= width * 3 / 5 && difference > width * 2 / 5) {
                newVel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            } else if (difference <= width * 4 / 5 && difference > width * 3 / 5) {
                newVel = Velocity.fromAngleAndSpeed(60, speed);
            } else if (difference <= width * 5 / 5 && difference > width * 4 / 5) {
                newVel = Velocity.fromAngleAndSpeed(30, speed);
            }
        }
        //if we hit on one of the siedes of the paddle we change the x direction.
        if (rect.getLeftLine().onTheLine(collisionPoint) || rect.getRightLine().onTheLine(collisionPoint)) {
            newVel.setDx(newVel.getDx() * -1);
        }
        return newVel;
    }

    /**
     * @param g .
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        //add myself to the game.
        g.addSprite(this);
        g.addCollidable(this);
    }
}
