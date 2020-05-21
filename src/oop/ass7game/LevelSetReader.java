package oop.ass7game;

import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;


import java.util.List;

/**
 * class LevelSetReader .
 */
public class LevelSetReader {
    /**
     * @param reader   .
     * @param gameFlow .
     * @return new menu from the specification file
     */
    public Menu<Void> createMenueSet(LineNumberReader reader, GameFlow gameFlow) {
        MenuAnimation menu = new MenuAnimation(gameFlow.getGui().getKeyboardSensor(), gameFlow.getRunner());
        String lineK = null;
        String linePath = null;
        try {
            lineK = reader.readLine();
            linePath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (lineK != null) {
            String[] kAndName = lineK.split(":");

            Reader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream(linePath)));
            LevelSpecificationReader lsr = new LevelSpecificationReader();
            //creating levels array
            List<LevelInformation> levels = lsr.fromReader(r);

            menu.addSelection(kAndName[0], kAndName[1], new PlayGameAndEndScreenTask(gameFlow, levels));


            try {
                lineK = reader.readLine();
                linePath = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return menu;
    }
}
