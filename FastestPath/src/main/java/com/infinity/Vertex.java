package com.infinity;

import java.util.Objects;

public class Vertex {
    Character name;

    public Vertex(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
