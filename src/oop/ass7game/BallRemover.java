package oop.ass7game;

/**
 * BallRemover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballsLeft;

    /**
     * @param game      .
     * @param ballsLeft .
     */
    public BallRemover(GameLevel game, Counter ballsLeft) {
        this.game = game;
        this.ballsLeft = ballsLeft;
    }

    /**
     * @param beingHit .
     * @param hitter   .
     *                 Blocks that are hit and reach 0 hit-points should be removed
     *                 from the game. Remember to remove this listener from the block
     *                 that is being removed from the game.
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        ballsLeft.decrease(1);
    }
}
