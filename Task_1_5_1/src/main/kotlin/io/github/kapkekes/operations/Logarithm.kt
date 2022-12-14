package io.github.kapkekes.operations

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.log

object Logarithm: Operation {
    override val token: String = "log"
    override val arity: Int = 2
    override val action: Action = { pair -> log(pair.component2(), pair.component1()) }
}
