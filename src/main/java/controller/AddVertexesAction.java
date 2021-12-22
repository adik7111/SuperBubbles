package controller;

import dag.Vertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Add vertexes action is utilised by the add vertexes button
 * and adds a number of vertexes to the graph input by the
 * user.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class AddVertexesAction implements ActionListener {

    private JTextField label;
    private DefaultListModel<Vertex> list;
    private UpdateGraphInfo ug;
    private JTextArea graphInfo;

    public AddVertexesAction(DefaultListModel<Vertex> list, JTextField label, JTextArea graphInfo) {
        this.list = list;
        this.label = label;
        this.graphInfo = graphInfo;
    }

    /**
     * Once the button is clicked, it checks that the
     * corresponding label is a number, and if so adds
     * that many vertexes into the vertexes list. If the
     * input in the label is not a number then it returns
     * an error message.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String written = label.getText();

        if(written.matches("[0-9]+")) {
            int amountOfVertexesToAdd = Integer.parseInt(written);
            if (list.isEmpty()) {
                for (int i = 1; i <= amountOfVertexesToAdd; i++) {
                    list.addElement(new Vertex(i));
                }
            } else {
                int lastVertex = list.getElementAt(list.getSize() - 1).getId();
                for (int i = lastVertex; i < amountOfVertexesToAdd + lastVertex; i++) {
                    list.addElement(new Vertex(i + 1));
                }
            }
            ug = new UpdateGraphInfo(list, graphInfo);
        } else {
            JFrame frame = new JFrame("Clicked");
            frame.setSize(200, 100);
            frame.add(new JLabel("You can only enter a number!"));
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
