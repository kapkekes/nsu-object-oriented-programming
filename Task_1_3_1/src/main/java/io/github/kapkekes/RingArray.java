package io.github.kapkekes;

import java.util.NoSuchElementException;

/**
 * Utility class, which allows to use <i>infinite</i> array.
 *
 * @param <E> type of elements in the array
 */
public class RingArray<E> {
    private final Object[] array;
    private final int prefixLength;
    private final int ringLength;

    /**
     * Create a {@code RingArray} object.
     *
     * @param prefixLength the size of the prefix part of constructed {@code RingArray}
     * @param ringLength the size of the ring part of constructed {@code RingArray}
     */
    public RingArray(int prefixLength, int ringLength) {
        array = new Object[prefixLength + ringLength];
        this.prefixLength = prefixLength;
        this.ringLength = ringLength;
    }

    /**
     * Get the length of the prefix part.
     *
     * @return the length of the prefix
     */
    public int getPrefixLength() {
        return prefixLength;
    }

    /**
     * Get the length of the ring part.
     *
     * @return the length of the ring
     */
    public int getRingLength() {
        return ringLength;
    }

    private int normalizeIndex(int index) {
        if (index >= prefixLength) {
            index = ((index - prefixLength) % ringLength) + prefixLength;
        }

        return index;
    }

    /**
     * Set the new value on the given position.
     *
     * @param index the position
     * @param value the new value to set
     * @return {@code this}
     * @throws NoSuchElementException if {@code index < 0}
     */
    public RingArray<E> set(int index, E value) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }

        array[normalizeIndex(index)] = value;
        return this;
    }

    /**
     * Get the value from the given position.
     *
     * @param index the position
     * @return the value from it
     * @throws NoSuchElementException if {@code index < 0}
     */
    @SuppressWarnings("unchecked")
    public E get(int index) throws NoSuchElementException {
        if (index < 0) {
            throw new NoSuchElementException();
        }

        return (E) array[normalizeIndex(index)];
    }
}