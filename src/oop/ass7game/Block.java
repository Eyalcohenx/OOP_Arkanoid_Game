package oop.ass7game;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;
    private boolean[] colorOrNot; //not means image
    private Image[] backgroundImages;
    private Color[] colors;
    private Color borderColor;
    private int counter;
    private Point lastCollisionPoint;

    /**
     * @param recta - constructor.
     */
    public Block(Rectangle recta) {
        rect = recta;
        hitListeners = new Vector<>();
    }

    /**
     * empty constructor.
     */
    public Block() {
        rect = new Rectangle();
    }

    /**
     * @param other - modified copy constructor.
     */
    public Block(Block other) {
        this.hitListeners = new Vector<>();
        this.rect = new Rectangle(other.rect);
        if (other.colorOrNot != null) {
            colorOrNot = new boolean[other.colorOrNot.length];
            System.arraycopy(other.colorOrNot, 0, colorOrNot, 0, colorOrNot.length);
        }
        if (other.backgroundImages != null) {
            backgroundImages = new Image[other.backgroundImages.length];
            System.arraycopy(other.backgroundImages, 0, backgroundImages, 0,
                    backgroundImages.length);
        }
        if (other.colors != null) {
            colors = new Color[other.colors.length];
            System.arraycopy(other.colors, 0, colors, 0, colors.length);
        }
        borderColor = other.borderColor;
        this.counter = other.counter;
    }

    /**
     * @return - counter.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param count .
     */
    public void setCounter(int count) {
        if (count == Integer.MIN_VALUE) {
            counter = count;
            colorOrNot = new boolean[2];
            colors = new Color[2];
            backgroundImages = new Image[2];
        }
    }

    /**
     * @param count .
     */
    public void setCounterForFactory(int count) {
        counter = count;
        colorOrNot = new boolean[counter + 1];
        colors = new Color[counter + 1];
        backgroundImages = new Image[counter + 1];
    }

    /**
     * @return - my rect.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * @param collisionPoint  .
     * @param currentVelocity .
     * @param hitter          .
     * @return new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //hit flag to check if the hit is valid.
        boolean hitFlag = false;

        //saving collision point for the getHitPoints method.
        lastCollisionPoint = collisionPoint;

        //initializing lines that difine the rectangle.
        Line top = rect.getTopLine();
        Line bottom = rect.getBottomLine();
        Line left = rect.getLeftLine();
        Line right = rect.getRightLine();

        //new vel witch will be returned.
        Velocity newVel = new Velocity(currentVelocity);

        //checking for all of the lines if the hit is on them and fixing the velocity accordingly
        if (left.onTheLine(collisionPoint)) {
            //hit on left
            if (newVel.getDx() > 0) {
                newVel.setDx(newVel.getDx() * -1);
                hitFlag = true;
            }
        }
        if (right.onTheLine(collisionPoint)) {
            //hit on right
            if (newVel.getDx() < 0) {
                newVel.setDx(newVel.getDx() * -1);
                hitFlag = true;
            }
        }
        if (top.onTheLine(collisionPoint)) {
            //hit on top
            if (newVel.getDy() > 0) {
                newVel.setDy(newVel.getDy() * -1);
                hitFlag = true;
            }
        }
        if (bottom.onTheLine(collisionPoint)) {
            //hit on bottom
            if (newVel.getDy() < 0) {
                newVel.setDy(newVel.getDy() * -1);
                hitFlag = true;
            }
        }

        if (counter != Integer.MIN_VALUE) {
            --counter;
        }

        this.notifyHit(hitter);

        return newVel;
    }

    /**
     * @param d .
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (counter != Integer.MIN_VALUE) {
            if (colorOrNot[counter]) {
                //setting color.
                d.setColor(colors[counter]);
                //filling rectangle.
                d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                        (int) getCollisionRectangle().getUpperLeft().getY(),
                        (int) (getCollisionRectangle().getWidth()),
                        (int) (getCollisionRectangle().getHeight()));
            } else {
                Image img = backgroundImages[counter];
                d.drawImage((int) getCollisionRectangle().getUpperLeft().getX(),
                        (int) getCollisionRectangle().getUpperLeft().getY(), img);
            }
            if (borderColor != null) {
                //change to black.
                d.setColor(borderColor);
                //drawing edges.
                d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                        (int) getCollisionRectangle().getUpperLeft().getY(),
                        (int) (getCollisionRectangle().getWidth()),
                        (int) (getCollisionRectangle().getHeight()));
            }
        } else {
            d.setColor(colors[0]);
            //filling rectangle.
            d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                    (int) getCollisionRectangle().getUpperLeft().getY(),
                    (int) (getCollisionRectangle().getWidth()),
                    (int) (getCollisionRectangle().getHeight()));
        }
        /*
        //drawing the number (or x) on the block.
        d.setColor(Color.WHITE);
        if (counter != Integer.MIN_VALUE) {
            Point temp = rect.getBottomLine().middle();
        }
        */
    }

    /**
     * @param g .
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * doing nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * removing the block from the gameLevel.
     *
     * @param gameLevel gameLevel to be removed from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * @param hitter .
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * @return the last collision point.
     */
    public Point getHitPoints() {
        if (lastCollisionPoint != null) {
            return lastCollisionPoint;
        } else {
            return null;
        }
    }

    /**
     * @return .
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * @param hitListeners1 .
     */
    public void setHitListeners(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }

    /**
     * @return .
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @param rect1 .
     */
    public void setRect(Rectangle rect1) {
        this.rect = rect1;
    }

    /**
     * @param colorOrNotForAll .
     */
    public void setColorOrNot(boolean colorOrNotForAll) {
        Arrays.fill(colorOrNot, colorOrNotForAll);
    }

    /**
     * @param backgroundImage .
     */
    public void setBackgroundImages(String backgroundImage) {
        Image img = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundImage);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.fill(backgroundImages, img);
    }

    /**
     * @param color .
     */
    public void setColors(Color color) {
        Arrays.fill(colors, color);
    }

    /**
     * @param borderColor1 .
     */
    public void setBorderColor(Color borderColor1) {
        this.borderColor = borderColor1;
    }

    /**
     * @param index .
     * @param color .
     */
    public void setColorsAt(int index, Color color) {
        colors[index] = color;
    }

    /**
     * @param index  .
     * @param cOrNot .
     */
    public void setColorsOrNotAt(int index, boolean cOrNot) {
        colorOrNot[index] = cOrNot;
    }

    /**
     * @param index .
     * @param path  .
     */
    public void setImagesPathAt(int index, String path) {
        Image img = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundImages[index] = img;
    }

    /**
     * @return .
     */
    public Point getLastCollisionPoint() {
        return lastCollisionPoint;
    }

    /**
     * @param lastCollisionPoint1 .
     */
    public void setLastCollisionPoint(Point lastCollisionPoint1) {
        this.lastCollisionPoint = lastCollisionPoint1;
    }

    /**
     * @param p .
     */
    public void setRectPoint(Point p) {
        rect.setUpperLeft(p);
    }

    /**
     * @return .
     */
    public int getHeight() {
        return (int) rect.getHeight();
    }

    /**
     * @param height .
     */
    public void setHeight(double height) {
        rect.setHeight(height);
    }

    /**
     * @return .
     */
    public int getWidth() {
        return (int) rect.getWidth();
    }

    /**
     * @param width .
     */
    public void setWidth(double width) {
        rect.setWidth(width);
    }


}
