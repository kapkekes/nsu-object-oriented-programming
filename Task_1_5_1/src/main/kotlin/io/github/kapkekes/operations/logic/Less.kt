package io.github.kapkekes.operations.logic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

/**
 * Returns [True], if the numbers are real and the first number is less than the second.
 * Throws [ArithmeticException] if the numbers are not real.
 */
object Less : Operation {
    override val token: String = "<"
    override val arity: Int = 2
    override val action: Action = { pair ->
        if (pair.all { it.isReal }) {
            if (pair[0].real < pair[1].real) True.value else False.value
        } else {
            throw ArithmeticException("Can't perform ordering operations on Complex numbers: ${pair[0]} < ${pair[1]}")
        }
    }
}
