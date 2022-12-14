package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.cos

class Cosine: Operation {
    override val token: String = "cos"
    override val arity: Int = 1
    override val action: Action = { unit -> cos(unit.component1()) }
}
