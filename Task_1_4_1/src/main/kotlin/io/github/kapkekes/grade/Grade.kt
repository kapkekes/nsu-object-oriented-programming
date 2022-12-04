package io.github.kapkekes.grade

/** Base interface for grade types. */
sealed interface Grade {
    /** The point measurement of the grade. */
    val points: Int
}
