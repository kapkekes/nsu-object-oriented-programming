package io.github.kapkekes;

public interface Graph<V> {
    public Graph<V> addVertex(Vertex<V> vertex);
    public Graph<V> addEdge(Edge<V> edge);
    public Vertex<V> getVertex(V name);
    public Edge<V> getEdge(Vertex<V> a, Vertex<V> b);
}
