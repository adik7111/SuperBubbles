package dagTests;

import dag.Vertex;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VertexTest {

    private Vertex vertex1;
    private Vertex vertex2;
    private Vertex vertex3;
    private Vertex vertex4;
    private Vertex vertex5;
    private Vertex vertex6;
    private Vertex vertex7;
    private Vertex vertex8;

    @Before
    public void setUp() throws Exception {
        vertex1 = new Vertex(1); vertex2 = new Vertex(2);
        vertex3 = new Vertex(3); vertex4 = new Vertex(4);
        vertex5 = new Vertex(5); vertex6 = new Vertex(6);
        vertex7 = new Vertex(7); vertex8 = new Vertex(8);
    }

    @Test
    public void correctVertexInitialisationWithNoParentsAndChildren() {
        Assert.assertTrue(vertex1.getParents().isEmpty());
        Assert.assertTrue(vertex1.getChildren().isEmpty());
        Assert.assertTrue(vertex2.getParents().isEmpty());
        Assert.assertTrue(vertex2.getChildren().isEmpty());
        Assert.assertTrue(vertex3.getParents().isEmpty());
        Assert.assertTrue(vertex3.getChildren().isEmpty());
        Assert.assertTrue(vertex4.getParents().isEmpty());
        Assert.assertTrue(vertex4.getChildren().isEmpty());
        Assert.assertTrue(vertex5.getParents().isEmpty());
        Assert.assertTrue(vertex5.getChildren().isEmpty());
        Assert.assertTrue(vertex6.getParents().isEmpty());
        Assert.assertTrue(vertex6.getChildren().isEmpty());
        Assert.assertTrue(vertex7.getParents().isEmpty());
        Assert.assertTrue(vertex7.getChildren().isEmpty());
        Assert.assertTrue(vertex8.getParents().isEmpty());
        Assert.assertTrue(vertex8.getChildren().isEmpty());
    }

    @Test
    public void correctRepresentationOfVertexAsString() {
        Assert.assertThat(vertex1.toString(), CoreMatchers.is("V1"));
        Assert.assertThat(vertex2.toString(), CoreMatchers.is("V2"));
        Assert.assertThat(vertex3.toString(), CoreMatchers.is("V3"));
        Assert.assertThat(vertex4.toString(), CoreMatchers.is("V4"));
        Assert.assertThat(vertex5.toString(), CoreMatchers.is("V5"));
        Assert.assertThat(vertex6.toString(), CoreMatchers.is("V6"));
        Assert.assertThat(vertex7.toString(), CoreMatchers.is("V7"));
        Assert.assertThat(vertex8.toString(), CoreMatchers.is("V8"));
    }

    @Test
    public void correctlyAddAParentVertex() {
        vertex2.addParent(vertex1);
        vertex3.addParent(vertex1);
        vertex3.addParent(vertex2);

        List<Vertex> vertex2Parents = new ArrayList<>();
        vertex2Parents.add(vertex1);
        List<Vertex> vertex3Parents = new ArrayList<>();
        vertex3Parents.add(vertex1);
        vertex3Parents.add(vertex2);

        Assert.assertEquals(vertex2.getParents(), vertex2Parents);
        Assert.assertEquals(vertex3.getParents(), vertex3Parents);
    }

    @Test
    public void correctlyAddAChildVertex() {
        vertex4.addChild(vertex6);
        vertex5.addChild(vertex7);
        vertex5.addChild(vertex8);

        List<Vertex> vertex4Children = new ArrayList<>();
        vertex4Children.add(vertex6);
        List<Vertex> vertex5Children = new ArrayList<>();
        vertex5Children.add(vertex7);
        vertex5Children.add(vertex8);

        Assert.assertEquals(vertex4.getChildren(), vertex4Children);
        Assert.assertEquals(vertex5.getChildren(), vertex5Children);
    }

    @Test
    public void correctlyRemoveParentVertex() {
        vertex3.addParent(vertex1);
        vertex3.addParent(vertex2);
        List<Vertex> vertex3Parents = new ArrayList<>();
        vertex3Parents.add(vertex2);

        Assert.assertTrue(vertex3.removeParent(vertex1));
        Assert.assertFalse(vertex3.removeParent(vertex8));
        Assert.assertEquals(vertex3Parents, vertex3.getParents());
    }

    @Test
    public void correctlyRemoveChildVertex() {
        vertex6.addChild(vertex7);
        vertex6.addChild(vertex8);
        List<Vertex> vertex6Children = new ArrayList<>();
        vertex6Children.add(vertex8);

        Assert.assertTrue(vertex6.removeChild(vertex7));
        Assert.assertFalse(vertex6.removeChild(vertex5));
        Assert.assertEquals(vertex6Children, vertex6.getChildren());
    }

    @Test
    public void correctlyAddMultipleParentVertexes() {
        List<Vertex> vertexesToAdd = new ArrayList<>();
        vertexesToAdd.add(vertex4);
        vertexesToAdd.add(vertex5);
        vertexesToAdd.add(vertex6);
        vertex3.addParents(vertexesToAdd);

        Assert.assertTrue(vertex3.getParents().size() == vertexesToAdd.size());
    }

    @Test
    public void correctlyAddMultipleChildrenVertexes() {
        List<Vertex> vertexesToAdd = new ArrayList<>();
        vertexesToAdd.add(vertex4);
        vertexesToAdd.add(vertex5);
        vertexesToAdd.add(vertex6);
        vertex3.addChildren(vertexesToAdd);

        Assert.assertTrue(vertex3.getChildren().size() == vertexesToAdd.size());
    }

    @Test
    public void correctChangeOfVertexId() {
        vertex6.setId(10);

        Assert.assertThat(vertex6.toString(), CoreMatchers.is("V10"));
    }

    @Test
    public void VertexIsEqualToAnotherVertexIfTheyHaveTheSameID() {
        Vertex v1Clone = new Vertex(1);
        Assert.assertEquals(vertex1, v1Clone);
        Assert.assertNotEquals(vertex1, vertex2);
    }
}
