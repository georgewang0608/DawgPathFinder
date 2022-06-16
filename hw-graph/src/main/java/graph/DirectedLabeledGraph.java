package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a directed graph with labelled edges.
 * It has edges connecting nodes and representing a path going
 * through mutiple nodes with mutiple edges
 * It stores a list of edges and a list of nodes
 *
 * @param <N> Type for Node value
 * @param <E> Type for Edge label
 *
 * Abstract Invariant:
 *  All edges and nodes are valid with no null values, no two edges with the same parent and child
 *  share the same edge label and no two nodes should have the same value
 *
 */
public class DirectedLabeledGraph<N, E> {
    public static final boolean DEBUG = false;
//  edges and nodes are stored in set
//
//  RI: nodes and edges != null, edges and nodes contain no null value
//  AF(this) = A directed labelled graph with edges and nodes list
//             edges.toArray(): edges[0], edges[1], ... edges[edges.size() - 1]
//             nodes.toArray(): nodes[0], nodes[1], ... nodes[nodes.size() - 1]
    private Set<Edge<N, E>> edges;
    private Set<Node<N>> nodes;

    /**
     * This inner class represents a node in the graph. It will be connected by edges in the graph
     * and store a string value
     *
     * @param <N> Type for Node value
     *
     * Abstract Invariant:
     *  A node should store a string value
     */
    public static class Node<N> {

//      The value will be stored as a String
//      RI: value != null
//      AF(this) = node with label value: Node(value)
        private final N value;
        /**
         * Creates a single node with the input value
         * @param value Type represents the value of the node
         * @spec.requires value != null
         * @spec.effects this = Node(value)
         */
        public Node(N value) {
            this.value = value;
            checkRep();
        }

        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (!(obj instanceof Node<?>)) {
                return false;
            }
            Node<?> other = (Node<?>) obj;
            checkRep();
            return this.value.equals(other.value);
        }

        @Override
        public int hashCode() {
            checkRep();
            return this.value.hashCode();
        }

        /**
         * Get the value of this Node
         * @return the value of the Node
         */
        public N getValue() {
            checkRep();
            return this.value;
        }

        private void checkRep() {
            assert this.value != null : "this.value is null";
        }
    }

    /**
     * This inner class represents an immutable edge in the graph. It connects two nodes of the graph representing
     * a path between the node.
     * It stores a label which represents the weight of the edge,
     * A parent node which that the represents where the edge comes from
     * A child node  that represents where the edge goes to
     *
     * @param <E> Type of the Node value
     * @param <N> Tye of the Edge label value
     *
     * Abstract Invariant:
     *  An edge should contain two valid nodes and a label of string type
     */
    public static class Edge<N, E> {
//      Parent and child are stored as nodes and label is stored as String
//      RI: parent and child and label != null
//      AF(this) = Edge with parent as parent node and child as child node and label as the weight
        private final Node<N> parent;
        private final Node<N> child;
        private final E label;
        /**
         * Creates a single edge connecting two nodes with the input label
         * @param parent Node that represents the start point of the edge
         * @param child Node that represents the end point of the edge
         * @param label Type that represents the weight of the edge
         * @spec.requires parent != null, child != null, label != null
         * @spec.effects this = Edge(parent, child, label)
         */
        public Edge(Node<N> parent, Node<N> child, E label) {
            this.parent = parent;
            this.child = child;
            this.label = label;
            checkRep();
        }

        @Override
        public boolean equals(Object obj) {
            checkRep();
            if (!(obj instanceof Edge<?,?>)) {
                return false;
            }
            Edge<?,?> other = (Edge<?,?>) obj;
            checkRep();
            return this.parent.equals(other.parent) && this.child.equals(other.child) && this.label.equals(other.label);
        }

        @Override
        public int hashCode() {
            checkRep();
            return this.parent.hashCode() * this.parent.hashCode() * this.child.hashCode()
                    + this.label.hashCode();
        }

        /**
         * Get the parent node of this Edge
         * @return the parent node of the Edge
         */
        public Node<N> getParent() {
            checkRep();
            return this.parent;
        }

        /**
         * Get the child node of this Edge
         * @return the parent node of the Edge
         */
        public Node<N> getChild() {
            checkRep();
            return this.child;
        }

        /**
         * Get the label of this Edge
         * @return the label of the Edge
         */
        public E getLabel() {
            checkRep();
            return this.label;
        }

        private void checkRep() {
            assert this.parent != null : "this.parent is null";
            assert this.child != null : "this.child is null";
            assert this.label != null : "this.label is null";
        }
    }

    /**
     * Constructs a new empty graph
     * @spec.effects this = DirectedLabeledGraph()
     */
    public DirectedLabeledGraph() {
        edges = new HashSet<>();
        nodes = new HashSet<>();
        checkRep();
    }

    /**
     * Add a new node to the graph
     * @param newNode Node to be added to graph
     * @spec.requires newNode != null
     * @spec.modifies this
     * @spec.effects add the new node to the node list stored in the graph
     *               if the node with same value already exist do nothing
     */
    public void addNode(Node<N> newNode) {
        checkRep();
        nodes.add(newNode);
        checkRep();
    }

    /**
     * add a new edge to the graph
     * @param newEdge Edge to be added to graph
     * @spec.requires newEdge != null
     * @spec.modifies this
     * @spec.effects add a new edge to the edge list stored in the graph
     *               if the edge with same parent, child and label already exist then do nothing
     *               if parent or child are not in the graph then do nothing
     */
    public void addEdge(Edge<N,E> newEdge) {
        checkRep();
        if (nodes.contains(newEdge.parent) && nodes.contains(newEdge.child)) {
            edges.add(newEdge);
        }
        checkRep();
    }

    /**
     * check if the graph contains a node with the input value
     * @param check Node the client try to check in graph
     * @spec.requires check != null
     * @return true if the node exist
     *         false otherwise
     */
    public boolean containNode(Node<N> check) {
        checkRep();
        return nodes.contains(check);
    }

    /**
     * check if the graph contains the input edge
     * @param check Edge that the clients trying to search for
     * @spec.requires check != null
     * @return true if the edge exist
     *         false otherwise
     * @throws IllegalArgumentException
     *         if check is null.
     */
    public boolean containEdge(Edge<N,E> check) {
        checkRep();
        return edges.contains(check);
    }

    /**
     * return the number of node in the graph
     * @return the length of the node list in the graph
     */
    public int nodeNum() {
        checkRep();
        return nodes.size();
    }

    /**
     * return the number of edges in the graph
     * @return the length of the edge list in the graph
     */
    public int edgeNum() {
        checkRep();
        return edges.size();
    }

    /**
     * return all the outgoing edges of the input node
     * @param parent Node whose outgoing edges will be returned
     * @spec.requires parent != null
     * @return a list of edges
     */
    public List<Edge<N,E>> listChildren(Node<N> parent) {
        checkRep();
        List<Edge<N,E>> children = new ArrayList<>();
        for (Edge<N,E> i : edges) {
            if (i.parent.equals(parent)) {
                children.add(i);
            }
        }
        checkRep();
        return children;
    }

    /**
     * return all the nodes in the graph
     * @return a list of nodes in the graph
     */
    public List<Node<N>> listNode() {
        checkRep();
        List<Node<N>> ret = new ArrayList<>();
        for (Node<N> i : nodes) {
            ret.add(i);
        }
        checkRep();
        return ret;
    }

    private void checkRep() {
        assert this.nodes != null : "this.nodes is null";
        assert this.edges != null : "this.edges is null";
        if (DEBUG) {
            assert !nodes.contains(null) : "this.nodes contains null";
            assert !edges.contains(null) : "this.edges contains null";
        }
    }
}
