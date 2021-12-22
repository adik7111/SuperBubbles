package dag;

import java.util.*;

/**
 * A class implementing the recursive topological sort
 * algorithm in linear time using the depth first search
 * technique inspired by geeks for geeks
 * (url: https://www.geeksforgeeks.org/topological-sorting/).
 * This algorithm uses a Hashmap in order to store information
 * on whether the vertex has been visited or not instead of a
 * stack. This algorithm works in linear time O(n) where n is
 * the number of vertexes.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class TopologicalSorter {

    private List<Vertex> sortedArray;
    private Map<Vertex, Boolean> visited;

    /**
     * The constructor for the topological sorter class
     * which initialises the Arraylist and Hashmap.
     */
    public TopologicalSorter() {
        sortedArray = new ArrayList<>();
        visited = new HashMap<>();
    }

    /**
     * The topological sort method initialises the visited
     * Hashmap using the input graph. It then utilises the
     * recursive topological sort method and finally
     * reverses the sorted array in order to return it in the
     * correct format for the superbubble algorithm.
     *
     * @param graph     Input graph which provides the
     *                  vertices to be topologically sorted
     * @return          A topologically sorted array
     */
    public List<Vertex> topologicalSort(DirectedAcyclicGraph graph) {
        for(Vertex v : graph.getVertexes()) {
            visited.put(v, false);
        }
        recursiveTopologicalSort(graph, graph.getSource());
        Collections.reverse(sortedArray);

        return sortedArray;
    }

    /**
     * The recursive topological sort method recursively
     * solves the input vertex v against the input graph
     * utilising the visited Hashmap. Every time a vertex
     * v is visited for the first time, it is updated
     * as being visited in the Hashmap, and then its
     * children are checked. Once a vertex has no more
     * children, or all its children have been visited,
     * it is added to the sorted array.
     *
     * @param graph     The graph whos vertexes are being
     *                  topologically sorted
     * @param v         Current vertex being solved
     */
    public void recursiveTopologicalSort(DirectedAcyclicGraph graph, Vertex v) {
        visited.replace(v, true);
        for (Vertex vertex : v.getChildren()) {
            if (!visited.get(vertex)) {
                recursiveTopologicalSort(graph, vertex);
            }
        }
        sortedArray.add(v);
    }
}
