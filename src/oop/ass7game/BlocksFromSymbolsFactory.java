package oop.ass7game;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * class BlocksFromSymbolsFactory.
 */
public class BlocksFromSymbolsFactory {
    private Dictionary<String, BlockCreator> blockCreators;
    private Dictionary<String, Integer> spacerWidths;
    private Block defaultBlock;

    /**
     * @param fileName - file to read the definitions from.
     */
    public BlocksFromSymbolsFactory(String fileName) {
        defaultBlock = new Block();
        blockCreators = new Hashtable<>();
        spacerWidths = new Hashtable<>();
        List<String> lines = new ArrayList<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
        String line1;
        try {
            while ((line1 = reader.readLine()) != null) {
                lines.add(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //deviding to lines
        for (String line : lines) {
            //checking if its not a comment
            if (line.length() > 0 && line.charAt(0) != '#') {
                //splitting by whitespace
                String[] words = line.split(" ");
                if (words[0].equals("default")) {
                    defaultBlock = stringToBlock(line);
                } else if (words[0].equals("bdef")) {
                    blockCreators.put(extractSymbol(line), new MyBlockCreator(stringToBlock(line)));
                } else if (words[0].equals("sdef")) {
                    spacerWidths.put(extractSymbol(line), extractWidth(line));
                }
            }
        }
    }

    /**
     * @param s .
     * @return - returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.get(s) != null;
    }

    /**
     * @param s .
     * @return - returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.get(s) != null;
    }

    /**
     * @param s .
     * @param x .
     * @param y .
     * @return - Return a block according to the definitions associated
     * with symbol s. The block will be located at position (x, y).
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * @param s .
     * @return - Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * @param line - converts string line to block definition.
     * @return .
     */
    private Block stringToBlock(String line) {
        Block b = new Block(defaultBlock);
        ColorsParser cParser = new ColorsParser();
        String[] words = line.split(" ");
        //cutting by ':'
        for (String definition : words) {
            String[] sp = definition.split(":");
            if ("height".equals(sp[0])) {
                b.setHeight(Double.parseDouble(sp[1]));

            } else if ("width".equals(sp[0])) {
                b.setWidth(Double.parseDouble(sp[1]));

            } else if ("hit_points".equals(sp[0])) {
                b.setCounterForFactory(Integer.parseInt(sp[1]));

            } else if ("fill".equals(sp[0])) {
                //checking if it starts with color
                if (sp[1].startsWith("color")) {
                    b.setColorOrNot(true);
                    String temp = sp[1].substring(6);
                    b.setColors(cParser.colorFromString(temp));

                } else if (sp[1].startsWith("image")) {
                    //setting image path for the default.
                    b.setColorOrNot(false);
                    String path = sp[1].substring(6, sp[1].length() - 1);
                    b.setBackgroundImages(path);
                }
            } else if (sp[0].startsWith("fill-")) {
                String fillIndex = sp[0].substring(5);
                int fIndex = Integer.parseInt(fillIndex);
                //checking if it starts with color
                if (sp[1].startsWith("color")) {
                    b.setColorsOrNotAt(fIndex, true);
                    String temp = sp[1].substring(6);
                    b.setColorsAt(fIndex, cParser.colorFromString(temp));

                } else if (sp[1].startsWith("image")) {
                    //setting image path for the default.
                    b.setColorsOrNotAt(fIndex, false);
                    String path = sp[1].substring(6, sp[1].length() - 1);
                    b.setImagesPathAt(fIndex, path);

                }
            } else if (sp[0].equals("stroke")) {
                if (sp[1].startsWith("color")) {
                    String temp = sp[1].substring(6);
                    b.setBorderColor(cParser.colorFromString(temp));

                }
            }
        }

        return b;
    }

    /**
     * @param line .
     * @return .
     */
    private String extractSymbol(String line) {
        String[] words = line.split(" ");
        String[] sp = words[1].split(":");
        return sp[1];
    }

    /**
     * @param line .
     * @return .
     */
    private int extractWidth(String line) {
        String[] words = line.split(" ");
        String[] sp = words[2].split(":");
        return Integer.parseInt(sp[1]);
    }

}