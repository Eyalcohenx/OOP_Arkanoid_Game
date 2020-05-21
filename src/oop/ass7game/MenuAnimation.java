package oop.ass7game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;


/**
 * class MenuAnimation implements Menu<Task>.
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> selections;
    private boolean stop;
    private KeyboardSensor keyboard;
    private T status;

    /**
     * @param sensor  .
     * @param runner1 .
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner runner1) {
        selections = new ArrayList<>();
        stop = false;
        keyboard = sensor;
        status = null;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        selections.add(new Selection<>(key, message, subMenu.getStatus()));
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        selections.add(new Selection<>(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        stop = false;
        return status;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //drawing rthe options and getting the input.
        int space = 30;
        d.drawText(d.getWidth() / 3, 50, "Menu:", 50);
        int i = 1;
        for (Selection s : selections) {
            d.drawText(d.getWidth() / 3, 55 + (space * (i + 1)),
                    i + ". \"" + s.getKey() + "\" : " + s.getMessage(), 30);
            i++;
        }
        for (Selection s : selections) {
            if (this.keyboard.isPressed(s.getKey())) {
                status = (T) s.getRetuernVal();
                stop = true;
                return;
            }
        }
    }


    @Override
    public boolean shouldStop() {
        return stop;
    }
}

