package io.github.kapkekes;

import static io.github.kapkekes.HeapSort.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the implemented heap sorting algorithm. */
public class HeapSortTest {

    @Test
    public void simpleTest() {
        Integer[] actual = {5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5};

        sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void singleElement() {
        Integer[] actual = {1};
        Integer[] expected = {1};

        sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void empty() {
        Integer[] actual = {};
        Integer[] expected = {};

        sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void pseudorandomNumbers() {
        Random random = new Random(123456789);

        Integer[] actual = random.ints(1000).boxed().toArray(Integer[]::new);
        Integer[] expected = Arrays.stream(actual).sorted().toArray(Integer[]::new);

        sort(actual);

        assertArrayEquals(expected, actual);
    }
}