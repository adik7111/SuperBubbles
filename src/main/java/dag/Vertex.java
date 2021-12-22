package dag;

import java.util.ArrayList;
import java.util.List;

/**
 * The Vertex Class is a representation of vertices in a
 * directed acyclic graph. The class stores the id, parent
 * vertices and child vertices of the vertex.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 **/

public class Vertex {

    private int id;
    private List<Vertex> parents;
    private List<Vertex> children;

    /**
     * Constructor which initialises the Vertexes parents and
     * children list.
     *
     * @param id    Sets the vertexes id to this int
     */
    public Vertex(int id) {
        this.id = id;
        parents = new ArrayList<>();
        children = new ArrayList<>();
    }

    /**
     * A method to return the parents list stored in the class.
     *
     * @return  returns the parents list
     */
    public List<Vertex> getParents() { return parents; }

    /**
     * A method to return the children list stored in
     * the class.
     *
     * @return  returns the children list
     */
    public List<Vertex> getChildren() { return children; }

    /**
     * A method to return the id stored in the class.
     *
     * @return  returns the id of the vertex
     */
    public int getId() { return id; }

    /**
     * A method which sets the vertex to the input id.
     *
     * @param id    Sets the vertex to this id
     */
    public void setId(int id) { this.id = id; }

    /**
     * The add child method takes an input vertex and adds
     * it to the vertexes children list.
     *
     * @param vertex    Input vertex which is added to the
     *                  children list
     */
    public void addChild(Vertex vertex) {
        if (!this.children.contains(vertex)) {
            children.add(vertex);
            vertex.addParent(this);
        }
    }

    /**
     * The add parent method takes an input vertex and adds
     * it to the vertexes parents list.
     *
     * @param vertex    Input vertex which is added to the
     *                  parents list
     */
    public void addParent(Vertex vertex) {
        if (!this.parents.contains(vertex)) {
            parents.add(vertex);
            vertex.addChild(this);
        }
    }

    /**
     * The remove parent method takes an input vertex and
     * checks whether it is in the parents list. If it is
     * the vertex is removed and the method returns true.
     * If the input vertex is not apart of the parents list
     * then the method returns false as no vertex has been
     * removed.
     *
     * @param vertex    Vertex to be deleted from the parents
     *                  list
     * @return          A boolean stating whether or not the
     *                  input vertex has been removed
     */
    public boolean removeParent(Vertex vertex) {
        boolean removed = parents.contains(vertex);
        if (removed) {
            for (int i = 0; i < vertex.getChildren().size(); i++) {
                if (vertex.getChildren().get(i).equals(this)) {
                    vertex.getChildren().remove(vertex.getChildren().get(i));
                }
            }
            parents.remove(vertex);
        }
        return removed;
    }

    /**
     * The remove child method takes an input vertex and
     * checks whether it is in the children list. If it is
     * the vertex is removed and the method returns true.
     * If the input vertex is not apart of the children list
     * then the method returns false as no vertex has been
     * removed.
     *
     * @param vertex    Vertex to be deleted from the children
     *                  list
     * @return          A boolean stating whether or no the
     *                  input vertex has been removed.
     */
    public boolean removeChild(Vertex vertex) {
        boolean removed = children.contains(vertex);
        if(removed) {

            for (int i = 0; i < vertex.getParents().size(); i++) {
                if (vertex.getParents().get(i).equals(this)) {
                    vertex.getParents().remove(vertex.getParents().get(i));
                }
            }
            children.remove(vertex);
        }
        return removed;
    }

    /**
     * The add parents method takes a list input of vertexes
     * and adds them all to the parents list.
     *
     * @param vertexesToAdd List of vertexes to add to the
     *                      parent list
     */
    public void addParents(List<Vertex> vertexesToAdd) {
        parents.addAll(vertexesToAdd);
    }

    /**
     * The add children method takes a list input of
     * vertexes and adds them all to the children list.
     *
     * @param vertexesToAdd List of vertexes to add to the
     *                      children list
     */
    public void addChildren(List<Vertex> vertexesToAdd) {
        children.addAll(vertexesToAdd);
    }

    /**
     * A method which defines that a vertex should be in the
     * form of "Vn" as a string, where n is the id of the
     * vertex.
     *
     * @return  The string form of the vertex
     */
    @Override
    public String toString() {
        return "V" + id;
    }

    /**
     * Equals method which checks whether the input object
     * is equal to the object using the method. A vertex is
     * equal to another vertex if it has the same id.
     *
     * @param o     Input object checked against the object
     *              calling the method
     * @return      A boolean, which is true if the two objects
     *              equal each other and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex v = (Vertex) o;

        return v.getId() == id;
    }
}
