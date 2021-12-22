package superbubble;

import dag.Vertex;

/**
 * The candidate class is used to store a vertex and its type
 * with regards to whether it is an entrance or an exit
 * candidate. The type can only be "Entrance" or "Exit", and
 * and attempt to initialise a candidate without such a type
 * will return an illegal argument exception error.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class Candidate {

    private Vertex vertex;
    private String type;

    /**
     * A constructor that takes a vertex and a string as input
     * and stores the vertex as a candidate with its
     * accompanying type. The type can only be a string written
     * exactly as "Entrance" or "Exit", otherwise an illegal
     * argument exception will be thrown.
     *
     * @param v     Vertex to be stored in this candidate
     * @param s     Type of candidate the input vertex is
     */
    public Candidate(Vertex v, String s) {
        vertex = v;
        type = s;
        if (!s.equals("Entrance")) {
            if (!s.equals("Exit")) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * A method which returns the vertex stored in this
     * candidate.
     *
     * @return      The vertex stored in this class
     */
    public Vertex getVertex() {
        return vertex;
    }

    /**
     * A method which returns the string type stored
     * for this candidate.
     *
     * @return      The string definition of the type
     *              of candidate stored in this class
     */
    public String getType() {
        return type;
    }

    /**
     * A method which defines what a candidate should be in
     * string format. Here we define it as:
     * "Candidate: Vn, type" where n is the id of the vertex
     * and the type is the "Entrance" or "Exit" string
     * depending what type the vertex is.
     *
     * @return      A string format of the candidate
     */
    @Override
    public String toString() {
        return "Candidate: " + vertex.getId() + ", " + type;
    }

    /**
     * Equals method which checks whether the input object is
     * equal to the object using the method. Two candidates
     * equal each other if the candidate stores both the same
     * vertex and is of the same type (i.e "Entrance" or "Exit).
     *
     * @param o     Input object checked against the object
     *              calling the method
     * @return      A boolean, which is true if the two objects
     *              equal each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate c = (Candidate) o;

        if (vertex.equals(c.getVertex()) && type.equals(c.getType())) {
            return true;
        } else {
            return false;
        }
    }
}
