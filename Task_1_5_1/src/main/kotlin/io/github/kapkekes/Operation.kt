package io.github.kapkekes

import io.github.kapkekes.complex.Complex

typealias Action = (List<Complex>) -> Complex

/**
 * Represents an instruction to [Calculator] on how to execute a some operation.
 *
 * @property token A string, by which operation will be recognized.
 * @property arity A quantity of operands, which is required by this operation.
 * @property action A function, which will be applied to the list of [Complex] of [arity] length.
 */
abstract class Operation {
    abstract val token: String
    abstract val arity: Int
    abstract val action: Action

    val definition get() = Pair(token, Pair(arity, action))
}
