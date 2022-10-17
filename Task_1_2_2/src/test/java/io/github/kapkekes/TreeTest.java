package io.github.kapkekes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** A set of tests for the {@code Tree} class. */
public class TreeTest {
    public static Tree<Integer> expected;
    public static Tree<Integer> branch;
    public static List<Integer> breadth =
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
    public static List<Integer> depth =
            List.of(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 3, 12, 16, 17, 13, 14, 15);

    /** Initialize tree and branch. */
    @BeforeAll
    public static void initialization() {
        expected = new Tree<>(1);
        expected.add(2).getBranch(0).add(4).add(5).add(6).add(7).add(8).add(9).add(10).add(11);

        branch = new Tree<>(3);
        branch.add(12).getBranch(0).add(16).add(17).getParent().add(13).add(14).add(15);

        expected.add(branch);
    }

    @Test
    public void testBreadthTraverse() {
        List<Integer> actual = new ArrayList<>();
        expected.with(Tree.Search.BREADTH).iterator().forEachRemaining(actual::add);

        assertEquals(breadth, actual);
    }

    @Test
    public void testDepthTraverse() {
        List<Integer> actual = new ArrayList<>();
        expected.with(Tree.Search.DEPTH).iterator().forEachRemaining(actual::add);

        assertEquals(depth, actual);
    }

    @Test
    public void testSize() {
        assertEquals(17, expected.size());
    }

    @Test
    public void testRelationsAndGetters() {
        assertNull(expected.getParent());
        assertEquals(expected, branch.getParent());

        assertEquals(1, expected.getValue());
        assertThrows(IndexOutOfBoundsException.class, () -> expected.getBranch(100));
    }

    @Test
    public void testSetter() {
        expected.setValue(100);
        assertEquals(100, expected.getValue());
        expected.setValue(1);
        assertEquals(1, expected.getValue());
    }

    @Test
    public void testNullActions() {
        assertThrows(NullPointerException.class, () -> new Tree<>(null));
        assertThrows(NullPointerException.class, () -> expected.with(null));
        assertThrows(NullPointerException.class, () -> expected.add((Integer) null));
        assertThrows(NullPointerException.class, () -> expected.add((Tree<Integer>) null));
        assertThrows(NullPointerException.class, () -> expected.setValue(null));
    }

    @Test
    public void testIteratorExceptions() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    Iterator<Integer> iterator = expected.with(Tree.Search.BREADTH).iterator();
                    while (true) {
                        iterator.next();
                    }
                });

        assertThrows(
                NoSuchElementException.class,
                () -> {
                    Iterator<Integer> iterator = expected.with(Tree.Search.DEPTH).iterator();
                    while (true) {
                        iterator.next();
                    }
                });

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    for (Integer integer : expected) {
                        expected.setValue(10).setValue(1);
                    }
                });
    }

    @Test
    public void testRemove() {
        Tree<Integer> removed = expected.remove(1);

        assertThrows(IndexOutOfBoundsException.class, () -> expected.remove(1));
        assertNull(removed.getParent());

        expected.add(removed);
    }
}