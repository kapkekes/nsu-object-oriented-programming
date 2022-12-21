package io.github.kapkekes.grade

/**
 * Base interface for grade types.
 *
 * @property points The point measurement of the grade.
 */
sealed interface Grade {
    val points: Int
}
