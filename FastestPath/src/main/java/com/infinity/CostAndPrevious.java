package com.infinity;

public class CostAndPrevious {
    Integer cost;
    Vertex previous;

    public CostAndPrevious(Integer cost, Vertex previous) {
        this.cost = cost;
        this.previous = previous;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }
}
