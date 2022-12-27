package io.github.kapkekes.operations.logic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

/** Returns [True] if the numbers are not equal, [False] otherwise. */
object Inequality : Operation {
    override val token: String = "!="
    override val arity: Int = 2
    override val action: Action = { pair -> if (pair[0] != pair[1]) True.value else False.value }
}
