package ru.kapkekes.sortings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.kapkekes.sortings.Heap.sort;

import org.junit.jupiter.api.Test;

class HeapTest {
    @Test
    void testFromTask() {
        Integer[] actual = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};

        sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void singleElement() {
        Integer[] actual = {1};
        Integer[] expected = {1};

        sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void empty() {
        Integer[] actual = {};
        Integer[] expected = {};

        sort(actual);

        assertArrayEquals(expected, actual);
    }
}