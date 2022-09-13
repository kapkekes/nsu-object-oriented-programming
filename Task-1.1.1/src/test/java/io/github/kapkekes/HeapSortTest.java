package io.github.kapkekes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static io.github.kapkekes.HeapSort.sort;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class HeapSortTest {

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
