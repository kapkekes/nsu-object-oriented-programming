package io.github.kapkekes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Generic tree class. Each node contains a value and a list of its children.
 *
 * @param <E> type of tree elements
 */
public class Tree<E> implements Iterable<E> {
    private E value;
    private Tree<E> parent;
    private final List<Tree<E>> children;
    private Search mode;
    private int modificationState;

    /**
     * Create an empty tree with the given value.
     *
     * @param val the value to set
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree(E val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        value = val;
        parent = null;
        children = new ArrayList<>();
        mode = Search.BREADTH;
        modificationState = 0;
    }

    private void update() {
        modificationState++;
    }

    private void propagate() {
        Tree<E> node = this;

        while (node.parent != null) {
            node.update();
            node = node.parent;
        }

        node.update();
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
            throw new NullPointerException();
        }

        propagate();

        this.mode = mode;
        children.forEach(t -> t.with(mode));

        return this;
    }

    /**
     * Get the quantity of nodes in this tree, including the head.
     *
     * @return the quantity of nodes
     */
    public int size() {
        return children.stream().mapToInt(Tree::size).sum() + 1;
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
        if (mode == Search.BREADTH) {
            return new BreadthIterator();
        } else if (mode == Search.DEPTH) {
            return new DepthIterator();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Add a new value to the tree as a new branch.
     *
     * @param val a value to add
     * @return {@code this}
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree<E> add(E val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        propagate();

        Tree<E> child = new Tree<>(val);
        child.parent = this;
        children.add(child);

        return this;
    }

    /**
     * Add an existing tree as a branch to this tree.
     *
     * @param branch a tree to add
     * @return {@code this}
     * @throws NullPointerException if {@code branch} is {@code null}
     */
    public Tree<E> add(Tree<E> branch) throws NullPointerException {
        if (branch == null) {
            throw new NullPointerException();
        }

        propagate();

        branch.parent = this;
        children.add(branch);

        return this;
    }

    /**
     * Get the value in this node.
     *
     * @return the value in this node
     */
    public E getValue() {
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
    public Tree<E> getBranch(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    /**
     * Get the parent of this tree.
     *
     * @return the parent
     */
    public Tree<E> getParent() {
        return parent;
    }

    /**
     * Set the value in this node.
     *
     * @param val value to set
     * @return {@code this}
     * @throws NullPointerException if {@code val} is {@code null}
     */
    public Tree<E> setValue(E val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
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
        Tree<E> child = children.remove(index);
        child.parent = null;

        propagate();

        return child;
    }

    private abstract class TreeIterator implements Iterator<E> {
        private final Tree<E> root;
        private final int modificationState;

        private TreeIterator() {
            root = Tree.this;
            modificationState = Tree.this.modificationState;
        }

        @Override
        public E next() throws ConcurrentModificationException {
            if (root.modificationState != modificationState) {
                throw new ConcurrentModificationException();
            }

            return null;
        }
    }

    private class BreadthIterator implements Iterator<E> {
        private final Queue<Tree<E>> queue;
        private final Tree<E> root;
        private final int modificationState;

        private BreadthIterator() {
            queue = new ArrayDeque<>();
            queue.add(Tree.this);
            root = Tree.this;
            modificationState = Tree.this.modificationState;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() throws ConcurrentModificationException, NoSuchElementException {


            Tree<E> polled = queue.poll();
            if (polled == null) {
                throw new NoSuchElementException();
            }

            queue.addAll(polled.children);

            return polled.value;
        }
    }

    private class DepthIterator implements Iterator<E> {
        private final Stack<Tree<E>> stack;
        private final int modificationState;

        private DepthIterator() {
            stack = new Stack<>();
            stack.push(Tree.this);
            modificationState = 0;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() throws ConcurrentModificationException, NoSuchElementException {
            Tree<E> popped;

            try {
                popped = stack.pop();
            } catch (EmptyStackException e) {
                throw new NoSuchElementException();
            }

            stack.addAll(0, popped.children);

            return null;
        }
    }

    /** Search algorithm options. */
    public enum Search {
        /** Use breadth-first search algorithm (aka BFS) to traverse the tree. */
        BREADTH,

        /** Use depth-first search algorithm (aka DFS) to traverse the tree. */
        DEPTH
    }
}
