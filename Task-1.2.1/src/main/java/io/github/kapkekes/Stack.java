package io.github.kapkekes;

import static java.util.Arrays.copyOf;

import java.util.Optional;

public class Stack<E> {
    private int used;
    private int length;
    private Object[] array;


    public Stack() {
        this.used = 0;
        this.length = 4;
        this.array = new Object[this.length];
    }

    public int count() {
        return this.used;
    }

    private Stack<E> reallocateStack(int newLength) {
        this.length = newLength;
        this.array = copyOf(this.array, newLength);
        return this;
    }

    public Stack<E> push(E elem) {
        if (this.used >= this.length) {
            this.reallocateStack(this.used << 1);
        }

        this.array[this.length++] = elem;

        return this;
    }

    public Stack<E> pushStack(Stack<E> stack) {
        if (stack == null) {
            return this;
        }

        if (this.used + stack.used >= this.length) {
            this.reallocateStack((this.used + stack.used - 1) << 1);
        }

        for (int index = 0; index < stack.used; index++) {
            this.array[this.used++] = stack.array[index];
        }

        return this;
    }

    @SuppressWarnings("unchecked")
    public Optional<E> pop() {
        if (this.used == 0) {
            return Optional.empty();
        }

        Optional<E> element = (Optional<E>) Optional.of(this.array[--this.used]);

        if (this.used <= (this.length >> 2)) {
            this.reallocateStack(this.length >> 1);
        }

        return element;
    }

    public Optional<Stack<E>> popStack(int length) {
        if (this.used < length) {
            return Optional.empty();
        }

        Stack<E> stack = new Stack<>();
        stack.reallocateStack(length).used = length;

        for (int i = 0; i < length; i++) {
            stack.array[i] = this.array[--this.used];
        }

        if (this.used <= (this.length >> 2)) {
            this.reallocateStack(this.length >> 1);
        }

        return Optional.of(stack);
    }
}
