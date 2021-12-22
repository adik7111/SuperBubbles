package controller;

import dag.DirectedAcyclicGraph;
import dag.Vertex;
import main.Report;
import superbubble.SuperBubble;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate report action utilises the superbubble algorithm
 * in order to return any superbubbles in the superbubble information
 * section.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class GenerateReportAction implements ActionListener {

    private DefaultListModel<Vertex> vertexList;
    private JTextArea reportArea;
    private DirectedAcyclicGraph dag;
    private SuperBubble sb;

    public GenerateReportAction(DefaultListModel<Vertex> vertexList, JTextArea reportArea) {
        this.vertexList = vertexList;
        this.reportArea = reportArea;
    }

    /**
     * The populate graph method takes a vertex and a list of vertex
     * from the vertexes list and places them into a graph. It then
     * calls the updateReportArea.
     *
     * @param source    Source vertex of the graph
     * @param vertexes  The rest of the vertexes belonging to the
     *                  graph
     */
    private void populateGraph(Vertex source, List<Vertex> vertexes) {
        dag = new DirectedAcyclicGraph(source, vertexes);
        updateReportArea();
    }

    /**
     * Calls the superbubble method on the previously created graph
     * in order to update the superbubble report area.
     */
    private void updateReportArea() {
        sb = new SuperBubble(dag);
        Report rep = sb.getReport();
        reportArea.append(rep.toString());
    }

    /**
     * Once the button is clicked, the vertexes list is checked. If
     * no vertexes created then an error message pops up informing this.
     * Otherwise it uses all the vertexes in the vertexes list to create
     * a graph and run the superbubble algorithm on the graph, after which
     * the output is returned to the superbubble report area.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (vertexList.isEmpty()) {
            JFrame frame = new JFrame("Clicked");
            frame.setSize(400, 100);
            frame.add(new JLabel("No Vertexes Created."));
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            Vertex source = vertexList.firstElement();
            List<Vertex> vertexes = new ArrayList<>();
            for (int i = 1; i < vertexList.size(); i++) {
                vertexes.add(vertexList.getElementAt(i));
            }
            populateGraph(source, vertexes);
        }
    }
}
