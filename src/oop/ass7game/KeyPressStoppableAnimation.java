package oop.ass7game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class KeyPressStoppableAnimation implements Animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * @param sensor    .
     * @param key       .
     * @param animation .
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.key = key;
        keyboard = sensor;
        this.animation = animation;
        stop = false;
        isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        //did what was suggested in the ass6
        if (this.keyboard.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}