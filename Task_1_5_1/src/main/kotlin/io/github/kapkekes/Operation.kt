package io.github.kapkekes

typealias Action = (List<Number>) -> Number

interface Operation: Token {
    val arity: Int
    val token: String
    val action: Action
}
