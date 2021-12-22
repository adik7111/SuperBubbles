package dagTests;

import dag.DirectedAcyclicGraph;
import dag.Vertex;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DirectedAcyclicGraphTest {

    private DirectedAcyclicGraph graph;
    private Vertex source;
    private Vertex vertex2;
    private Vertex vertex3;
    private Vertex vertex4;
    private Vertex vertex5;
    private Vertex vertex6;
    private Vertex vertex7;
    private Vertex vertex8;

    @Before
    public void setUp() throws Exception {
        source = new Vertex(1); vertex2 = new Vertex(2);
        vertex3 = new Vertex(3); vertex4 = new Vertex(4);
        vertex5 = new Vertex(5); vertex6 = new Vertex(6);
        vertex7 = new Vertex(7); vertex8 = new Vertex(8);
    }

    @Test
    public void correctInitialisationOfDirectedAcyclicGraphWithSourceVertex() {
        graph = new DirectedAcyclicGraph(source);
        Assert.assertNotNull(graph.getSource());
    }

    @Test
    public void correctInitialisationOfDirectedAcyclicGraphWithSourceAndOtherVertexes() {
        List<Vertex> vertexes = new ArrayList<>();
        vertexes.add(vertex2); vertexes.add(vertex3); vertexes.add(vertex4);
        vertexes.add(vertex5); vertexes.add(vertex6); vertexes.add(vertex7);
        vertexes.add(vertex8);
        graph = new DirectedAcyclicGraph(source, vertexes);

        Assert.assertNotNull(graph.getSource());
        Assert.assertNotNull(graph.getVertexes());

    }

    @Test
    public void correctlyAddANewVertexToTheDirectedAcyclicGraph() {
        Vertex vertex9 = new Vertex(9);
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex9);
        List<Vertex> graphShouldBe = new ArrayList<>();
        graphShouldBe.add(source);
        graphShouldBe.add(vertex9);

        Assert.assertEquals(graphShouldBe, graph.getVertexes());
    }

    @Test
    public void findASpecificVertexInTheDirectedAcyclicGraph() {
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex2); graph.addVertex(vertex3);
        graph.addVertex(vertex7); graph.addVertex(vertex8);

        Assert.assertEquals(vertex7, graph.getVertex(vertex7));
    }

    @Test
    public void returnNullIfVertexNotInDirectedAcyclicGraph() {
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex2); graph.addVertex(vertex3);
        graph.addVertex(vertex7); graph.addVertex(vertex8);

        Assert.assertNull(graph.getVertex(vertex5));
    }

    @Test
    public void returnTrueIfGraphContainsVertexOtherwiseReturnFalse() {
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex2); graph.addVertex(vertex3);
        graph.addVertex(vertex7); graph.addVertex(vertex8);

        Assert.assertTrue(graph.containsVertex(vertex2));
        Assert.assertFalse(graph.containsVertex(vertex6));
    }

    @Test
    public void returnCorrectSizeOfGraphWhichWillBeAmountOfVertexesInGraph() {
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex2); graph.addVertex(vertex3);
        graph.addVertex(vertex7); graph.addVertex(vertex8);

        Assert.assertEquals(5, graph.size());
    }

    @Test
    public void correctRepresentationOfGraphAsString() {
        graph = new DirectedAcyclicGraph(source);
        graph.addVertex(vertex2); graph.addVertex(vertex3);
        graph.addVertex(vertex7); graph.addVertex(vertex8);

        Assert.assertThat(graph.toString(), CoreMatchers.is("Graph{V1, V2, V3, V7, V8}"));
    }
}
