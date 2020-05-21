package oop.ass7game;

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface,
 *  and register themselves with a HitNotifier object using its addHitListener method.
 */
public interface HitListener {
    /**
     * @param beingHit .
     * @param hitter .
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
