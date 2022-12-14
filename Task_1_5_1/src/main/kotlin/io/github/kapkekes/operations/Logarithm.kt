package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.log

class Logarithm: Operation {
    override val token: String = "log"
    override val arity: Int = 2
    override val action: Action = { pair -> log(pair.component2(), pair.component1()) }
}
