package io.github.kapkekes;

public class HeapSort {

    /**
     * Sorts the given array using the binary heap structure.
     * <p>
     * Warning: this is an unstable sort algorithm.
     *
     * @param array array, which will be sorted
     * @param <E> type of the array, should implement Comparable interface
     */
    public static <E extends Comparable<E>> void sort(E[] array) {
        int size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapTransform(array, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            // place the largest element to the end of the array
            E buffer = array[0];
            array[0] = array[i];
            array[i] = buffer;

            // restore a binary heap
            heapTransform(array, i, 0);
        }
    }

    /**
     * Internal function. Should not be used outside this class methods.
     *
     * @param array array, which will be transformed to the binary heap
     * @param size length of the array, used to build different heaps
     * @param index index of the element, which will be rebalanced
     * @param <E> type of the array, should implement Comparable interface
     */
    private static <E extends Comparable<E>> void heapTransform(E[] array, int size, int index) {
        int maximumIndex = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = leftIndex + 1;

        if (leftIndex < size && array[leftIndex].compareTo(array[maximumIndex]) > 0) {
            maximumIndex = leftIndex;
        }

        if (rightIndex < size && array[rightIndex].compareTo(array[maximumIndex]) > 0) {
            maximumIndex = rightIndex;
        }

        if (maximumIndex != index) {
            E swap = array[index];
            array[index] = array[maximumIndex];
            array[maximumIndex] = swap;
            heapTransform(array, size, maximumIndex);
        }
    }
}