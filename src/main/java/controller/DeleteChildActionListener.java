package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * The Delete child action listener is utilised by the delete child
 * button and deletes the selected child for a specific vertex in its
 * child list.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class DeleteChildActionListener implements ActionListener {

    private JList childList, vertexList;
    private DefaultListModel<Vertex> childModel, vertexListModel;
    private UpdateGraphInfo ug;
    private JTextArea graphInfo;

    public DeleteChildActionListener(JList childList , JList vertexList, DefaultListModel<Vertex> childModel,
                                     JTextArea graphInfo, DefaultListModel<Vertex> vertexListModel) {
        this.childList = childList;
        this.vertexList = vertexList;
        this.childModel = childModel;
        this.graphInfo = graphInfo;
        this.vertexListModel = vertexListModel;
    }

    /**
     * Once the button is clicked, if a child is selected for a
     * specific vertex it will delete it from its child list. If
     * no child selected an error message is returned.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Vertex selectedVertex = (Vertex) childList.getSelectedValue();
        if (selectedVertex != null) {
            Vertex currentVertex = (Vertex) vertexList.getSelectedValue();
            currentVertex.removeChild(selectedVertex);
            childModel.clear();
            for (int i = 0; i < currentVertex.getChildren().size(); i++) {
                childModel.addElement(currentVertex.getChildren().get(i));
            }
            ug = new UpdateGraphInfo(vertexListModel, graphInfo);
        } else {
            JFrame frame = new JFrame("Clicked");
            frame.setSize(200, 100);
            frame.add(new JLabel("No child Selected."));
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
