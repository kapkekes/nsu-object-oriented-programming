package io.github.kapkekes.grade

/** Provides possible grades for credits. */
enum class Credit(
    /**
     * Return the point measurement of this credit.
     *
     * @return the quantity of the points
     */
    val points: Int
) {
    FAIL(0),
    PASS(5),
}
