package oop.ass7game;

/**
 * interface BlockCreator.
 */
public interface BlockCreator {
    /**
     * @param xpos .
     * @param ypos .
     * @return .
     * Create a block at the specified location.
     */
    Block create(int xpos, int ypos);
}
