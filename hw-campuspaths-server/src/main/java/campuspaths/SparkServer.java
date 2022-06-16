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

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Path.Segment;
import pathfinder.datastructures.Point;
import spark.Spark;

import java.lang.reflect.Type;
import java.util.*;

public class SparkServer {
    private static final String BUILDINGPATH = "campus_buildings.csv";
    private static final String EDGEPATH = "campus_paths.csv";

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // TODO: Create all the Spark Java routes you need here.
        // Stores the campus map object that stores all information of on campus
        // buildings and paths
        CampusMap campus = new CampusMap(BUILDINGPATH, EDGEPATH);

        // Return the list of avaliable buildings
        Spark.get("/building", (req, res) -> {
            Gson gson = new Gson();
            Map<String, String> building = campus.buildingNames();
            String gsonString = gson.toJson(building);
            res.type("text/plain");
            return gsonString;
        });


        // Return the shortest path given the start and end buildings
        Spark.get("/path", (req, res) -> {
            String start = req.queryParams("start");
            String end = req.queryParams("end");
            if (start == null || end == null) {
                res.status(400);
                return "Missing start or end";
            } else {
                res.type("text/plain");
                Path<Point> path = campus.findShortestPath(start, end);
                if (path == null) {
                    return "no Path found";
                // Edge case when the path does not contain any segment
                } else if (path.getStart().equals(path.getEnd())) {
                    Map<String, String> cur = new HashMap<>();
                    cur.put("StartX", Double.toString(path.getStart().getX()));
                    cur.put("StartY", Double.toString(path.getStart().getY()));
                    cur.put("EndX", Double.toString(path.getEnd().getX()));
                    cur.put("EndY", Double.toString(path.getEnd().getY()));
                    cur.put("Cost", Double.toString(path.getCost()));
                    List<Map<String, String>> list = new LinkedList<>();
                    list.add(cur);
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    return json;
                } else {
                    List<Map<String, String>> list = new LinkedList<>();
                    Iterator<Path<Point>.Segment> itr = path.iterator();
                    while (itr.hasNext()) {
                        Path<Point>.Segment seg =itr.next();
                        Map<String, String> cur = new HashMap<>();
                        cur.put("StartX", Double.toString(seg.getStart().getX()));
                        cur.put("StartY", Double.toString(seg.getStart().getY()));
                        cur.put("EndX", Double.toString(seg.getEnd().getX()));
                        cur.put("EndY", Double.toString(seg.getEnd().getY()));
                        cur.put("Cost", Double.toString(seg.getCost()));
                        list.add(cur);
                    }
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    return json;
                }
            }
        });
    }
}
