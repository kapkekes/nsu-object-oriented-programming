package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

class Division: Operation {
    override val token: String = "/"
    override val arity: Int = 2
    override val action: Action = { pair -> pair.component1() / pair.component2() }
}
