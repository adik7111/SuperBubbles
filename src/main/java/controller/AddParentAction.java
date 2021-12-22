package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Action listener class utilised by the add parent button.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class AddParentAction implements ActionListener {

    private JList vertexList;
    private JTextField parent;
    private DefaultListModel<Vertex> parentListModel, vertexListModel;
    private UpdateGraphInfo ug;
    private JTextArea graphInfo;

    public AddParentAction(JTextField parent, DefaultListModel<Vertex> parentListModel,
                          DefaultListModel<Vertex> vertexListModel, JList vertexList, JTextArea graphInfo) {
        this.parent = parent;
        this.parentListModel = parentListModel;
        this.vertexListModel = vertexListModel;
        this.vertexList = vertexList;
        this.graphInfo = graphInfo;
    }

    /**
     * The parent text field is checked to confirm the input
     * vertex is of the correct format "Vn", where n is an
     * id of a vertex currently in the graph. If not of that
     * format then an error message is returned, otherwise the
     * parent is added to the currently selected vertex in the
     * vertexes list. If no vertex currently selected an error
     * message with this information is returned.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String written = parent.getText();

        if (written.matches("V{1}[0-9]+") ) {
            boolean contains = false;
            Vertex currentVertex = null;
            for (int i = 0; i < vertexListModel.size(); i++) {
                currentVertex = vertexListModel.getElementAt(i);
                if (currentVertex.toString().equals(written)) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                Vertex selectedVertex = (Vertex) vertexList.getSelectedValue();
                if (selectedVertex != null && !selectedVertex.equals(currentVertex)) {
                    selectedVertex.addParent(currentVertex);
                    parentListModel.clear();
                    for (int i = 0; i < selectedVertex.getChildren().size(); i++) {
                        parentListModel.addElement(selectedVertex.getChildren().get(i));
                    }
                    ug = new UpdateGraphInfo(vertexListModel, graphInfo);
                } else {
                    JFrame frame = new JFrame("Clicked");
                    frame.setSize(400, 100);
                    frame.add(new JLabel("No Vertex Selected."));
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            } else {
                JFrame frame = new JFrame("Clicked");
                frame.setSize(400, 100);
                frame.add(new JLabel("No Such Vertex."));
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        } else {
            JFrame frame = new JFrame("Clicked");
            frame.setSize(400, 100);
            frame.add(new JLabel("Enter a vertex in the form V and then Number e.g V1 or V12."));
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
