package io.github.kapkekes

class Calculator(
    operations: Iterable<Operation>
) {
    private val instructions: Map<String, Pair<Int, Action>> =
        operations.associate {
            Pair(it.token, Pair(it.arity, it.action))
        }

    private fun readToken(rawToken: String): Token? {
        when (rawToken) {
            in instructions.keys ->
        }
    }
}
