package io.github.kapkekes

import java.util.Stack

/**
 * Evaluates mathematical expressions based on [Operation] implementing classes.
 *
 * @constructor Creates an object.
 * @param operations A set of operations, which will be used by the object to execute expressions.
 * @throws IllegalArgumentException If any two operations share the same [Operation.token] at the same time.
 */
class Calculator(
    operations: Set<Operation>,
) {
    private val definedOperations: Map<String, Pair<Int, Action>> = operations.associate { it.definition }

    init {
        require(operations.size == definedOperations.size) {
            "Some operations share the same token."
        }
    }

    private fun parseComplex(token: String): Complex? {
        val isImaginary: Boolean = token.last() == 'i'
        val parsed: String = if (isImaginary) token.dropLast(1) else token
        val value: Double? = parsed.toDoubleOrNull()

        return if (value?.isFinite() == true) {
            if (isImaginary) Complex(imaginary = value) else Complex(value)
        } else {
            null
        }
    }

    /**
     * Evaluate the given [expression].
     *
     * @param expression The expression to evaluate.
     * @throws IllegalArgumentException If a token from the expression cannot be parsed.
     * @throws IllegalStateException If there are too much or too few arguments.
     */
    fun evaluate(expression: String): Complex {
        val tokenSequence = expression.split(' ').reversed()
        val stack: Stack<Complex> = Stack()

        for (token in tokenSequence) {
            val asComplex: Complex? = parseComplex(token)

            if (token in definedOperations) {
                if (definedOperations[token]!!.first > stack.size) {
                    throw IllegalStateException(
                        "Can't evaluate $token operation: ${definedOperations[token]!!.first} operands required, while ${stack.size} are available",
                    )
                } else {
                    val arguments: MutableList<Complex> = mutableListOf()
                    repeat(definedOperations[token]!!.first) { arguments.add(stack.pop()) }
                    stack.push(definedOperations[token]!!.second(arguments))
                }
            } else if (asComplex != null) {
                stack.push(asComplex)
            } else {
                throw IllegalArgumentException("Can't parse token: $token")
            }
        }

        if (stack.size == 1) {
            return stack.pop()
        } else {
            throw IllegalStateException("Expression can't be fully evaluated: not enough operations")
        }
    }
}
