package io.github.kapkekes;

/**
 * Interface, which defines an edge for usage with the graph and the vertex interfaces.
 *
 * @param <V> type of the name in the vertices
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
