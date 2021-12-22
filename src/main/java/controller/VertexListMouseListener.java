package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Vertex list mouse listener is utilised by the vertex list model
 * and checks for any mouse clicks on the list, and acts accordingly
 * to a click.
 */
public class VertexListMouseListener implements MouseListener {

    private JList vertexList;
    private DefaultListModel<Vertex> childrenList, parentsList;
    private JLabel childrenLabel, parentsLabel;
    private String children, parents;

    public VertexListMouseListener(JList vertexList, JLabel childrenLabel, JLabel parentsLabel,
                                    DefaultListModel<Vertex> childrenList, DefaultListModel<Vertex> parentsList) {
        this.vertexList = vertexList;
        this.childrenLabel = childrenLabel;
        this.parentsLabel = parentsLabel;
        this.childrenList = childrenList;
        this.parentsList = parentsList;
        children = "Children of Vertex ";
        parents = "Parents of Vertex ";
    }

    /**
     * If a vertex is clicked on the vertexes list, it is
     * highlighted and its children and parents are shown
     * on the childrens and parents lists in the center of
     * the application. If anything else is pressed on the
     * list nothing happens.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (e.getClickCount() == 1) {
                Vertex currentVertex = (Vertex) vertexList.getSelectedValue();
                String childrenTemp = children + currentVertex.toString() + ":";
                String parentsTemp = parents + currentVertex.toString() + ":";
                childrenLabel.setText(childrenTemp);
                parentsLabel.setText(parentsTemp);
                childrenList.clear();
                parentsList.clear();
                if (!currentVertex.getChildren().isEmpty()) {
                    for (int i = 0; i < currentVertex.getChildren().size(); i++) {
                        childrenList.addElement(currentVertex.getChildren().get(i));
                    }
                }
                if (!currentVertex.getParents().isEmpty()) {
                    for (int i = 0; i < currentVertex.getParents().size(); i++) {
                        parentsList.addElement(currentVertex.getParents().get(i));
                    }
                }
            }
        } catch (NullPointerException exc) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
