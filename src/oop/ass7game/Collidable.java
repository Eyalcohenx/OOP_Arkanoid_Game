package oop.ass7game;

/**
 * Collidable interface.
 */
public interface Collidable {

    /**
     * @return - Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  .
     * @param currentVelocity .
     * @param hitter .
     * @return - The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @param ballMid .
     * @return if the ball mid point is inside the collidable.
     */
}