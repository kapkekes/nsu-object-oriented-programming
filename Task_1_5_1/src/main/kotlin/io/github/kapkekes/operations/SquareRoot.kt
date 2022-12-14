package io.github.kapkekes.operations

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.sqrt

object SquareRoot: Operation {
    override val token: String = "sqrt"
    override val arity: Int = 1
    override val action: Action = { unit -> sqrt(unit.component1()) }
}
