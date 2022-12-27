package io.github.kapkekes.operations.logic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.Complex

/** True representation as a [Complex]. */
object True : Operation {
    override val token: String = "true"
    override val arity: Int = 0
    override val action: Action = { _ -> value }

    val value = Complex(1.0)
}
