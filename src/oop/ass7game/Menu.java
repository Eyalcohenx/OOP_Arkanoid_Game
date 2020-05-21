package oop.ass7game;

/**
 * @param <T> - interface Menu<T> extends Animation.
 */
public interface Menu<T> extends Animation {
    /**
     * @param key       - key to press.
     * @param message   - message to display.
     * @param returnVal - value to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status.
     */
    T getStatus();

    /**
     * @param key     .
     * @param message .
     * @param subMenu .
     *                You will need to add sub-menus support to the Menu<T> interface and its implementation:
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
