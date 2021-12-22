package superbubbleTests;

import dag.Vertex;
import org.junit.Assert;
import org.junit.Test;
import superbubble.Candidate;

public class CandidateTest {

    private Candidate candidate;
    private Vertex v1;

    @Test
    public void CandidateShouldTakeAVertexAndStringForConstructor() throws Exception {
        v1 = new Vertex(1);
        candidate = new Candidate(v1, "Entrance");
        Assert.assertEquals(v1, candidate.getVertex());
        Assert.assertEquals("Entrance", candidate.getType());
    }

    @Test (expected = IllegalArgumentException.class)
    public void ConstructorShouldOnlyTakeEntranceOrExitAsAString() throws Exception {
        candidate = new Candidate(v1, "wrong");
    }

    @Test
    public void CandidatesEqualEachOtherIfVertexIDAndTypeOfCandidateIsTheSame() throws Exception {
        v1 = new Vertex(1);
        candidate = new Candidate(v1, "Entrance");
        Candidate candidate2 = new Candidate(v1, "Exit");
        Candidate candidate3 = new Candidate(v1, "Entrance");
        Vertex v2 = new Vertex(2);
        Candidate candidate4 = new Candidate(v2, "Entrance");

        Assert.assertEquals(candidate, candidate3);
        Assert.assertNotEquals(candidate, candidate2);
        Assert.assertNotEquals(candidate, candidate4);
    }
}
