package io.github.kapkekes;

/**
 * An edge class, which has a starting and ending vertex, and a weight.
 *
 * @param <V> type of the name in the vertex
 */
public class OrientedWeightedEdge<V> implements Edge<V> {
    private double weight;
    private final Vertex<V> starting;
    private final Vertex<V> ending;

    /**
     * Create an oriented weighted edge.
     *
     * @param weight weight of this edge
     * @param a starting vertex
     * @param b ending vertex
     */
    public OrientedWeightedEdge(double weight, Vertex<V> a, Vertex<V> b) {
        this.weight = weight;
        starting = a;
        ending = b;
    }

    /**
     * Get weight of this edge.
     *
     * @return weight of the edge
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set a new weight value for this edge.
     *
     * @param weight new weight
     * @return {@code this}
     */
    public Edge<V> setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    /**
     * Get starting vertex of this edge.
     *
     * @return starting vertex of the edge
     */
    @Override
    public Vertex<V> getVertexA() {
        return starting;
    }

    /**
     * Get ending vertex of this edge.
     *
     * @return ending edge of the edge
     */
    @Override
    public Vertex<V> getVertexB() {
        return ending;
    }
}
