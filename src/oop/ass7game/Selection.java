package oop.ass7game;

/**
 * @param <T> .
 */
public class Selection<T> {
    private String key;
    private String message;
    private T retuernVal;

    /**
     * @param key        .
     * @param message    .
     * @param retuernVal .
     */
    protected Selection(String key, String message, T retuernVal) {
        this.key = key;
        this.message = message;
        this.retuernVal = retuernVal;
    }

    /**
     * @return .
     */
    public String getKey() {
        return key;
    }

    /**
     * @return .
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return .
     */
    public T getRetuernVal() {
        return retuernVal;
    }

}