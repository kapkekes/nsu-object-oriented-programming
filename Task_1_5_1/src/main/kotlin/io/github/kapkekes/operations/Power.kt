package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

class Power: Operation {
    override val token: String = "pow"
    override val arity: Int = 2
    override val action: Action = { pair -> pair.component1().pow(pair.component2()) }
}
