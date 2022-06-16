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

package pathfinder;

import graph.DirectedLabeledGraph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the path calculator and building directory
 * for the UW campus
 *
 */
public class CampusMap implements ModelAPI {
    // RI: buildings != null && graph != null
    // AF(this) = A CampusBuiding List and A directed labelled graph with points as CampusBuilding
    //            and Edge weight as distance
    private List<CampusBuilding> buildings;
    private DirectedLabeledGraph<Point, Double> graph;

    /**
     * Construct a new CampusMap object given the file names to look into
     *
     * @param campusBuilding String that represents the filename storing campus buildings
     * @param campusPath String that represents the filenmae storing campus paths
     */
    public CampusMap(String campusBuilding, String campusPath) {
        this.buildings = CampusPathsParser.parseCampusBuildings(campusBuilding);
        this.graph = graphCreator(campusPath);
        checkRep();
    }

    @Override
    public boolean shortNameExists(String shortName) {
        checkRep();
        for (CampusBuilding i : buildings) {
            if (i.getShortName().equals(shortName)) {
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }

    @Override
    public String longNameForShort(String shortName) {
        checkRep();
        for (CampusBuilding i : buildings) {
            if (i.getShortName().equals(shortName)) {
                checkRep();
                return i.getLongName();
            }
        }
        checkRep();
        throw new IllegalArgumentException("shortName does not exist");
    }

    @Override
    public Map<String, String> buildingNames() {
        checkRep();
        Map<String, String> map  = new HashMap<>();
        for (CampusBuilding i : buildings) {
            map.put(i.getShortName(), i.getLongName());
        }
        checkRep();
        return map;
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        checkRep();
        if (startShortName == null || endShortName == null ||
                !shortNameExists(startShortName) || !shortNameExists(endShortName)) {
            throw new IllegalArgumentException("input is wrong");
        }
        Point start = null;
        Point end = null;
        for (CampusBuilding i : buildings) {
            if (startShortName.equals(i.getShortName())) {
                start = new Point(i.getX(), i.getY());
            }
            if (endShortName.equals(i.getShortName())) {
                end = new Point(i.getX(), i.getY());
            }
        }
        checkRep();
        return Dijkstra.findPath(graph, start, end);
    }

    // helper method for constructing a graph
    private DirectedLabeledGraph<Point, Double> graphCreator(String path) {
        List<CampusPath> paths = CampusPathsParser.parseCampusPaths(path);
        DirectedLabeledGraph<Point, Double> graph = new DirectedLabeledGraph<>();
        for (CampusPath i : paths) {
            Point cur = new Point(i.getX1(), i.getY1());
            DirectedLabeledGraph.Node<Point> start = new DirectedLabeledGraph.Node<>(cur);
            Point cur1 = new Point(i.getX2(), i.getY2());
            DirectedLabeledGraph.Node<Point> end = new DirectedLabeledGraph.Node<>(cur1);
            graph.addNode(start);
            graph.addNode(end);
            DirectedLabeledGraph.Edge<Point, Double> edge = new DirectedLabeledGraph.Edge<>(start, end, i.getDistance());
            graph.addEdge(edge);
        }
        return graph;
    }

    private void checkRep() {
        assert graph != null : "this.graph is null";
        assert buildings != null : "this.buildings is null";
    }
}
