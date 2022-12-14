package io.github.kapkekes.calculator

import io.github.kapkekes.complex.Complex

typealias Action = (List<Complex>) -> Complex

interface Operation {
    val token: String
    val arity: Int
    val action: Action
}
