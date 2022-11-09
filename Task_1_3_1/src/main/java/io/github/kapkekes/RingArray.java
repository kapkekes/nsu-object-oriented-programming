package io.github.kapkekes;

import java.util.NoSuchElementException;

/**
 * Utility class, which allows to use <i>infinite</i> array.
 *
 * @param <E> type of elements in the array
 */
public class RingArray<E> {
    private final Object[] array;
    private final int size;

    /**
     * Create a {@code RingArray} object.
     *
     * @param size the size of <i>init</i> and <i>ring</i> parts of the buffer
     */
    public RingArray (int size) {
        array = new Object[2 * size];
        this.size = size;
    }

    /**
     * Set a new value on the given position.
     *
     * @param index the position
     * @param val the new value to set
     * @return {@code this}
     * @throws NoSuchElementException if {@code index < 0}
     */
    public RingArray<E> set (int index, E val) {
        if (index < 0) {
            throw new NoSuchElementException();
        }

        if (index <= size) {
            array[index] = val;
        } else {
            array[(index - size) % size + size] = val;
        }

        return this;
    }

    /**
     * Get a value from the given position.
     *
     * @param index the position
     * @return the value from it
     * @throws NoSuchElementException if {@code index < 0}
     */
    @SuppressWarnings("unchecked")
    public E get (int index) throws NoSuchElementException{
        if (index < 0) {
            throw new NoSuchElementException();
        }

        if (index <= size) {
            return (E) array[index];
        }

        return (E) array[(index - size) % size + size];
    }
}