package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.sin

class Sine: Operation {
    override val token: String = "sin"
    override val arity: Int = 1
    override val action: Action = { unit -> sin(unit.component1()) }
}
