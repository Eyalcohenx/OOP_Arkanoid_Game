package oop.ass7game;

/**
 * Collision info class.
 */
public class CollisionInfo {

    private Collidable col;
    private Point colPoint;

    /**
     * @param col      .
     * @param colPoint .
     */
    public CollisionInfo(Collidable col, Point colPoint) {
        this.col = col;
        this.colPoint = colPoint;
    }

    /**
     * @return - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return colPoint;
    }

    /**
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return col;
    }
}