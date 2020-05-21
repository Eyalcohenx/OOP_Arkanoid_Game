package oop.ass7game;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * class HighScoresTable.
 */
public class HighScoresTable implements Serializable {

    private List<ScoreInfo> scores;
    private int sizeToShow;

    /**
     * @param size // Create an empty high-scores table with the specified size.
     *             // The size means that the table holds up to size top scores.
     */

    public HighScoresTable(int size) {
        scores = new ArrayList<ScoreInfo>();
        sizeToShow = size;
    }

    /**
     * @param filename .
     * @return // Read a table from file and return it.
     * // If the file does not exist, or there is a problem with
     * // reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable hst = new HighScoresTable(1);
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hst = (HighScoresTable) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
        }
        return hst;
    }

    /**
     * @param score Add a high-score.
     */
    public void add(ScoreInfo score) {
        scores.add(score);
        scores.sort(new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo o1, ScoreInfo o2) {
                return o2.getScore() - o1.getScore();
            }
        });
    }

    /**
     * @return - Return table size.
     */
    public int size() {
        return sizeToShow;
    }

    /**
     * @return - Return the current high scores.
     * // The list is sorted such that the highest
     * // scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        // if the size is larger we cut at the wanted size
        if (sizeToShow > scores.size()) {
            return scores;
        }
        // else we return the whole list.
        return scores.subList(0, sizeToShow);
    }

    /**
     * @param score .
     * @return // return the rank of the current score: where will it
     * // be on the list if added?
     * // Rank 1 means the score will be highest on the list.
     * // Rank `size` means the score will be lowest.
     * // Rank > `size` means the score is too low and will not
     * //      be added to the list.
     */
    public int getRank(int score) {
        int counter = 1;
        for (ScoreInfo si : scores) {
            if (si.getScore() > score) {
                counter++;
            }
        }
        if (counter >= sizeToShow) {
            return sizeToShow;
        }
        return counter;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        scores = new ArrayList<ScoreInfo>();
    }


    /**
     * @param filename // Load table data from file.
     *                 // Current table data is cleared.
     * @throws IOException .
     */
    public void load(File filename) throws IOException {
        this.scores = loadFromFile(filename).scores;
    }

    /**
     * @param filename - Save table data to the specified file.
     * @throws IOException .
     */
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * @param size .
     */
    public void setSizeToShow(int size) {
        this.sizeToShow = size;
    }
}
