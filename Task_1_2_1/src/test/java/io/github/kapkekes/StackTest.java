package io.github.kapkekes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Tests for stack class. */
class StackTest {

    @Test
    void simpleTest() {
        Stack<Integer> appendingStack = new Stack<Integer>()
                .push(4)
                .push(8);

        Stack<Integer> stack = new Stack<Integer>()
                .push(2)
                .push(7)
                .pushStack(appendingStack);

        assertEquals(4, stack.count());
        assertEquals(8, stack.length);

        Optional<Integer> poppedValue = stack.pop();

        assertEquals(Optional.of(8), poppedValue);
        assertEquals(3, stack.count());

        stack
                .popStack(2)
                .ifPresentOrElse(
                        (s) -> {
                            assertEquals(2, s.count());
                            assertEquals(Optional.of(4), s.pop());
                            assertEquals(Optional.of(7), s.pop());
                        },
                        Assertions::fail);

        assertEquals(1, stack.count());
        assertEquals(Optional.of(2), stack.pop());
    }

    @Test
    void stackMemoryManagement() {
        Stack<Integer> stack = new Stack<Integer>().resizeStack(10500);
        assertEquals(10500, stack.length);

        Random random = new Random(123456789);

        random.ints(10000).boxed().forEachOrdered(stack::push);
        assertEquals(10500, stack.length);

        random.ints(4000).boxed().forEachOrdered(stack::push);
        assertEquals(21000, stack.length);

        stack.popStack(8749);
        assertEquals(21000, stack.length);

        stack.popStack(10);
        assertEquals(10500, stack.length);
    }

    @Test
    void manualMemoryManagement() {
        Stack<Integer> stack = new Stack<Integer>().resizeStack(1);
        assertEquals(Stack.minimalLength, stack.length);

        int range = 5;

        int shift = (int) Math.ceil(Math.log(range) / Math.log(2)) - 1;
        IntStream.range(0, range).forEach(stack::push);

        assertEquals(Stack.minimalLength << shift, stack.length);
    }

    @Test
    void emptyAndNotEnough() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(Optional.empty(), stack.pop());

        int range = 5;

        IntStream.range(0, range).forEach(stack::push);
        assertEquals(Optional.empty(), stack.popStack(range + 1));


    }

    @Test
    void nullChecks() {
        Stack<Integer> stack = new Stack<>();

        stack.push(null);
        assertEquals(0, stack.count());

        stack.pushStack(null);
        assertEquals(0, stack.count());
    }
}