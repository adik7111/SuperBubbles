package superbubbleTests;

import dag.DirectedAcyclicGraph;
import dag.TopologicalSorter;
import dag.Vertex;
import main.SuperBubblePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import superbubble.Candidate;
import superbubble.SuperBubble;

import java.util.*;

public class SuperBubbleTest {

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
    private Vertex vertex10;
    private Vertex vertex11;
    private Vertex vertex12;
    private Vertex vertex13;
    private Vertex vertex14;
    private Vertex vertex15;
    private DirectedAcyclicGraph graph;
    private SuperBubble sb;
    private List dll;

    @Before
    public void setUp() throws Exception {
        topologicalSorter = new TopologicalSorter();
        sb = new SuperBubble();
        dll = Collections.synchronizedList(new LinkedList<Candidate>());
        vertex1 = new Vertex(1);
        vertex2 = new Vertex(2); vertex3 = new Vertex(3);
        vertex4 = new Vertex(4); vertex5 = new Vertex(5);
        vertex6 = new Vertex(6); vertex7 = new Vertex(7);
        vertex8 = new Vertex(8); vertex9 = new Vertex(9);
        vertex10 = new Vertex(10); vertex11 = new Vertex(11);
        vertex12 = new Vertex(12); vertex13 = new Vertex(13);
        vertex14 = new Vertex(14); vertex15 = new Vertex(15);

        vertex1.addChild(vertex3); vertex1.addChild(vertex2);
        vertex2.addChild(vertex3);
        vertex3.addChild(vertex4); vertex3.addChild(vertex5); vertex3.addChild(vertex11);
        vertex4.addChild(vertex8);
        vertex5.addChild(vertex6); vertex5.addChild(vertex9);
        vertex6.addChild(vertex7); vertex6.addChild(vertex10);
        vertex7.addChild(vertex8);
        vertex8.addChild(vertex13); vertex8.addChild(vertex14);
        vertex9.addChild(vertex10);
        vertex10.addChild(vertex7);
        vertex11.addChild(vertex12);
        vertex12.addChild(vertex8);
        vertex13.addChild(vertex14); vertex13.addChild(vertex15);
        vertex15.addChild(vertex14);

        List<Vertex> vertexes = new ArrayList<>(Arrays.asList(vertex1,
                vertex2, vertex3, vertex4, vertex5, vertex6, vertex7, vertex8, vertex9,
                vertex10, vertex11, vertex12, vertex13, vertex14, vertex15));

        graph = new DirectedAcyclicGraph(vertex1, vertexes);
    }

    @Test
    public void EntranceMethodReturnsTrueForCorrectEntranceCandidates() {
        Assert.assertTrue(sb.entrance(vertex1)); Assert.assertTrue(sb.entrance(vertex3));
        Assert.assertTrue(sb.entrance(vertex5)); Assert.assertTrue(sb.entrance(vertex8));
        Assert.assertTrue(sb.entrance(vertex11)); Assert.assertTrue(sb.entrance(vertex13));

        Assert.assertFalse(sb.entrance(vertex2)); Assert.assertFalse(sb.entrance(vertex4));
        Assert.assertFalse(sb.entrance(vertex6)); Assert.assertFalse(sb.entrance(vertex7));
        Assert.assertFalse(sb.entrance(vertex9)); Assert.assertFalse(sb.entrance(vertex10));
        Assert.assertFalse(sb.entrance(vertex12)); Assert.assertFalse(sb.entrance(vertex14));
        Assert.assertFalse(sb.entrance(vertex15));
    }

    @Test
    public void ExitMethodReturnsTrueForCorrectExitCandidates() {
        Assert.assertTrue(sb.exit(vertex3)); Assert.assertTrue(sb.exit(vertex7));
        Assert.assertTrue(sb.exit(vertex8)); Assert.assertTrue(sb.exit(vertex10));
        Assert.assertTrue(sb.exit(vertex12)); Assert.assertTrue(sb.exit(vertex14));

        Assert.assertFalse(sb.exit(vertex1)); Assert.assertFalse(sb.exit(vertex2));
        Assert.assertFalse(sb.exit(vertex4)); Assert.assertFalse(sb.exit(vertex5));
        Assert.assertFalse(sb.exit(vertex6)); Assert.assertFalse(sb.exit(vertex9));
        Assert.assertFalse(sb.exit(vertex11)); Assert.assertFalse(sb.exit(vertex13));
        Assert.assertFalse(sb.exit(vertex15));

    }

    @Test
    public void InsertEntranceMethodCorrectlyInsertsVertexIntoCandidatesListAndLabelsAsEntrance() {
        sb.insertEntrance(vertex1);
        Candidate candidate = new Candidate(vertex1, "Entrance");
        dll.add(candidate);
        Assert.assertEquals(dll, sb.getCandidates());
    }

    @Test
    public void InsertExitMethodCorrectlyInsertsVertexIntoCandidatesListAndLabelsAsExit() {
        sb.insertExit(vertex1);
        Candidate candidate = new Candidate(vertex1, "Exit");
        dll.add(candidate);
        Assert.assertEquals(dll, sb.getCandidates());
    }

    @Test
    public void HeadAndTailMethodsRespectivelyReturnTheFirstAndLastElementInCandidates() {
        sb.insertEntrance(vertex1);
        sb.insertExit(vertex3);
        sb.insertEntrance(vertex5);
        sb.insertExit(vertex7);
        Candidate c1 = new Candidate(vertex1, "Entrance");
        Candidate c2 = new Candidate(vertex7, "Exit");
        Assert.assertEquals(sb.head(), c1);
        Assert.assertEquals(sb.tail(), c2);
    }

    @Test
    public void DeleteTailMethodCorrectlyDeletesTheLastElementInTheCandidatesList() {
        sb.insertEntrance(vertex1);
        sb.insertExit(vertex3);
        sb.insertEntrance(vertex5);
        sb.insertExit(vertex7);
        Candidate c1 = new Candidate(vertex1, "Entrance");
        Candidate c2 = new Candidate(vertex3, "Exit");
        Candidate c3 = new Candidate(vertex5, "Entrance");
        dll.add(c1); dll.add(c2); dll.add(c3);
        sb.deleteTail();
        Assert.assertEquals(dll, sb.getCandidates());
    }

    @Test
    public void NextMethodCorrectlyReturnsTheNextCandidateInCandidatesFromGivenVertex() {
        sb.insertEntrance(vertex1);
        sb.insertExit(vertex3);
        sb.insertEntrance(vertex5);
        sb.insertExit(vertex7);
        Candidate c1 = new Candidate(vertex1, "Entrance");
        Candidate c2 = new Candidate(vertex3, "Exit");
        Candidate c3 = new Candidate(vertex5, "Entrance");
        Candidate c4 = new Candidate(vertex7, "Exit");

        Assert.assertEquals(c2, sb.next(c1));
        Assert.assertEquals(c3, sb.next(c2));
        Assert.assertEquals(c4, sb.next(c3));
        Assert.assertNull(sb.next(c4));
    }

    @Test
    public void SuperBubbleConstructorTakesGraphAsParameterAndTopologicallySortsTheGraph() {
        SuperBubble superBubbleWithGraph = new SuperBubble(graph);

        List<Vertex> expectedOutput = new ArrayList<>(Arrays.asList(vertex1, vertex2, vertex3, vertex11, vertex12,
                vertex5, vertex9, vertex6, vertex10, vertex7, vertex4, vertex8, vertex13, vertex15, vertex14));

        Assert.assertEquals(expectedOutput, superBubbleWithGraph.getOrdD());
    }

    @Test
    public void VertexMethodTakesAsInputAnIntegerAndReturnsTheCorrespondingVertexFromOrdD() {
        SuperBubble superBubbleWithGraph = new SuperBubble(graph);

        Assert.assertEquals(vertex11, superBubbleWithGraph.vertex(3));
        Assert.assertEquals(vertex1, superBubbleWithGraph.vertex(0));
        Assert.assertEquals(vertex7, superBubbleWithGraph.vertex(9));
    }

    @Test
    public void WhenSuperBubbleInitialisedOutParentArrayCorrectlyPreComputed() {
        SuperBubble sp = new SuperBubble(graph);

        List<Integer> expectedOutput = new ArrayList<>(Arrays.asList(null, 1, 1, 3, 4, 3,
                6, 6, 7, 8, 3, 5, 12, 13, 12));

        Assert.assertEquals(expectedOutput, sp.getOutParent());
    }

    @Test
    public void WhenSuperBubbleInitialisedOutChildArrayCorrectlyPreComputed() {
        SuperBubble sp = new SuperBubble(graph);

        List<Integer> expectedOutput = new ArrayList<>(Arrays.asList(3, 3, 11, 5, 12, 8,
                9, 10, 10, 12, 12, 15, 15, 15, null));

        Assert.assertEquals(expectedOutput, sp.getOutChild());
    }

    @Test
    public void CorrectArrayInitialisationForIndexesOfEachVertexOrdDTopological() {
        SuperBubble sp = new SuperBubble(graph);

        List<Integer> expectedOutput = new ArrayList<>(Arrays.asList(1, 2, 3, 11, 6, 8, 10,
                12, 7, 9, 4, 5, 13, 15, 14));

        Assert.assertEquals(expectedOutput, sp.getOrdDTopological());
    }

    /*@Test
    public void SeeHowCandidatesListIsSetUp() {
        SuperBubble sp = new SuperBubble(graph);
        List<Candidate> test = sp.getCandidates();
        Iterator<Candidate> it = test.iterator();
        System.out.println("candidates");
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
        List<Candidate> aE = sp.getAlternativeEntrance();
        List<Candidate> pE = sp.getPreviousEntrance();
        System.out.println("alternativeEntrance");
        for (int i = 0; i < aE.size(); i++) {
            if (aE.get(i) == null) {
                System.out.println("Null");
            } else {
                System.out.println(aE.get(i).toString());
            }
        }
        System.out.println("previousEntrance:");
        for (int i = 0; i < pE.size(); i++) {
            if (pE.get(i) == null) {
                System.out.println("Null");
            } else {
                System.out.println(pE.get(i).toString());
            }
        }
        List<Integer> ordD = sp.getOrdDTopological();
        System.out.println("OrdDTopological");
        for (int i = 0; i < ordD.size(); i++) {
            System.out.println("Index " + i + " value: " + ordD.get(i));
        }
        List<Vertex> ord = sp.getOrdD();
        System.out.println("OrdD");
        for (int i = 0; i < ord.size(); i++) {
            System.out.println("Index " + i + " value: " + ord.get(i).toString());
        }
    }*/

    @Test
    public void AfterSupperBubbleCreatedCandidatesListSetUpCorrectly() {
        SuperBubble sp = new SuperBubble(graph);
        Candidate c1 = new Candidate(vertex1, "Entrance");
        Candidate c2 = new Candidate(vertex3, "Exit");
        Candidate c3 = new Candidate(vertex3, "Entrance");
        Candidate c4 = new Candidate(vertex11, "Entrance");
        Candidate c5 = new Candidate(vertex12, "Exit");
        Candidate c6 = new Candidate(vertex5, "Entrance");
        Candidate c7 = new Candidate(vertex10, "Exit");
        Candidate c8 = new Candidate(vertex7, "Exit");
        Candidate c9 = new Candidate(vertex8, "Exit");
        Candidate c10 = new Candidate(vertex8, "Entrance");
        Candidate c11 = new Candidate(vertex13, "Entrance");
        Candidate c12 = new Candidate(vertex14, "Exit");
        List<Candidate> expected = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6,
                c7, c8, c9, c10, c11, c12));

        Assert.assertEquals(expected, sp.getCandidatesWhenFirstComputed());
    }

    @Test
    public void RangeMinMethodWorksCorrectly() {
        SuperBubble sp = new SuperBubble(graph);
        List<Integer> ordD = sp.getOrdDTopological();
        Assert.assertEquals(3, sp.rangeMin(sp.getOutParent(), ordD.get(4) + 1, ordD.get(7)));
    }

    @Test
    public void RangeMaxMethodWorksCorrectly() {
        SuperBubble sp = new SuperBubble(graph);
        List<Integer> ordD = sp.getOrdDTopological();
        Assert.assertEquals(8, sp.rangeMax(sp.getOutParent(), ordD.get(4) + 1, ordD.get(7)));
    }

    @Test
    public void ValidateSuperBubbleMethodWorksCorrectly() {
        SuperBubble sp = new SuperBubble(graph);
        Candidate expected5and8 = new Candidate(new Vertex(3), "Entrance");
        Candidate validation5and8 = sp.validateSuperBubble(5, 8);
        Candidate expected13and14 = new Candidate(new Vertex(8), "Entrance");
        Candidate validation13and14 = sp.validateSuperBubble(13, 14);
        Assert.assertEquals(expected5and8, validation5and8);
        Assert.assertEquals(expected13and14, validation13and14);
    }

    @Test
    public void superBubbleAlgorithmCorrectlyStoresSuperBubbleReports() {
        SuperBubble sp = new SuperBubble(graph);
        SuperBubblePair pair1 = new SuperBubblePair(vertex8, vertex14);
        SuperBubblePair pair2 = new SuperBubblePair(vertex3, vertex8);
        SuperBubblePair pair3 = new SuperBubblePair(vertex5, vertex7);
        SuperBubblePair pair4 = new SuperBubblePair(vertex11, vertex12);
        SuperBubblePair pair5 = new SuperBubblePair(vertex1, vertex3);
        List<SuperBubblePair> expected = new ArrayList<>(Arrays.asList(pair1, pair2, pair3, pair4, pair5));

        Assert.assertEquals(expected, sp.getReport().getListOfPairs());
    }
}
