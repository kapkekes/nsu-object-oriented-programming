package io.github.kapkekes.operations

import io.github.kapkekes.Action
import io.github.kapkekes.Operation

object Addition: Operation {
    override val token: String = "+"
    override val arity: Int = 2
    override val action: Action = { pair -> pair.component1() + pair.component2() }
}
