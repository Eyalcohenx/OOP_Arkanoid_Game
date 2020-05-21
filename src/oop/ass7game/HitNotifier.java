package oop.ass7game;

/**
 * The HitNotifier interface indicate that objects
 * that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * @param hl - Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * @param hl - Remove hl from the list of listeners to hit events.
     */
    void removeHitListener(HitListener hl);
}