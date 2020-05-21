package oop.ass7game;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * class Background implements Sprite.
 * just a background class to hold picture of the background.
 */
public class Background implements Sprite {
    private String imagePath;
    private BufferedImage image;
    private Color backgroundColor;

    /**
     * @param imagePath1 - path to image of background.
     */
    public Background(String imagePath1) {
        imagePath = imagePath1;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param backgroundColor1 - path to image of background.
     */
    public Background(Color backgroundColor1) {
        backgroundColor = backgroundColor1;
    }

    /**
     * empty constructor.
     */
    public Background() {
    }

    /**
     * @param imagePath1 .
     */
    public void setImagePath(String imagePath1) {
        imagePath = imagePath1;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param backgroundColor1 .
     */
    public void setBackgroundColor(Color backgroundColor1) {
        this.backgroundColor = backgroundColor1;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (image != null) {
            d.drawImage(0, 0, image);
        } else {
            d.setColor(backgroundColor);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        }
    }

    @Override
    public void timePassed() {

    }
}
