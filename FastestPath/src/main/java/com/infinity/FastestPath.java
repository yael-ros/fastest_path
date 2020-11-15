package com.infinity;

import java.util.*;
/*
fastest path between two vertices in a graph is determined by dijkstra algorithm.
all vertices in the graph are connected to each other. the connections are not
the same in their costs.
*/
public class FastestPath {
    private String fileName;
    private Graph graph;
    private Vertex source;
    private Vertex destination;

    public FastestPath(String filePath) {
        this.fileName = filePath;
    }

    public String getFastestPath() {
        FileReader fileReader = new FileReader(fileName);
        graph = fileReader.initGraphFromFile();
        source = fileReader.getSource();
        destination = fileReader.getDestination();
        Map<Vertex, CostAndPrevious> costs = createTableOfCosts();
        Path path = buildPath(costs);
        return path.fastestPathForPrinting();
    }

    // build path using cost map.
    // tracking the path from destination vertex to source vertex
    // via previous vertex which located in CostAndPrevious
    private Path buildPath(Map<Vertex, CostAndPrevious> costs) {
        Path path = new Path(graph);
        Vertex current = destination;

        while (!current.equals(source)) {
            path.addToBeginningOfPath(current);
            current = costs.get(current).getPrevious();
        }
        path.addToBeginningOfPath(current);
        return path;
    }

    private Map<Vertex, CostAndPrevious> createTableOfCosts() {
        Map<Vertex, CostAndPrevious> costs = initTableOfCosts();
        //list of all existing vertices in graph
        List<Vertex> unvisited = new ArrayList<>(graph.getGraph().keySet());
        Vertex current = source;

        while (!unvisited.isEmpty()) {
            Map<Vertex, Integer> allConnectionsAndCostsToCurrentVertex = graph.getGraph().get(current);
            Set<Map.Entry<Vertex, Integer>> pairs = allConnectionsAndCostsToCurrentVertex.entrySet();
            for (Map.Entry<Vertex, Integer> pair : pairs) {
                Integer oldCost = costs.get(pair.getKey()).getCost();
                Integer newCost = pair.getValue() + costs.get(current).getCost();
                if (oldCost > newCost) {
                    //update a shorter path
                    costs.put(pair.getKey(), new CostAndPrevious(newCost, current));
                }
            }
            unvisited.remove(current);
            current = getNextVertexByMinimumCost(costs, unvisited);
        }
        return costs;
    }

    private Vertex getNextVertexByMinimumCost(Map<Vertex, CostAndPrevious> costs, List<Vertex> unvisited) {
        Vertex result = null;
        int minCost = Integer.MAX_VALUE;

        for (Vertex vertex : unvisited) {
            Integer currentCost = costs.get(vertex).getCost();
            if (currentCost < minCost) {
                result = vertex;
                minCost = currentCost;
            }
        }
        return result;
    }

    // init all previous vertices in cost map to null;
    // init all costs to Integer.MAX_VALUE except source vertex
    private Map<Vertex, CostAndPrevious> initTableOfCosts() {
        Map<Vertex, CostAndPrevious> costs = new HashMap<>();
        Set<Vertex> vertices = graph.getGraph().keySet();

        for (Vertex vertex : vertices) {
            costs.put(vertex, new CostAndPrevious(Integer.MAX_VALUE, null));
        }
        costs.put(source, new CostAndPrevious(0, null));

        return costs;
    }
}
