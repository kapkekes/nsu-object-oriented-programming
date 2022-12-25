package io.github.kapkekes.operations.trigonometry

import io.github.kapkekes.Action
import io.github.kapkekes.Operation
import io.github.kapkekes.complex.cos

object Cosine : Operation {
    override val token: String = "cos"
    override val arity: Int = 1
    override val action: Action = { unit -> cos(unit[0]) }
}
