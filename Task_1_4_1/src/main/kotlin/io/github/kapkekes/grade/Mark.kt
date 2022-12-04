package io.github.kapkekes.grade

/** Provides possible grades for differential credits and exams. */
sealed class Mark(
    override val points: Int
): Grade {
    object BAD : Mark(1)
    object POOR : Mark(2)
    object SATISFACTORY : Mark(3)
    object GOOD : Mark(4)
    object EXCELLENT : Mark(5)
}
