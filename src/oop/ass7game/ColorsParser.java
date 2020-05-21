package oop.ass7game;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * class ColorsParser.
 */
public class ColorsParser {
    /**
     * @param s .
     * @return - parse color definition and return the specified color.
     */
    public Color colorFromString(String s) {
        Color c;
        //checking if its starts with RGB
        if (s.startsWith("RGB")) {
            s = s.substring(4, s.length() - 2);
            String[] temps = s.split(",");
            c = new Color(Integer.parseInt(temps[0]),
                    Integer.parseInt(temps[1]), Integer.parseInt(temps[2]));
        } else { //just color name
            s = s.substring(0, s.length() - 1);
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                c = (Color) field.get(null);
            } catch (Exception e) {
                c = null; // Not defined
                e.printStackTrace();
            }
        }
        return c;
    }
}
