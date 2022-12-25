package io.github.kapkekes.operations.flow

import io.github.kapkekes.Action
import io.github.kapkekes.Operation

class Exit : Operation() {
    override val token: String = "exit"
    override val arity: Int = 0
    override val action: Action = { _ -> throw RuntimeException("Got exit command; closing...") }
}
