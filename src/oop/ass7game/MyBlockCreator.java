package oop.ass7game;

/**
 * class MyBlockCreator implements BlockCreator.
 */
public class MyBlockCreator implements BlockCreator {
    private Block block;

    /**
     * @param block .
     */
    public MyBlockCreator(Block block) {
        this.block = block;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block b = new Block(block);
        b.setRectPoint(new Point(xpos, ypos));
        return b;
    }
}
