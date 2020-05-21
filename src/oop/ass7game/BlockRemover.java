package oop.ass7game;

/**
 * // a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * // of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter blocksLeft;

    /**
     * @param gameLevel       .
     * @param blocksLeft .
     */
    public BlockRemover(GameLevel gameLevel, Counter blocksLeft) {
        this.game = gameLevel;
        this.blocksLeft = blocksLeft;
    }

    /**
     * @param beingHit .
     * @param hitter   .
     *                 Blocks that are hit and reach 0 hit-points should be removed
     *                 from the game. Remember to remove this listener from the block
     *                 that is being removed from the game.
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() == 0) {
            beingHit.removeHitListener(this);
            game.removeSprite(beingHit);
            game.removeCollidable(beingHit);
            blocksLeft.decrease(1);
        }
    }
}