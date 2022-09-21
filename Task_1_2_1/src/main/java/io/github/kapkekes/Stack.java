package io.github.kapkekes;

import static java.util.Arrays.copyOf;

import java.util.Optional;

/**
 * Generic stack class with memory management.
 *
 * @param <E> type of stack elements
 */
public class Stack<E> {
    private static final int MINIMAL_LENGTH = 2;
    private int length;
    private int used;
    private Object[] array;

    /** Creates a stack object with internal size of four elements. */
    public Stack() {
        this.used = 0;
        this.length = MINIMAL_LENGTH;
        this.array = new Object[this.length];
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements
     */
    public int count() {
        return this.used;
    }

    /**
     * Changes the stack size. It should be used if you know the approximate number of items that
     * will be stored in the stack.
     *
     * <p>The method returns this stack for fluent style code.
     *
     * @param newLength new actual length of stack
     * @return this
     */
    public Stack<E> resizeStack(int newLength) {
        this.length = Math.max(newLength, MINIMAL_LENGTH);
        this.array = copyOf(this.array, this.length);
        return this;
    }

    /**
     * Pushes the element on the top of the stack.
     *
     * <p>The method returns this stack for fluent style code.
     *
     * @param elem the element to put in the stack
     * @return {@code this} instance
     * @throws IllegalStateException if {@code elem} is {@code null}
     */
    public Stack<E> push(E elem) throws IllegalStateException {
        if (elem == null) {
            throw new IllegalStateException("Can't push 'null' to the stack");
        }

        if (this.used >= this.length) {
            this.resizeStack(this.used * 2);
        }

        this.array[this.used++] = elem;

        return this;
    }

    /**
     * Copies elements from the given stack to the top of this stack.
     *
     * <p>The method returns this stack for fluent style code.
     *
     * @param stack the stack from which the elements will be copied
     * @return {@code this} instance
     * @throws IllegalStateException if {@code stack} is {@code null}
     */
    public Stack<E> pushStack(Stack<E> stack) throws IllegalStateException {
        if (stack == null) {
            throw new IllegalStateException("Can't push 'null' to the stack");
        }

        if (this.used + stack.used >= this.length) {
            this.resizeStack((this.used + stack.used) * 2);
        }

        for (int index = 0; index < stack.used; index++) {
            this.array[this.used++] = stack.array[index];
        }

        return this;
    }

    /**
     * Retrieves an element from the top of this stack and wraps it with {@code Optional}. If this
     * stack is empty, the method will return an empty {@code Optional} instance.
     *
     * @return an element from the top of this stack
     */
    @SuppressWarnings("unchecked")
    public Optional<E> pop() {
        if (this.used == 0) {
            return Optional.empty();
        }

        Optional<E> element = Optional.of((E) this.array[--this.used]);

        if (this.used <= (this.length / 4)) {
            this.resizeStack(this.length / 2);
        }

        return element;
    }

    /**
     * Retrieves the {@code length} elements from the top of this stack as a new stack and wraps it
     * with {@code Optional}. If this stack contains less than {@code length} elements, the method
     * will return an empty {@code Optional} instance.
     *
     * @param length the length of the new stack
     * @return the new stack
     */
    public Optional<Stack<E>> popStack(int length) {
        if (this.used < length) {
            return Optional.empty();
        }

        Stack<E> stack = new Stack<>();
        stack.resizeStack(length).used = length;

        for (int i = length - 1; i >= 0; i--) {
            stack.array[i] = this.array[--this.used];
        }

        if (this.used <= (this.length / 4)) {
            this.resizeStack(this.length / 2);
        }

        return Optional.of(stack);
    }
}