package ru.kapkekes.sortings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.kapkekes.sortings.Heap.sort;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class HeapTest {

    @Test
    void simpleTest() {
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

    @Test
    void pseudorandomNumbers() {
        Random random = new Random(123456789);

        Integer[] actual = Arrays.stream(random.ints(1000).toArray()).boxed().toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(actual).sorted().toArray(Integer[]::new);

        sort(actual);

        assertArrayEquals(expected, actual);
    }
}
