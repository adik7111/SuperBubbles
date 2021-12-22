package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * The Delete parent action listener is utilised by the delete parent
 * button and deletes the selected parent for a specific vertex in its
 * parent list.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class DeleteParentActionListener implements ActionListener {

    private JList parentList, vertexList;
    private DefaultListModel<Vertex> parentModel, vertexListModel;
    private UpdateGraphInfo ug;
    private JTextArea graphInfo;

    public DeleteParentActionListener(JList parentList , JList vertexList, DefaultListModel<Vertex> parentModel,
                                      JTextArea graphInfo, DefaultListModel<Vertex> vertexListModel) {
        this.parentList = parentList;
        this.vertexList = vertexList;
        this.parentModel = parentModel;
        this.graphInfo = graphInfo;
        this.vertexListModel = vertexListModel;
    }

    /**
     * Once the button is clicked, if a parent is selected for a
     * specific vertex it will delete it from its parent list. If
     * no parent is selected an error message is returned.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Vertex selectedVertex = (Vertex) parentList.getSelectedValue();
        if (selectedVertex != null) {
            Vertex currentVertex = (Vertex) vertexList.getSelectedValue();
            currentVertex.removeParent(selectedVertex);
            parentModel.clear();
            for (int i = 0; i < currentVertex.getChildren().size(); i++) {
                parentModel.addElement(currentVertex.getChildren().get(i));
            }
            ug = new UpdateGraphInfo(vertexListModel, graphInfo);
        } else {
            JFrame frame = new JFrame("Clicked");
            frame.setSize(200, 100);
            frame.add(new JLabel("No Parent Selected."));
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}