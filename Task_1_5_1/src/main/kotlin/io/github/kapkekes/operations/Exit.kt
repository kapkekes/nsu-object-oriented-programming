package io.github.kapkekes.operations

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation

class Exit: Operation {
    override val token: String = "exit"
    override val arity: Int = 0
    override val action: Action = { _ -> throw RuntimeException("Got exit command; closing...") }
}
