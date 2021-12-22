package superbubble;

import dag.DirectedAcyclicGraph;
import dag.TopologicalSorter;
import dag.Vertex;
import main.Report;
import main.SuperBubblePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The superbubble class holds all the methods proposed in
 * "Linear-time superbubble identification algorithm for
 * genome assembly" by Brankovic et al. Most notably, it holds
 * the implementation of the superbubble algorithm with its two
 * utility algorithms, the report super bubble and validate
 * superbubble sub-routines.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class SuperBubble {

    private List<Candidate> candidates;
    /**
     * candidateWhenFirstComputed is used to determine that
     * the candidates list is computed correctly before being
     * used in the test classes.
     */
    private List<Candidate> candidatesWhenFirstComputed;
    private List<Vertex> ordD;
    /**
     * ordDTopological stores the vertexes topological value at
     * index but vertexID - 1 (V1 is actually index 0)
     */
    private List<Integer> ordDTopological;
    private List<Candidate> previousEntrance;
    private List<Candidate> alternativeEntrance;
    private List<Integer> OutParent;
    private List<Integer> OutChild;
    private Report report;

    /**
     * A constructor with no input. It sets up all the necessary
     * variables. This constructor is used for test cases.
     */
    public SuperBubble() {
        candidates = new LinkedList<>();
        ordD = new ArrayList<>();
        previousEntrance = new ArrayList<>();
        alternativeEntrance = new ArrayList<>();
        ordDTopological = new ArrayList<>();
        candidatesWhenFirstComputed = new ArrayList<>();
        report = new Report();
    }

    /**
     * A constructor which takes a directed acyclic graph as its
     * input. It uses this graph to compute the topological
     * ordering of the graph and saves it in variable ordD. The
     * constructor continues by computing the other necessary
     * lists (i.e ordDTopological, OutChild, OutParent,
     * alternativeEntrance and previousEntrance). Finally it
     * utilises the superBubble algorithm to store the
     * superbubbles from the graph in the report.
     *
     * @param g     The graph in which superbubbles are to be
     *              analysed.
     */
    public SuperBubble(DirectedAcyclicGraph g) {
        candidates = new LinkedList<>();
        ordD = new ArrayList<>();
        previousEntrance = new ArrayList<>();
        alternativeEntrance = new ArrayList<>();
        ordDTopological = new ArrayList<>();
        candidatesWhenFirstComputed = new ArrayList<>();
        report = new Report();

        TopologicalSorter ts = new TopologicalSorter();
        ordD = ts.topologicalSort(g);
        computeOrdDTopological();
        computeOutChild();
        computeOutParent();
        for (int i = 0; i < ordD.size(); i++) {
            alternativeEntrance.add(null);
        }
        for (int i = 0; i < ordD.size(); i++) {
            previousEntrance.add(null);
        }
        //Algorithm SuperBubble
        superBubble();
    }

    /**
     * @return      Returns the ordD Arraylist
     */
    public List<Vertex> getOrdD() { return ordD; }

    /**
     * @return      Returns the OutParent Arraylist
     */
    public List<Integer> getOutParent() { return OutParent; }

    /**
     * @return      Returns the OutChild Arraylist
     */
    public List<Integer> getOutChild()  { return OutChild; }

    /**
     *
     * @return      Returns the candidates Arraylist
     */
    public List<Candidate> getCandidates() { return candidates; }

    /**
     * @return      Returns the ordDTopological Arraylist
     */
    public List<Integer> getOrdDTopological() { return ordDTopological; }

    /**
     * @return      Returns the alternativeEntrance Arraylist
     */
    public List<Candidate> getAlternativeEntrance() { return alternativeEntrance; }

    /**
     * @return      Returns the previousEntrance Arraylist
     */
    public List<Candidate> getPreviousEntrance() { return previousEntrance; }

    /**
     * @return      Returns the getCandidatesWhenFirstComputed
     *              Arraylist
     */
    public List<Candidate> getCandidatesWhenFirstComputed() { return candidatesWhenFirstComputed; }

    /**
     * @return      Returns the report
     */
    public Report getReport() { return report; }

    /**
     * The superbubble method as proposed in "Linear-time
     * superbubble identification algorithm for genome
     * assembly" by Brankovic et al.
     */
    private void superBubble() {
        //setUp Candidates
        Candidate prevEnt = null;
        for (Vertex v: ordD) {
            alternativeEntrance.set(v.getId()-1, null);
            previousEntrance.set(v.getId()-1, prevEnt);
            if(exit(v)) insertExit(v);
            if(entrance(v)) {
                insertEntrance(v);
                prevEnt = new Candidate(v, "Entrance");
            }
        }
        candidatesWhenFirstComputed.addAll(candidates);
        while (!candidates.isEmpty()) {
            if (entrance(tail().getVertex()) && tail().getType().equals("Entrance")) {
                deleteTail();
            } else {
                reportSuperBubble(head(), tail());
            }
        }
    }

    /**
     * The report Superbubble sub-routine as proposed in
     * "Linear-time superbubble identification algorithm for
     * genome assembly" by Brankovic et al. It takes as
     * input two candidates, and uses their vertexes and type
     * utilising the validate superbubble sub-routine to decide
     * whether to store these or other potential candidates
     * in the superbubble report.
     *
     * @param start     A candidate to be checked for being the
     *                  start of a superbubble
     * @param exit      A candidate to be checked for being the
     *                  end of a superbubble
     */
    public void reportSuperBubble(Candidate start, Candidate exit) {
        if (start == null || exit == null ||
                ordDTopological.get(start.getVertex().getId() - 1) >=
                        ordDTopological.get(exit.getVertex().getId() - 1) ) {
            deleteTail();
            return;
        }
        Candidate s = previousEntrance.get(exit.getVertex().getId() - 1);
        Candidate valid = null;
        while (ordDTopological.get(s.getVertex().getId() -1) >= ordDTopological.get(start.getVertex().getId() - 1)) {
            valid = validateSuperBubble(s.getVertex().getId(), exit.getVertex().getId());
            if (valid.equals(s) || valid.equals(alternativeEntrance.get(s.getVertex().getId() - 1)) ||
            valid.getVertex().getId() == -1) {
                break;
            }
            alternativeEntrance.set(s.getVertex().getId() - 1, valid);
            s = valid;
        }
        deleteTail();
        if (valid.equals(s)) {
            report.addPairToList(new SuperBubblePair(s.getVertex(), exit.getVertex()));
        }
        while(!tail().equals(s)) {
            if (exit(tail().getVertex())) {
                reportSuperBubble(next(s), tail());
            } else {
                deleteTail();
            }
        }
        return;
    }

    /**
     * The validate superbubble method takes as input the id of
     * two vertexes, which represent the start and end vertex of
     * a potential superbubble. The algorithm then returns a
     * candidate if the superbubble is valid i.e the two vertexes
     * represent a superbubble. However, if invalid the method
     * returns a candidate with "-1" as the id of the vertex.
     *
     * @param startVertex   int representing the start vertex ID
     *                      of the potential superbubble
     * @param endVertex     int representing the end vertex ID of
     *                      the potential superbubble
     * @return              Method returns an entrance candidate
     *                      for the potential superbubble
     */
    public Candidate validateSuperBubble(int startVertex, int endVertex) {
        int start = ordDTopological.get(startVertex - 1);
        int end = ordDTopological.get(endVertex - 1);
        int outChild = rangeMax(OutChild, start, end - 1);
        int outParent = rangeMin(OutParent, start + 1, end);
        if (outChild != end) return new Candidate(new Vertex(-1), "Entrance");
        if (outParent == start) {
            return new Candidate(new Vertex(startVertex), "Entrance");
        } else if (entrance(vertex(outParent - 1))) {
            return new Candidate(new Vertex(vertex(outParent - 1).getId()), "Entrance");
        } else {
            return previousEntrance.get(outParent - 1);
        }
    }

    /**
     * The rangeMin method takes as input a list and two int's,
     * representing two indexes of the provided list. The algorithm
     * then looks for the smallest number in the Integer list
     * between the two int's and returns it.
     *
     * @param v         An Integer list within which the smallest
     *                  integer between the two int's is to be found
     * @param start     An int defining the index to start the check
     *                  for in the Integer list
     * @param end       An int defining the last index (inclusive) to
     *                  stop looking for the smallest integer
     * @return          Smallest Integer of list v
     */
    public int rangeMin(List<Integer> v, int start, int end) {
        int smallestNum = -1;

        for (int i = start; i <= end; i++) {
            int current = v.get(i - 1);
            if (smallestNum == -1) {
                smallestNum = current;
            } else if (smallestNum > current) {
                smallestNum = current;
            }

        }
        return smallestNum;
    }

    /**
     * The rangeMax method takes as input a list and two int's,
     * representing two indexes of the provided list. The algorithm
     * then looks for the largest number in the Integer list
     * between the two int's and returns it.
     *
     * @param v         An Integer list within which the largest
     *                  integer between the two int's is to be found
     * @param start     An int defining the index to start the check
     *                  for in the Integer list
     * @param end       An int defining the last index (inclusive) to
     *                  stop looking for the smallest integer
     * @return          Largest Integer of list v
     */
    public int rangeMax(List<Integer> v, int start, int end) {
        int largestNum = -1;

        for (int i = start; i <= end; i++) {
            int current = v.get(i - 1);
            if (largestNum == -1) {
                largestNum = current;
            } else if (largestNum < current) {
                largestNum = current;
            }

        }
        return largestNum;
    }

    /**
     * The vertex method takes as input an int i which represents
     * the index position that is trying to be returned from the
     * ordD array which is the topologically sorted array of the
     * graph.
     *
     * @param i     int which refers to the index position being
     *              queried for
     * @return      A vertex at the position i of the topologically
     *              sorted array
     */
    public Vertex vertex(int i) { return ordD.get(i); }

    /**
     * Takes as input a vertex v and returns the position of the
     * vertex in the topologically ordered array.
     *
     * @param v     Vertex which is being queried for its position
     *              in the topologically sorted array
     * @return      The integer position at the topologically
     *              sorted array
     */
    public Integer vertexIndex(Vertex v) { return ordDTopological.get(v.getId() - 1); }

    /**
     * Compute ordDTopological is a method that computes the
     * ordDTopological array created for this particular
     * implementation of the superbubble algorithm. It uses the
     * ordD array and only stores each vertex as its ID instead
     * of storing it as a vertex.
     *
     */
    private void computeOrdDTopological() {
        for (int i = 0; i < ordD.size(); i++) {
            ordDTopological.add(0);
        }
        for (int i = 0; i < ordD.size(); i++) {
            //ordDTopological.set(i, ordD.get(i).getId());
            ordDTopological.set(ordD.get(i).getId() - 1, i + 1);
        }
    }

    /**
     * The compute out parent method runs in O(n + m) time. The method
     * goes through each vertex and through each vertexes edge to compute
     * the vertex most furtherst away from itself and stores it in the
     * OutParent array.
     */
    private void computeOutParent() {
        this.OutParent = new ArrayList<>();
        for (int i = 0; i < ordD.size(); i++) {
            Vertex currentVertex = ordD.get(i);
            if (!currentVertex.getParents().isEmpty()) {
                Integer furthestAway = ordD.size();
                for (Vertex v: currentVertex.getParents()) {
                    Integer temp = vertexIndex(v);//runs now in o(1)
                    if (temp < furthestAway) {
                        furthestAway = temp;
                    }
                }
                this.OutParent.add(furthestAway);
            } else {
                this.OutParent.add(null);
            }
        }
    }

    /**
     * The compute out child method runs in O(n + m) time. The method
     * goes through each vertex and through each vertexes edge to compute
     * the vertex that is closest to itself and stores it in the
     * OutParent array.
     */
    private void computeOutChild() {
        this.OutChild = new ArrayList<>();
        for (int i = 0; i < ordD.size(); i++) {
            Vertex currentVertex = ordD.get(i);
            if (!currentVertex.getChildren().isEmpty()) {
                Integer furthestAway = 0;
                for (Vertex v: currentVertex.getChildren()) {
                    Integer temp = vertexIndex(v);//runs now in 0(1)
                    if (temp > furthestAway) {
                        furthestAway = temp;
                    }
                }
                this.OutChild.add(furthestAway);
            } else {
                this.OutChild.add(null);
            }
        }
    }

    /**
     * The entrance method takes as input a vertex and returns a
     * boolean value whether or not the vertex classifies as an
     * entrance candidate or not. A vertex classifies as an entrance
     * candidate if at least one child has exactly one parent (this
     * vertex).
     *
     * @param vertex    Vertex to be checked for entrance candidacy
     * @return          Returns true if the vertex is an entrance
     *                  candidate and false if not
     */
    public boolean entrance(Vertex vertex) {
        boolean toReturn = false;
        for (Vertex v: vertex.getChildren()) {
            if (v.getParents().size() == 1) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    /**
     * The exit method takes as input a vertex and returns a
     * boolean value whether or not the vertex classifies as an
     * exit candidate or not. A vertex classifies as an exit
     * candidate if at least one parent has exactly one child (this
     * vertex).
     *
     * @param vertex    Vertex to be checked for exit candidacy
     * @return          Returns true if the vertex is an exit
     *                  candidate and false if not
     */
    public boolean exit(Vertex vertex) {
        boolean toReturn = false;
        for (Vertex v: vertex.getParents()) {
            if (v.getChildren().size() == 1) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    /**
     * The insert entrance method takes as input a vertex, and
     * creates a new candidate object with that vertex and the
     * type "Entrance", and adds this into the candidates list.
     *
     * @param vertex    Vertex to be created into a candidate
     *                  and added to the candidates list
     */
    public void insertEntrance(Vertex vertex) {
        Candidate c = new Candidate(vertex, "Entrance");
        candidates.add(c);
    }

    /**
     * The insert exit method takes as input a vertex, and
     * creates a new candidate object with that vertex and the
     * type "Exit", and adds this into the candidates list.
     *
     * @param vertex    Vertex to be created into a candidate
     *                  and added to the candidates list
     */
    public void insertExit(Vertex vertex) {
        Candidate c = new Candidate(vertex, "Exit");
        candidates.add(c);
    }

    /**
     * The head method is used to return the first candidate
     * in the candidates list
     *
     * @return      Returns first element in candidates list
     */
    public Candidate head() { return candidates.get(0); }

    /**
     * The tail method is used to return the last candidate
     * in the candidates list
     *
     * @return      Returns last element in candidates list
     */
    public Candidate tail() { return candidates.get(candidates.size() - 1); }

    /**
     * The delete tail method is used to delete the last
     * element in the candidates list.
     */
    public void deleteTail() { candidates.remove(candidates.size() - 1); }

    /**
     * The next method takes as input a candidate c, and
     * iterates through the candidates list to find the next
     * candidate following the input candidate. If no such
     * candidate or it is the last element in the candidates
     * list, the list returns null.
     *
     * @param c     Candidate for which to find the following
     *              candidate in the candidates list
     * @return      Returns the candidate following c in the
     *              candidates list
     */
    public Candidate next(Candidate c) {
        Iterator<Candidate> iterator = candidates.iterator();
        //Take O(n) time.
        while(iterator.hasNext()) {
            if (iterator.next().equals(c) && iterator.hasNext()) {
                Candidate current = iterator.next();
                return current;
            }
        }
        return null;
    }
}
