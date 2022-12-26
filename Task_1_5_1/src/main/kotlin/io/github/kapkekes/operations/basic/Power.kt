package io.github.kapkekes.operations.basic

import io.github.kapkekes.Action
import io.github.kapkekes.Operation

/** Returns the power of the first number to the second. */
object Power : Operation {
    override val token: String = "pow"
    override val arity: Int = 2
    override val action: Action = { pair -> pair[0].pow(pair[1]) }
}
