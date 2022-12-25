package io.github.kapkekes.operations.logic

import io.github.kapkekes.Action
import io.github.kapkekes.Operation

/** Returns [True] if the numbers are equal, [False] otherwise. */
object Equality : Operation {
    override val token: String = "="
    override val arity: Int = 2
    override val action: Action = { pair -> if (pair[0] == pair[1]) True.value else False.value }
}
