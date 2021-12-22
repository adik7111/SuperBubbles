package mainTests;

import dag.Vertex;
import main.SuperBubblePair;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SuperBubblePairTest {

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Vertex v4;
    SuperBubblePair sbp1;
    SuperBubblePair sbp2;

    @Before
    public void setUp() {
        v1 = new Vertex(1);
        v2 = new Vertex(2);
        v3 = new Vertex(3);
        v4 = new Vertex(4);
        sbp1 = new SuperBubblePair(v1, v2);
        sbp2 = new SuperBubblePair(v3, v4);
    }

    @Test
    public void superBubblePairShouldTakeTwoVertexesInConstructorAndStoreTheValues() {
        Assert.assertEquals(v1, sbp1.getStart());
        Assert.assertEquals(v2, sbp1.getEnd());
    }

    @Test
    public void correctRepresentationOfSuperBubblePairAsAString() {
        Assert.assertThat(sbp1.toString(), CoreMatchers.is("{V1, V2}"));
        Assert.assertThat(sbp2.toString(), CoreMatchers.is("{V3, V4}"));
    }
}
