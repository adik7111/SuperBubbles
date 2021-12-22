package dagTests;

import dag.DirectedAcyclicGraph;
import dag.TopologicalSorter;
import dag.Vertex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopologicalSorterTest {

    private TopologicalSorter topologicalSorter;
    private Vertex vertex1;
    private Vertex vertex2;
    private Vertex vertex3;
    private Vertex vertex4;
    private Vertex vertex5;
    private Vertex vertex6;
    private Vertex vertex7;
    private Vertex vertex8;
    private Vertex vertex9;
    private DirectedAcyclicGraph graph;

    @Before
    public void setUp() {
        topologicalSorter = new TopologicalSorter(); vertex1 = new Vertex(1);
        vertex2 = new Vertex(2); vertex3 = new Vertex(3);
        vertex4 = new Vertex(4); vertex5 = new Vertex(5);
        vertex6 = new Vertex(6); vertex7 = new Vertex(7);
        vertex8 = new Vertex(8); vertex9 = new Vertex(9);

        vertex1.addChild(vertex2); vertex2.addChild(vertex3); vertex2.addChild(vertex4);
        vertex3.addChild(vertex5); vertex4.addChild(vertex6); vertex5.addChild(vertex6);
        vertex6.addChild(vertex7); vertex6.addChild(vertex8); vertex7.addChild(vertex9);
        vertex7.addChild(vertex9);

        List<Vertex> vertexes = new ArrayList<>(Arrays.asList(vertex1,
                vertex2, vertex3, vertex4, vertex5, vertex6, vertex7, vertex8, vertex9));

        graph = new DirectedAcyclicGraph(vertex1, vertexes);
    }

    @Test
    public void topologicalSortCorrectlySortsGraph() {
        List<Vertex> expectedTopologicalSort = new ArrayList<>(Arrays.asList(
                vertex1, vertex2, vertex4, vertex3, vertex5, vertex6, vertex8, vertex7,
                vertex9
        ));

        Assert.assertEquals(expectedTopologicalSort, topologicalSorter.topologicalSort(graph));
    }

    @Test
    public void topologicallySortingGraphUsedInSuperbubblesPaper() {
        Vertex v1 = new Vertex(1); Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3); Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5); Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7); Vertex v8 = new Vertex(8);
        Vertex v9 = new Vertex(9); Vertex v10 = new Vertex(10);
        Vertex v11 = new Vertex(11); Vertex v12 = new Vertex(12);
        Vertex v13 = new Vertex(13); Vertex v14 = new Vertex(14);
        Vertex v15 = new Vertex(15);

        v1.addChild(v3); v1.addChild(v2);
        v2.addChild(v3);
        v3.addChild(v4); v3.addChild(v5); v3.addChild(v11);
        v4.addChild(v8);
        v5.addChild(v6); v5.addChild(v9);
        v6.addChild(v7); v6.addChild(v10);
        v7.addChild(v8);
        v8.addChild(v13); v8.addChild(v14);
        v9.addChild(v10);
        v10.addChild(v7);
        v11.addChild(v12);
        v12.addChild(v8);
        v13.addChild(v14); v13.addChild(v15);
        v15.addChild(v14);

        List<Vertex> vertexes = new ArrayList<>(Arrays.asList(
                v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15
        ));

        DirectedAcyclicGraph graph2 = new DirectedAcyclicGraph(v1, vertexes);

        List<Vertex> expectedOutput = new ArrayList<>(Arrays.asList(v1, v2, v3, v11, v12,
            v5, v9, v6, v10, v7, v4, v8, v13, v15, v14));

        Assert.assertEquals(expectedOutput, topologicalSorter.topologicalSort(graph2));
    }
}
