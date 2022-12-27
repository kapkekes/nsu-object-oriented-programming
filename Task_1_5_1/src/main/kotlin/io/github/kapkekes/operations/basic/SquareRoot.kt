package io.github.kapkekes.operations.basic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.extensions.sqrt

/** Returns the square root of the given number. */
object SquareRoot : Operation {
    override val token: String = "sqrt"
    override val arity: Int = 1
    override val action: Action = { unit -> sqrt(unit[0]) }
}
