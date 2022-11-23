package io.github.kapkekes.grade

/** Provides possible marks for credits. */
enum class Credit(
    /**
     * Return the point measurement of this result.
     *
     * @return the quantity of the points
     */
    val points: Int
) {
    FAIL(0), PASS(5);
}
