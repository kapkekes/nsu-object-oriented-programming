package io.github.kapkekes.operations.trigonometry

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.extensions.cos

/** Returns the cosine of the given number. */
object Cosine : Operation {
    override val token: String = "cos"
    override val arity: Int = 1
    override val action: Action = { unit -> cos(unit[0]) }
}
