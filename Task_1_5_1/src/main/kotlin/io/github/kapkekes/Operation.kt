package io.github.kapkekes

typealias Action = (List<Complex>) -> Complex

interface Operation {
    val token: String
    val arity: Int
    val action: Action
}
