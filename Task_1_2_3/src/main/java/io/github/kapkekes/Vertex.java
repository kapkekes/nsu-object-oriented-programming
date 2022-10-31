package io.github.kapkekes;

/**
 * Interface, which defines a vertex for usage with the graph and the edge interfaces.
 *
 * @param <V> type of the name
 */
public interface Vertex<V> {
    /**
     * Get name of the vertex.
     *
     * @return name of the vertex
     */
    public V getName();

    /**
     * Set a new name for the vertex.
     *
     * @param name new name
     * @return {@code this}
     */
    public Vertex<V> setName(V name);
}
