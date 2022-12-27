package io.github.kapkekes.operations.logic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.complex.Complex
import io.github.kapkekes.calculator.Operation

/** False representation as a [Complex]. */
object False : Operation {
    override val token: String = "false"
    override val arity: Int = 0
    override val action: Action = { _ -> value }

    val value = Complex(0.0)
}
