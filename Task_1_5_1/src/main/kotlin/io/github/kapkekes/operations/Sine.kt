package io.github.kapkekes.operations

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.sin

object Sine: Operation {
    override val token: String = "sin"
    override val arity: Int = 1
    override val action: Action = { unit -> sin(unit.component1()) }
}
