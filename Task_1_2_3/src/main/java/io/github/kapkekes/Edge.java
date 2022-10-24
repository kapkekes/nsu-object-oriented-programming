package io.github.kapkekes;

public interface Edge<V> {
    public double getWeight();
    public Vertex<V> getFromVertex();
    public Vertex<V> getToVertex();
    public Edge<V> setWeight();
}
