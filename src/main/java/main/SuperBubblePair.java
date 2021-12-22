package main;

import dag.Vertex;

/**
 * The super bubble pair class holds two vertexes which make
 * up the entrance and exit of a superbubble. It is utilised
 * by the Report class which stores all superbubbles using
 * this format.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class SuperBubblePair {

    private Vertex start;
    private Vertex end;

    /**
     * The constructor takes as input 2 vertexes, the first
     * representing the start vertex of the superbubble and the
     * second representing the end vertex of the superbubble.
     * These are stored in this class in the start and end
     * variables.
     *
     * @param start     Vertex to be stored as the start vertex
     *                  of the pair
     * @param end       Vertex to be stored as the end vertex
     *                  of the pair
     */
    public SuperBubblePair(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A method which returns the start vertex of the superbubble
     * pair.
     *
     * @return      Returns the start vertex
     */
    public Vertex getStart() { return start; }

    /**
     * A method which returns the end vertex of the superbubble
     * pair.
     *
     * @return      Returns the end vertex
     */
    public Vertex getEnd() {return end; }

    /**
     * A method which defines that a superbubblePair should
     * be in the form of "{Vx, Vy}" where x is the id of the
     * start vertex and y is the id of the end vertex.
     *
     * @return  The string form of the superbubblePair
     */
    @Override
    public String toString() {
        return "{" + start + ", " + end + "}";
    }

    /**
     * Equals method which checks whether the input object is
     * equal to the object using the method. Two superbubblePairs
     * are equal to each other if both the start and end vertexes
     * are the same.
     *
     * @param o     Input object checked against the object
     *              calling the method
     * @return      A boolean, which is true if the two objects
     *              equal each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SuperBubblePair)) return false;
        SuperBubblePair c = (SuperBubblePair) o;

        if (start.equals(c.getStart()) && end.equals(c.getEnd())) {
            return true;
        } else {
            return false;
        }
    }
}
