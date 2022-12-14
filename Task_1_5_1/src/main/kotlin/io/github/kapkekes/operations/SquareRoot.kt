package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.sqrt

class SquareRoot: Operation {
    override val token: String = "sqrt"
    override val arity: Int = 1
    override val action: Action = { unit -> sqrt(unit.component1()) }
}
