/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder.scriptTestRunner;

import graph.DirectedLabeledGraph;
import pathfinder.Dijkstra;
import pathfinder.datastructures.Path;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    private final Map<String, DirectedLabeledGraph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new GraphTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        // TODO: Implement this, reading commands from `r` and writing output to `w`.
        // See GraphTestDriver as an example.
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    // Leave this method public
    public void runTests() throws IOException {
        // TODO: Implement this.
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }


    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        // TODO Insert your code here.

        graphs.put(graphName, new DirectedLabeledGraph<>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        // TODO Insert your code here.

        DirectedLabeledGraph<String, Double> nodes = graphs.get(graphName);
        DirectedLabeledGraph.Node<String> node = new DirectedLabeledGraph.Node<>(nodeName);
        nodes.addNode(node);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }
        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        Double edgeLabel = Double.valueOf(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        // TODO Insert your code here.
        DirectedLabeledGraph<String, Double> edges = graphs.get(graphName);
        DirectedLabeledGraph.Node<String> parent = new DirectedLabeledGraph.Node<>(parentName);
        DirectedLabeledGraph.Node<String> child = new DirectedLabeledGraph.Node<>(childName);
        DirectedLabeledGraph.Edge<String, Double> edge = new DirectedLabeledGraph.Edge<>(parent, child, edgeLabel);
        edges.addEdge(edge);
        output.println("added edge " + String.format("%.3f", edgeLabel) + " from " + parentName + " to " + childName + " in " + graphName);
    }

    private void findPath(List<String> arguments) {
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }
        String graphName = arguments.get(0);
        String startNode = arguments.get(1);
        String endNode = arguments.get(2);

        findPath(graphName, startNode, endNode);
    }

    private void findPath(String graphName, String startNode, String endNode) {
        DirectedLabeledGraph<String, Double> graph = graphs.get(graphName);
        DirectedLabeledGraph.Node<String> start = new DirectedLabeledGraph.Node<>(startNode);
        DirectedLabeledGraph.Node<String> end = new DirectedLabeledGraph.Node<>(endNode);
        if (graph.containNode(start) && graph.containNode(end)) {
            output.println("path from " + startNode + " to " + endNode + ":");
            Path<String> ret = Dijkstra.findPath(graph, startNode, endNode);
            if (ret == null) {
                output.println("no path found");
            } else {
                Iterator<Path<String>.Segment> itr = ret.iterator();
                if (itr.hasNext()) {
//                    Path<String>.Segment cur = itr.next();
//                    output.println(startNode + " to " + cur.getEnd() + "with");
                    while (itr.hasNext()) {
                        Path<String>.Segment cur = itr.next();
                        output.println(cur.getStart() + " to " + cur.getEnd() +
                                " with weight " + String.format("%.3f", cur.getCost()));
                    }
                }
//                } else {
//                    output.println(startNode + " to " + endNode + " with weight " + String.format("%.3f", ret.getCost()));
//                }
                output.println("total cost: " + String.format("%.3f", ret.getCost()));
            }


        } else {
            if (!graph.containNode(start)) {
                output.println("unknown: " + startNode);
            }
            if (!graph.containNode(end)) {
                output.println("unknown: " + endNode);
            }
        }

    }
    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
