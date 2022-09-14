package io.github.kapkekes;

import java.lang.reflect.Array;

public class Stack<E> {
    private int used;
    private int real;
    private Object[] array;

    Stack() {
        this.used = 0;
        this.real = 4;
        this.array = new Object[this.real];
    }
}
