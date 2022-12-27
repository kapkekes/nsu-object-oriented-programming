package io.github.kapkekes.operations.logic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

/**
 * Returns the second argument, if the first is [True]
 */
object IfElse : Operation {
    override val token: String = "if"
    override val arity: Int = 3
    override val action: Action = { triple -> if (triple[0] == True.value) triple[1] else triple[2] }
}
