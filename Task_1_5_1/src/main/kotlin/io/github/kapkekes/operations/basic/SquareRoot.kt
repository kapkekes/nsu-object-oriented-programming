package io.github.kapkekes.operations.basic

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.complex.sqrt

class SquareRoot : Operation() {
    override val token: String = "sqrt"
    override val arity: Int = 1
    override val action: Action = { unit -> sqrt(unit[1]) }
}
