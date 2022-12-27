package io.github.kapkekes.operations.basic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

/** Returns the product of two numbers. */
object Multiplication : Operation {
    override val token: String = "*"
    override val arity: Int = 2
    override val action: Action = { pair -> pair[0] * pair[1] }
}
