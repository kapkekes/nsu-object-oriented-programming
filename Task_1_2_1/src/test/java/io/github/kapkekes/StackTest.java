package io.github.kapkekes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
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
    void emptyAndNotEnough() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(Optional.empty(), stack.pop());

        IntStream.range(0, 5).forEach(stack::push);
        assertEquals(Optional.empty(), stack.popStack(6));
    }

    @Test
    void nullChecks() {
        Stack<Integer> stack = new Stack<>();

        assertThrows(IllegalStateException.class, () -> stack.push(null));
        assertEquals(0, stack.count());

        assertThrows(IllegalStateException.class, () -> stack.pushStack(null));
        assertEquals(0, stack.count());
    }
}