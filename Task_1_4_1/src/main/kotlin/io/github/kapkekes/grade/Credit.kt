package io.github.kapkekes.grade

/** Provides possible grades for credits. */
sealed class Credit(
    override val points: Int
): Grade {
    object FAIL : Credit(0)
    object PASS : Credit(5)
}
