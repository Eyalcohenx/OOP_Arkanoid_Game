package oop.ass7game;

/**
 * adapter to make a menu as task.
 *
 * @param <T>
 */
public class MenuTask<T> implements Task<Object> {
    private Menu<Task> menu;
    private AnimationRunner runner;

    /**
     * @param menu   .
     * @param runner .
     */
    public MenuTask(Menu<Task> menu, AnimationRunner runner) {
        this.menu = menu;
        this.runner = runner;
    }

    @Override
    public Object run() {
        runner.run(menu);
        // wait for user selection
        Task task = menu.getStatus();
        task.run();
        return null;
    }
}
