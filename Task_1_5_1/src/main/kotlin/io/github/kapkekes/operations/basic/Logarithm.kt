package io.github.kapkekes.operations.basic

import io.github.kapkekes.calculator.Action
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.extensions.log

/** Returns the logarithm of the first number by base of the second. */
object Logarithm : Operation {
    override val token: String = "log"
    override val arity: Int = 2
    override val action: Action = { pair -> log(pair[0], pair[1]) }
}
