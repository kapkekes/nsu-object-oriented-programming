package io.github.kapkekes.operations.logic

import io.github.kapkekes.Action
import io.github.kapkekes.Complex
import io.github.kapkekes.Operation

/** True representation as a [Complex]. */
object True : Operation {
    override val token: String = "true"
    override val arity: Int = 0
    override val action: Action = { _ -> value }

    val value = Complex(1.0)
}
