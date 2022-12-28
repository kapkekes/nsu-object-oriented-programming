package io.github.kapkekes.operations.trigonometry

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

/** Handles the given number as a degree value. */
object Degree : Operation {
    override val token: String = "deg"
    override val arity: Int = 1
    override val action: Action = { unit -> unit[0] * kotlin.math.PI / 180.0 }
}
