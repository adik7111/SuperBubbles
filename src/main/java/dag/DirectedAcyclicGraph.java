package dag;

import java.util.ArrayList;
import java.util.List;

/**
 * The Directed acyclic graph class is a representation of
 * such a graph. It stores the source vertex and the rest of
 * the vertices belonging to the graph.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class DirectedAcyclicGraph {

    private Vertex source;
    private List<Vertex> vertexes;

    /**
     * A constructor that takes as input a single source
     * vertex and after initialising the vertexes list, adds
     * it to the list.
     *
     * @param source    Vertex which is stored as the source
     *                  and added to the vertexes list
     */
    public DirectedAcyclicGraph(Vertex source) {
        this.source = source;
        this.vertexes = new ArrayList<>();
        vertexes.add(source);
    }

    /**
     * A constructor that takes as input a single source
     * vertex and a list of vertexes. The source vertex is
     * stored as the source and the other vertices are added
     * to the vertexes list. The constructor does a check
     * on the list to ensure the source vertex is not added
     * twice.
     *
     * @param source    Vertex which is stored as the source
     *                  and added to the vertexes list
     * @param vertexes  A list of vertexes which is added to
     *                  the vertexes list
     */
    public DirectedAcyclicGraph(Vertex source, List<Vertex> vertexes) {
        this.source = source;
        this.vertexes = new ArrayList<>();
        this.vertexes = vertexes;
        if (!vertexes.contains(source)) vertexes.add(source);
    }

    /**
     * A method which returns the source vertex of the
     * graph.
     *
     * @return      The source vertex stored in the class
     */
    public Vertex getSource() { return source; }

    /**
     * A method which returns all the vertexes of the
     * graph.
     *
     * @return      The list of all vertexes belonging
     *              to the graph
     */
    public List<Vertex> getVertexes() { return vertexes; }

    /**
     * The get Vertex methods takes as input a vertex
     * and returns such a vertex if it belongs to the
     * graph.
     *
     * @param vertex    Vertex to be retrieved if found
     * @return          Retrieved vertex
     */
    public Vertex getVertex(Vertex vertex) {
        if (vertexes.contains(vertex)) return vertex;
        return null;
    }

    /**
     * The add vertex method takes as input a vertex and
     * adds it to the vertexes list (the graph).
     *
     * @param vertex    Vertex to be added to the vertexes
     *                  list
     */
    public void addVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

    /**
     * The contains vertex method checks that the input
     * vertex is a part of the graph (in the vertexes
     * list), and returns true if such is the case or
     * false if the vertexes list does not contain the
     * input vertex.
     *
     * @param vertex    Vertex to be checked for in the
     *                  vertexes list
     * @return          A boolean stating whether the
     *                  vertexes list contains the
     *                  vertex or not
     */
    public boolean containsVertex(Vertex vertex) {
        if (vertexes.contains(vertex)) return true;
        return false;
    }

    /**
     * The size method returns the size of the graph
     * which is gained from the size of the vertexes
     * list.
     *
     * @return      An int which defines the current
     *              size of the graph
     */
    public int size() {
        return vertexes.size();
    }

    /**
     * A method which defines what the graph should look
     * like in string form. The form here is defined as
     * "Graph{V1, V2, ... , Vn-1, Vn}" where n is the
     * size of the graph.
     *
     * @return      A string form of the graph
     */
    @Override
    public String toString() {
        String toReturn = "Graph{";
        for (Vertex v : vertexes) {
            toReturn = toReturn + v.toString() + ", ";
        }
        toReturn = toReturn.substring(0, toReturn.length() -2);
        return toReturn = toReturn + "}";
    }

}
