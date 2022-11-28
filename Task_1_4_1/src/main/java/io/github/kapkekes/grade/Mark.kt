package io.github.kapkekes.grade

/** Provides possible grades for differential credits and exams. */
enum class Mark(
    /**
     * Return the point measurement of this mark.
     *
     * @return the quantity of the points
     */
    val points: Int
) {
    BAD(1),
    POOR(2),
    SATISFACTORY(3),
    GOOD(4),
    EXCELLENT(5),
}
