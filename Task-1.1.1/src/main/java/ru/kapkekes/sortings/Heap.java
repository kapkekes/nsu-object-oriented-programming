package ru.kapkekes.sortings;

public class Heap {
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