package oop.ass7game;

/**
 * simple Counter class.
 */
public class Counter {
    private int count;

    /**
     * empty constructor initialize to 0.
     */
    public Counter() {
        count = 0;
    }

    /**
     * @param counT - number to initialize to.
     */
    public Counter(int counT) {
        count = counT;
    }

    /**
     * @param number - add number to current count.
     */
    void increase(int number) {
        count += number;
    }

    /**
     * @param number - subtract number from current count.
     */
    void decrease(int number) {
        count -= number;
    }

    /**
     * @return - current count.
     */
    public int getValue() {
        return count;
    }
}