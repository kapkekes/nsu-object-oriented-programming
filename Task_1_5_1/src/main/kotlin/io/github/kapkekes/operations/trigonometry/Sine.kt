package io.github.kapkekes.operations.trigonometry

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.complex.sin

/** Returns the cosine of the given number. */
object Sine : Operation {
    override val token: String = "sin"
    override val arity: Int = 1
    override val action: Action = { unit -> sin(unit[0]) }
}
