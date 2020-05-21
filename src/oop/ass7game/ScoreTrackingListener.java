package oop.ass7game;

/**
 * ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     */
    public ScoreTrackingListener() {
        currentScore = new Counter(); //counter = 0
    }

    /**
     * @param scoreCounter .
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() == 0) {
            currentScore.increase(10);
        }
        if (beingHit.getCounter() != Integer.MIN_VALUE) {
            currentScore.increase(5);
        }
    }


    /**
     * @return currentScore.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}