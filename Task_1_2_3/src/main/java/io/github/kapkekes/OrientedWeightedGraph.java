package io.github.kapkekes;

import java.util.ArrayList;
import java.util.List;

public class OrientedWeightedGraph<V> implements Graph<V> {
    private final List<Vertex<V>> vertices;

    public OrientedWeightedGraph() {
        vertices = new ArrayList<>();
    }

    @Override
    public Graph<V> addVertex(Vertex<V> vertex) {
        return null;
    }

    @Override
    public Graph<V> addEdge(Edge<V> edge) {
        return null;
    }

    @Override
    public Vertex<V> getVertex(V name) {
        return null;
    }

    @Override
    public Edge<V> getEdge(Vertex<V> a, Vertex<V> b) {
        return null;
    }
}
