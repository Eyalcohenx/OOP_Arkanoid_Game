package oop.ass7game;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * @param c - add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * @return collidable.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the line we need to check if we colide with.
     * @return - collision info.
     */
    //
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesC = new ArrayList<>(collidables);
        CollisionInfo info = null;
        double min = Double.MAX_VALUE;
        for (Collidable c : collidablesC) {
            //checking if null...
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                //if not null we check if its smaller then min.
                if (trajectory.start().distance(trajectory.closestIntersectionToStartOfLine(
                        c.getCollisionRectangle())) < min) {
                    //if smaller then min then its the new min.
                    min = trajectory.start().distance(trajectory.closestIntersectionToStartOfLine(
                            c.getCollisionRectangle()));
                    //the info will become the new min.
                    info = new CollisionInfo(c, trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
                }
            }
        }
        return info;
    }

    /**
     * @param c - collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}