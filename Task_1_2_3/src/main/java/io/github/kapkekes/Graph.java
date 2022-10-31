package io.github.kapkekes;

import java.util.Optional;

/**
 * Interface, representing a graph structure.
 * <p>
 * Consists of objects, which implement {@code Edge<V>} and {@code Vertex<V>}.
 *
 * @param <V> type of the name, used by {@code Vertex<V>}
 */
public interface Graph<V> {
    /**
     * Add vertex to the graph.
     *
     * @param vertex vertex to add
     * @return {@code this}
     */
    public Graph<V> addVertex(Vertex<V> vertex);

    /**
     * Add edge to the graph.
     *
     * @param edge edge to add
     * @return {@code this}
     */
    public Graph<V> addEdge(Edge<V> edge);

    /**
     * Get vertex from the graph by the name.
     *
     * @param name name of the vertex
     * @return needed vertex or {@code Optional.empty()}
     */
    public Optional<Vertex<V>> getVertex(V name);

    /**
     * Get edge from the graph by two vertices from this graph.
     *
     * @param a first vertex
     * @param b second vertex
     * @return needed edge or {@code Optional.empty()}
     */
    public Optional<Edge<V>> getEdge(Vertex<V> a, Vertex<V> b);
}
