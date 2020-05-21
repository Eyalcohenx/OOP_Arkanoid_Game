package oop.ass7game;

/**
 * PrintingHitListener - test.
 */
public class PrintingHitListener implements HitListener {
    /**
     * @param beingHit .
     * @param hitter   .
     *                 This method is called whenever the beingHit object is hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}