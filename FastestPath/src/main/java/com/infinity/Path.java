package com.infinity;

import java.util.Stack;

public class Path {
    private Graph graph;
    private Stack<Vertex> stack = new Stack<>();

    public Path(Graph graph) {
        this.graph = graph;
    }

    //using stack in order to reverse the order of path
    //which starts in destination and ends in source
    public void addToBeginningOfPath(Vertex vertex){
        stack.push(vertex);
    }

    public String fastestPathForPrinting(){
        String firstArrow = "--(";
        String secondArrow = ")-->";
        StringBuilder str = new StringBuilder();

        while(1 < stack.size()){
            Vertex current = stack.pop();
            str.append(current.getName());
            str.append(firstArrow);
            str.append(getCostFromCurrVertexToNextVertex(current));
            str.append(secondArrow);
        }
        str.append(stack.pop().getName());
        return str.toString();
    }

    private Integer getCostFromCurrVertexToNextVertex(Vertex current) {
        return graph.getGraph().get(current).get(stack.peek());
    }
}
