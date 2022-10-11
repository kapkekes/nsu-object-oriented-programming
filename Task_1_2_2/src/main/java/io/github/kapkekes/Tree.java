package io.github.kapkekes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Generic tree class. Each node contains a value and a list of its children.
 *
 * @param <E> type of tree elements
 */
public class Tree<E> implements Iterable<E> {
    private static class TreeIterator<T> implements Iterator<T> {
        private final Iterator<Tree<T>> descendants;

        private TreeIterator(List<Tree<T>> traversed) {
            descendants = traversed.iterator();
        }

        @Override
        public boolean hasNext() {
            return descendants.hasNext();
        }

        @Override
        public T next() throws NoSuchElementException {
            Tree<T> descendant = descendants.next();
            descendant.iteratorCount--;
            return descendant.value;
        }
    }

    /** Search algorithm options. */
    public enum Search {
        /** Use breadth-first search algorithm (aka BFS) to traverse the tree. */
        BREADTH,

        /** Use depth-first search algorithm (aka DFS) to traverse the tree. */
        DEPTH
    }

    private E value;
    private List<Tree<E>> children;
    private Search mode;
    private int iteratorCount;

    private List<Tree<E>> breadthFirst() {
        Queue<Tree<E>> queue = new ArrayDeque<>();
        List<Tree<E>> descendants = new ArrayList<>(this.size());

        queue.add(this);

        while (!queue.isEmpty()) {
            Tree<E> descendant = queue.poll();
            descendants.add(descendant);
            queue.addAll(descendant.children);
        }

        return descendants;
    }

    private List<Tree<E>> depthFirst() {
        List<Tree<E>> descendants = new ArrayList<>(this.size());

        descendants.add(this);
        for (Tree<E> descendant : children) {
            descendants.addAll(descendant.depthFirst());
        }

        return descendants;
    }

    /**
     * Create an empty tree with the given value.
     *
     * @param val the value to set
     */
    public Tree(E val) {
        value = val;
        children = new ArrayList<>();
        mode = Search.BREADTH;
        iteratorCount = 0;
    }

    /**
     * Change the traverse mode of tree. It is set to {@code BREADTH} by default.
     *
     * @param mode the algorithm to traverse tree
     * @return {@code this}
     */
    public Tree<E> with(Search mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Get the quantity of nodes in this tree, including the head.
     *
     * @return the quantity of nodes
     */
    public int size() {
        return children.stream().mapToInt(Tree::size).reduce(Integer::sum).orElse(1);
    }

    @Override
    public Iterator<E> iterator() {
        List<Tree<E>> descendants =
                switch (mode) {
                    case BREADTH -> breadthFirst();
                    case DEPTH -> depthFirst();
                };

        for (Tree<E> descendant : descendants) {
            descendant.iteratorCount++;
        }

        return new TreeIterator<>(descendants);
    }

    public Tree<E> add(E val) throws ConcurrentModificationException, NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        if (iteratorCount > 0) {
            throw new ConcurrentModificationException();
        }

        children.add(new Tree<>(val));
        return this;
    }

    public Tree<E> add(Tree<E> branch)
            throws ConcurrentModificationException, NullPointerException {
        if (branch == null) {
            throw new NullPointerException();
        }

        if (iteratorCount > 0) {
            throw new ConcurrentModificationException();
        }

        children.add(branch);
        return this;
    }

    public Tree<E> get(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public Tree<E> remove(int index) throws IndexOutOfBoundsException {
        return children.remove(index);
    }
}
