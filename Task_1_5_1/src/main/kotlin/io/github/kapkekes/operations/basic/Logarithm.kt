package io.github.kapkekes.operations.basic

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.complex.log

class Logarithm : Operation() {
    override val token: String = "log"
    override val arity: Int = 2
    override val action: Action = { pair -> log(pair[1], pair[0]) }
}
