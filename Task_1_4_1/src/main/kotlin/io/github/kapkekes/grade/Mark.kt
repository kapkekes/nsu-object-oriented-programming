package io.github.kapkekes.grade

/** Provides possible grades for differential credits and exams. */
enum class Mark(
    override val points: Int
): Grade {
    BAD(1), POOR(2), SATISFACTORY(3), GOOD(4), EXCELLENT(5)
}
