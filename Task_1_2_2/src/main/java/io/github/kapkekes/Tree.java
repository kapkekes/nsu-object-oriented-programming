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
    private final List<Tree<E>> children;
    private Search mode;
    private Integer iteratorCount;

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
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree(E val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException("can't create a tree with null value");
        }

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
     * @throws NullPointerException if {@code mode} is {@code null}
     */
    public Tree<E> with(Search mode) throws NullPointerException {
        if (mode == null) {
            throw new NullPointerException("can't set a null as a traverse mode");
        }

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

    /**
     * Returns an iterator over the elements in this tree in proper sequence. Order of the
     * elements will be defined by {@code mode} field; use {@code with()} method to specify needed
     * traverse option.
     *
     * @return an iterator over the elements
     */
    @Override
    public Iterator<E> iterator() {
        List<Tree<E>> descendants = null;

        if (mode == Search.BREADTH) {
            descendants = breadthFirst();
        } else if (mode == Search.DEPTH) {
            descendants = depthFirst();
        }

        assert descendants != null;

        for (Tree<E> descendant : descendants) {
            descendant.iteratorCount++;
        }

        return new TreeIterator<>(descendants);
    }

    /**
     * Add a new value to the tree as a new branch.
     *
     * @param val a value to add
     * @return {@code this}
     * @throws ConcurrentModificationException if this tree has active iterators at the moment
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree<E> add(E val) throws ConcurrentModificationException, NullPointerException {
        if (val == null) {
            throw new NullPointerException("can't add a null value to a tree");
        }

        if (iteratorCount > 0) {
            throw new ConcurrentModificationException(
                    "can't add a value to a tree, as it has %d active iterators"
                            .replace("%d", this.iteratorCount.toString())
            );
        }

        children.add(new Tree<>(val));
        return this;
    }

    /**
     * Add an existing tree as a branch to this tree.
     *
     * @param branch a tree to add
     * @return {@code this}
     * @throws ConcurrentModificationException if this tree has active iterators at the moment
     * @throws NullPointerException if {@code branch} is {@code null}
     */
    public Tree<E> add(Tree<E> branch)
            throws ConcurrentModificationException, NullPointerException {
        if (branch == null) {
            throw new NullPointerException("can't add a null branch to a tree");
        }

        if (iteratorCount > 0) {
            throw new ConcurrentModificationException(
                    "can't add a branch to a tree, as it has %d active iterators"
                            .replace("%d", this.iteratorCount.toString())
            );
        }

        children.add(branch);
        return this;
    }

    /**
     * Get the value in this node.
     *
     * @return the value in this node
     */
    public E get() {
        return this.value;
    }

    /**
     * Get a branch of this tree.
     *
     * @param index the number of a needed branch
     * @return a requested branch
     * @throws IndexOutOfBoundsException if the index is out of range {@code index < 0 || index >=
     *     children.size()}
     */
    public Tree<E> get(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    /**
     * Set the value in this node.
     *
     * @param val value to set
     * @return {@code this}
     * @throws ConcurrentModificationException if this tree has active iterators at the moment
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree<E> set(E val) throws ConcurrentModificationException, NullPointerException {
        if (val == null) {
            throw new NullPointerException("can't set a null value as a tree value");
        }

        if (iteratorCount > 0) {
            throw new ConcurrentModificationException(
                    "can't add a branch to a tree, as it has %d active iterators"
                            .replace("%d", this.iteratorCount.toString())
            );
        }

        value = val;
        return this;
    }

    /**
     * Remove a branch from this tree.
     *
     * @param index the number of a branch to remove
     * @return a removed branch
     * @throws IndexOutOfBoundsException if the index is out of range {@code index < 0 || index >=
     *     children.size()}
     */
    public Tree<E> remove(int index) throws IndexOutOfBoundsException {
        return children.remove(index);
    }
}
