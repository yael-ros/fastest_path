package com.infinity;

import java.util.Map;

public class Graph {
    //the value of map graph (Map<Vertex, Integer>), is a map of all
    //adjacent vertices to a specific vertex and their costs
    private Map<Vertex, Map<Vertex, Integer>> graph;

    public Graph(Map<Vertex, Map<Vertex, Integer>> graph) {
        this.graph = graph;
    }

    public Map<Vertex, Map<Vertex, Integer>> getGraph() {
        return graph;
    }

    public void setGraph(Map<Vertex, Map<Vertex, Integer>> graph) {
        this.graph = graph;
    }
}
