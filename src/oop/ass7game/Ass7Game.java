package oop.ass7game;

import biuoop.GUI;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import static oop.ass7game.GameFlow.HEIGHT;
import static oop.ass7game.GameFlow.WIDTH;

/**
 * Ass3 main class.
 */
public class Ass7Game {
    /**
     * @param args .
     */
    public static void main(String[] args) {
        //creating gui and other stuff
        GUI gui = new GUI("Ass7Game", WIDTH, HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        GameFlow game = new GameFlow(runner, gui);
        InputStream is;
        if (args.length > 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));

        //running game
        game.runLevels(reader);
    }
}
