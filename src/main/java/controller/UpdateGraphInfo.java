package controller;

import dag.Vertex;

import javax.swing.*;

/**
 * The Update Graph Info class is used in most buttons in order
 * to update the graph information whenever a new vertex or edge
 * is added to the graph.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class UpdateGraphInfo {

    private DefaultListModel<Vertex> vertexList;
    private JTextArea graphInfo;

    public UpdateGraphInfo(DefaultListModel<Vertex> vertexList, JTextArea graphInfo) {
        this.vertexList = vertexList;
        this.graphInfo = graphInfo;
        updateGraphInfoTA();
    }

    /**
     * The updateGraphInfoTA method loops through the vertex list
     * each time a vertex is added and updates the graph information.
     */
    public void updateGraphInfoTA() {
        String vertexToDisplay = "Vertexes in Graph: " + vertexList.size() + ".";
        String edgesToDisplay = "\nEdges in Graph: ";
        int amountOfEdges = 0;
        Vertex current = null;
        for (int i = 0; i < vertexList.size(); i++) {
            current = vertexList.getElementAt(i);
            amountOfEdges = amountOfEdges + current.getChildren().size();
        }
        edgesToDisplay = edgesToDisplay + amountOfEdges + ".";
        graphInfo.setText(vertexToDisplay + edgesToDisplay);
    }
}
