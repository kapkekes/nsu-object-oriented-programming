package io.github.kapkekes;

/**
 * Interface, representing an edge structure.
 * <p>
 * Consists of two objects, which implement {@code Vertex<V>}.
 *
 * @param <V> type of the name, used by {@code Vertex<V>}
 */
public interface Edge<V> {
    /**
     * Get the first vertex of the edge.
     *
     * @return the first vertex
     */
    public Vertex<V> getVertexA();

    /**
     * Get the second vertex of the edge.
     *
     * @return the second vertex
     */
    public Vertex<V> getVertexB();
}
