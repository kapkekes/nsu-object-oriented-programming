package io.github.kapkekes.grade

/** Provides possible grades for credits. */
enum class Credit(
    override val points: Int
): Grade {
    FAIL(0), PASS(5)
}
