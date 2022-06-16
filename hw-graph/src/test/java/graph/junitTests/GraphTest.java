package graph.junitTests;

import graph.DirectedLabeledGraph;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.Timeout;
/**
 * GraphTest is a glassbox test of the DirectedLabeledGraph class.
 */
public class GraphTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10);
    // 10 seconds max per method tested
    private static DirectedLabeledGraph<String, String> g0 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g1 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g11 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g12 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g2 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g21 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g22 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph<String, String> g23 = new DirectedLabeledGraph<>();
    private static DirectedLabeledGraph.Node<String> n1 = new DirectedLabeledGraph.Node<>("1");
    private static DirectedLabeledGraph.Node<String> n11 = new DirectedLabeledGraph.Node<>("1");
    private static DirectedLabeledGraph.Node<String> n12 = new DirectedLabeledGraph.Node<>("1");
    private static DirectedLabeledGraph.Node<String> n2 = new DirectedLabeledGraph.Node<>("2");
    private static DirectedLabeledGraph.Node<String> n3 = new DirectedLabeledGraph.Node<>("3");
    private static  DirectedLabeledGraph.Edge<String, String> e1 = new DirectedLabeledGraph.Edge<>(n1, n1, "1");
    private static  DirectedLabeledGraph.Edge<String, String> e11 = new DirectedLabeledGraph.Edge<>(n1, n1, "1");
    private static DirectedLabeledGraph.Edge<String, String> e12 = new DirectedLabeledGraph.Edge<>(n1, n1, "1");
    private static DirectedLabeledGraph.Edge<String, String> e2 = new DirectedLabeledGraph.Edge<>(n1, n1, "2");
    private static DirectedLabeledGraph.Edge<String, String> e3 = new DirectedLabeledGraph.Edge<>(n1, n2, "1");
    private static DirectedLabeledGraph.Edge<String, String> e4 = new DirectedLabeledGraph.Edge<>(n2, n1, "1");
    private static DirectedLabeledGraph.Edge<String, String> e5 = new DirectedLabeledGraph.Edge<>(n2, n2, "3");
    private static DirectedLabeledGraph.Edge<String, String> e6 = new DirectedLabeledGraph.Edge<>(n3, n2, "3");




    @BeforeClass
    public static void m() {
        g1.addNode(n1);

        g11.addNode(n1);
        g11.addEdge(e1);

        g12.addNode(n1);
        g12.addEdge(e1);
        g12.addEdge(e2);

        g2.addNode(n1);
        g2.addNode(n2);

        g21.addNode(n1);
        g21.addNode(n2);
        g21.addEdge(e3);

        g22.addNode(n1);
        g22.addNode(n2);
        g22.addEdge(e3);
        g22.addEdge(e4);

        g23.addNode(n1);
        g23.addNode(n2);
        g23.addEdge(e3);
        g23.addEdge(e4);
        g23.addEdge(e5);
    }


    /** Tests equals method for the node inner class */
    @Test
    public void testNodeEquals() {
        // reflexive
        assertEquals(n1, n1);
        // symmetric
        assertEquals(n11, n1);
        assertEquals(n1, n11);
        // transitive
        assertEquals(n1, n11);
        assertEquals(n11, n12);
        assertEquals(n12, n1);
        // symmetric
        assertNotEquals(null, n1);

        // consistent
        assertEquals(n1, n1);

        // check inequality
        assertNotEquals(n1, n2);
        assertNotEquals(n1, n3);
        assertNotEquals(n2, n3);
    }

    /** Tests hashcode method for the node inner class */
    @Test
    public void testNodeHashCode() {
        assertEquals(n1.hashCode(), n1.hashCode());
        assertEquals(n1.hashCode(), n11.hashCode());
        assertNotEquals(n1.hashCode(), n2.hashCode());
        assertNotEquals(n1.hashCode(), n3.hashCode());
    }

    /** Tests getValue method for the Node inner class */
    @Test
    public void testGetValue() {
        assertEquals(n1.getValue(), "1");
        assertEquals(n11.getValue(), "1");
        assertEquals(n2.getValue(), "2");
        assertEquals(n3.getValue(), "3");
    }


    /** Tests equal method for the Edge inner class */
    @Test
    public void testEdgeEquals() {
        // reflexive
        assertEquals(e1, e1);
        // symmetric
        assertEquals(e11, e1);
        assertEquals(e1, e11);
        // transitive
        assertEquals(e1, e11);
        assertEquals(e11, e12);
        assertEquals(e12, e1);
        // unsymmetric
        assertNotEquals(null, e1);
        // consistent
        assertEquals(e1, e1);
        // check inequality
        assertNotEquals(e1, e2);
        assertNotEquals(e1, e3);
        assertNotEquals(e2, e3);
    }

    /** Tests hashNode method for the Edge inner class */
    @Test
    public void testEdgeHashCode() {
        assertEquals(e1.hashCode(), e1.hashCode());
        assertEquals(e1.hashCode(), e11.hashCode());
        assertNotEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1.hashCode(), e3.hashCode());
        assertNotEquals(e3.hashCode(), e4.hashCode());

    }

    /** Tests getParent method for the Edge inner class */
    @Test
    public void testGetParent() {
        assertEquals(e1.getParent(), n1);
        assertEquals(e4.getParent(), n2);
        assertEquals(e6.getParent(), n3);
    }

    /** Tests getChild method for the Edge inner class */
    @Test
    public void testGetChild() {
        assertEquals(e1.getChild(), n1);
        assertEquals(e5.getChild(), n2);
    }

    /** Tests getLabel method for the Edge inner class */
    @Test
    public void testGetLabel() {
        assertEquals(e1.getLabel(), "1");
        assertEquals(e11.getLabel(), "1");
        assertEquals(e2.getLabel(), "2");
        assertEquals(e5.getLabel(), "3");
    }

    /** Tests containNode method for the graph class */
    @Test
    public void testNodeContain() {
        assertFalse(g0.containNode(n1));
        assertFalse(g0.containNode(n2));
        assertTrue(g1.containNode(n1));
        assertFalse(g1.containNode(n2));
        assertTrue(g11.containNode(n1));
        assertTrue(g2.containNode(n1));
        assertTrue(g2.containNode(n2));
        assertTrue(g21.containNode(n1));
    }

    /** Tests containEdge method for the graph class */
    @Test
    public void testEdgeContain() {
        assertFalse(g0.containEdge(e1));
        assertFalse(g1.containEdge(e1));
        assertTrue(g11.containEdge(e1));
        assertTrue(g12.containEdge(e1));
        assertTrue(g12.containEdge(e2));
        assertTrue(g21.containEdge(e3));
        assertTrue(g22.containEdge(e3));
        assertTrue(g22.containEdge(e4));
        assertTrue(g23.containEdge(e3));
        assertTrue(g23.containEdge(e4));
        assertTrue(g23.containEdge(e5));
    }

    /** Tests nodeNum for the graph class */
    @Test
    public void testNodeNum() {
        assertEquals(g0.nodeNum(), 0);
        assertEquals(g1.nodeNum(), 1);
        assertEquals(g2.nodeNum(), 2);
        assertEquals(g12.nodeNum(), 1);
        assertEquals(g21.nodeNum(), 2);
    }

    /** Tests edgeNum method for the graph class */
    @Test
    public void testEdgeNum() {
        assertEquals(g0.edgeNum(), 0);
        assertEquals(g1.edgeNum(), 0);
        assertEquals(g11.edgeNum(), 1);
        assertEquals(g12.edgeNum(), 2);
        assertEquals(g21.edgeNum(), 1);
        assertEquals(g22.edgeNum(), 2);
        assertEquals(g23.edgeNum(), 3);
    }

    /** Tests if addEdge for edge cases when the input contains node that doesn't exist */
    @Test
    public void testAddingWrongEdge() {
        g0.addEdge(e6);
        assertFalse(g0.containEdge(e6));
        g12.addEdge(e6);
        assertFalse(g12.containEdge(e6));
        g22.addEdge(e6);
        assertFalse(g22.containEdge(e6));
        g23.addEdge(e6);
        assertFalse(g23.containEdge(e6));
    }
}
