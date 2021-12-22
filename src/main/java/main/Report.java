package main;

import java.util.ArrayList;
import java.util.List;

/**
 * The report class stores all the pairs of superbubbles
 * generated by the superbubble algorithm.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class Report {

    private List<SuperBubblePair> listOfPairs;

    /**
     * Constructor which initialises the arraylist listOfPairs
     * which will store all pairs produced by the superbubble
     * algorithm.
     */
    public Report() {
        listOfPairs = new ArrayList<>();
    }

    /**
     * A method which returns the listOfPairs stored in this
     * class.
     *
     * @return      Returns the listOfPairs
     */
    public List<SuperBubblePair> getListOfPairs() { return listOfPairs; }

    /**
     * The add pair to list method takes as input a superbubble
     * pair and adds it to the listOfPairs ArrayList.
     *
     * @param pair      A superbubble pair to be added to the
     *                  listOfPairs list
     */
    public void addPairToList(SuperBubblePair pair) {
        listOfPairs.add(pair);
    }

    /**
     * A method which defines how a report should be represented
     * in string format. Here we define the format as:
     * "Super Bubble at: {V1, V2}
     *  Super Bubble at: {V3, V4}
     *  Super Bubble at: {V5, V6}"
     *
     * @return      A string format of the report
     */
    @Override
    public String toString() {
        String toReturn = "";
        for (SuperBubblePair pair: listOfPairs) {
            toReturn = toReturn + "Super Bubble at: " + pair.toString() + "\n";
        }
        return toReturn;
    }
}
