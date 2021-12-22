package mainTests;

import dag.Vertex;
import main.Report;
import main.SuperBubblePair;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportTest {

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Vertex v4;
    SuperBubblePair sbp1;
    SuperBubblePair sbp2;
    Report rep1;

    @Before
    public void setUp() {
        v1 = new Vertex(1);
        v2 = new Vertex(2);
        v3 = new Vertex(3);
        v4 = new Vertex(4);
        sbp1 = new SuperBubblePair(v1, v2);
        sbp2 = new SuperBubblePair(v3, v4);
        rep1 = new Report();
    }

    @Test
    public void canAddSuperBubblePairsToReportAndStore() {
        List<SuperBubblePair> listExpected = new ArrayList<>(Arrays.asList(sbp1, sbp2));
        rep1.addPairToList(sbp1);
        rep1.addPairToList(sbp2);
        Assert.assertEquals(listExpected, rep1.getListOfPairs());
    }

    @Test
    public void correctReportRepresentation() {
        rep1.addPairToList(sbp1);
        rep1.addPairToList(sbp2);
        Assert.assertThat(rep1.toString(), CoreMatchers.is("Super Bubble at: {V1, V2}\nSuper Bubble" + "" +
                " at: {V3, V4}\n"));
    }
}
