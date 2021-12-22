package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add vertex action is utilised by the add vertex button
 * and adds a vertex to the graph.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class AddVertexAction implements ActionListener {

    private DefaultListModel<Vertex> list;
    private JTextArea graphInfo;
    private UpdateGraphInfo ug;

    public AddVertexAction(DefaultListModel<Vertex> list, JTextArea graphInfo) {
        this.graphInfo = graphInfo;
        this.list = list;
    }

    /**
     * Once the button is clicked, the vertexes list will be updated
     * with either the first vertex of id 1 or a new vertex which has
     * the id + 1 of the previous vertex in the vertexes list.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Vertex toAdd;
        if (list.isEmpty()) {
            toAdd = new Vertex(1);
        } else {
            toAdd = new Vertex(list.lastElement().getId() + 1);
        }
        list.addElement(toAdd);
        ug = new UpdateGraphInfo(list, graphInfo);
    }
}
