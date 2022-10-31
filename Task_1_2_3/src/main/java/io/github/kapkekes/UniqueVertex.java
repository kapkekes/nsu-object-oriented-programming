package io.github.kapkekes;

import java.util.Objects;

/**
 * A vertex class, redefining {@code equals} method to check equality by {@code equals} method of
 * the name.
 *
 * @param <V> type of the name in the vertex
 */
public class UniqueVertex<V> implements Vertex<V> {
    private V name;

    /**
     * Create a unique vertex object.
     *
     * @param name name to set in the vertex
     */
    public UniqueVertex(V name) {
        this.name = name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        UniqueVertex<V> uniqueVertex = (UniqueVertex<V>) o;
        return Objects.equals(name, uniqueVertex.name);
    }

    @Override
    public V getName() {
        return name;
    }

    @Override
    public Vertex<V> setName(V name) {
        this.name = name;
        return this;
    }
}
