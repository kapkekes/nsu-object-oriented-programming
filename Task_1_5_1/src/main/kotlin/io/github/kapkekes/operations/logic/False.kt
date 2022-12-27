package io.github.kapkekes.operations.logic

import io.github.kapkekes.Action
import io.github.kapkekes.Complex
import io.github.kapkekes.Operation

/** False representation as a [Complex]. */
object False : Operation {
    override val token: String = "false"
    override val arity: Int = 0
    override val action: Action = { _ -> value }

    val value = Complex(0.0)
}
