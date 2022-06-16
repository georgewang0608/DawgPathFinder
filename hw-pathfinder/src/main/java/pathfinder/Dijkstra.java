package pathfinder;

import graph.DirectedLabeledGraph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.util.*;

/**
 * This class represents the shortest path calculator
 * of a directed graph with labelled edges.
 */
public class Dijkstra {
    // Do not have RI or AF since this class is not an ADT

    /**
     * find the shortest path between two given nodes of the graph
     * @param graph A graph with nodes as Points and Edge as Double
     * @param start Type represents the start point of the path
     * @param end  Type represents the end point of the path
     * @spec.requires graph.containNode(start) and graph.containNode(end)
     *                start != null and end != null
     * @return Path if there exists the shortest path between the nodes
     *         null otherwise
     */
    public static Path<Point> findPath(DirectedLabeledGraph<Point, Double> graph, Point start, Point end) {
        checkRep(graph);
        Path<Point> self = new Path<>(start);
        Set<Point> finished = new HashSet<>();
        Comparator<Path<Point>> compPath = new Comparator<>() {
            @Override
            public int compare(Path<Point> a, Path<Point> b) {
                return Double.compare(a.getCost(), b.getCost());
            }
        };
        // Consturct a queue for nodes that still needs probing
        PriorityQueue<Path<Point>> active = new PriorityQueue<>(compPath);
        active.add(self);
        // Loop through all the possible nodes while constructing the path
        while (!active.isEmpty()) {
            Path<Point> minPath = active.poll();
            Point minDest = minPath.getEnd();
            if (minDest.equals(end)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
            // Add the neighbouring nodes to the queue
            DirectedLabeledGraph.Node<Point> node = new DirectedLabeledGraph.Node<>(minDest);
            List<DirectedLabeledGraph.Edge<Point, Double>> edges = graph.listChildren(node);
            for (DirectedLabeledGraph.Edge<Point, Double> i : edges) {
                if (!finished.contains(i.getChild().getValue())) {
                    Path<Point> newPath = minPath.extend(i.getChild().getValue(), i.getLabel());
                    active.add(newPath);
                }
            }
            finished.add(minDest);
        }
        checkRep(graph);
        return null;
    }

    /**
     * find the shortest path between two given nodes of the graph
     * @param graph A graph with nodes as String and Edge as Double
     * @param start String represents the start point of the path
     * @param end  String represents the end point of the path
     * @spec.requires graph.containNode(start) and graph.containNode(end)
     *                start != null and end != null
     * @return Path if there exists the shortest path between the nodes
     *         null otherwise
     */
    public static Path<String> findPath(DirectedLabeledGraph<String, Double> graph, String start, String end) {
        checkRep(graph);
        Path<String> self = new Path<>(start);
        Set<String> finished = new HashSet<>();
        Comparator<Path<String>> compPath = new Comparator<>() {
            @Override
            public int compare(Path<String> a, Path<String> b) {
                return Double.compare(a.getCost(), b.getCost());
            }
        };
        // Consturct a queue for nodes that still needs probing
        PriorityQueue<Path<String>> active = new PriorityQueue<>(compPath);
        active.add(self);
        // Loop through all the possible nodes while constructing the path
        while (!active.isEmpty()) {
            Path<String> minPath = active.poll();
            String minDest = minPath.getEnd();
            if (minDest.equals(end)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
            // Add the neighbouring nodes to the queue
            DirectedLabeledGraph.Node<String> node = new DirectedLabeledGraph.Node<>(minDest);
            List<DirectedLabeledGraph.Edge<String, Double>> edges = graph.listChildren(node);
            for (DirectedLabeledGraph.Edge<String, Double> i : edges) {
                if (!finished.contains(i.getChild().getValue())) {
                    Path<String> newPath = minPath.extend(i.getChild().getValue(), i.getLabel());
                    active.add(newPath);
                }
            }
            finished.add(minDest);
        }
        checkRep(graph);
        return null;
    }

    private static void checkRep(Object graph) {
        assert graph != null : "this.graph is null";
    }
}

