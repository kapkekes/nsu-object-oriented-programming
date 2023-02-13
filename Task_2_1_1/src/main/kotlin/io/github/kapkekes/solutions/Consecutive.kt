package io.github.kapkekes.solutions

import io.github.kapkekes.Solution
import io.github.kapkekes.Sieve

/**
 * Simple consecutive solution.
 *
 * @param sieve A sieve, which will be used by the instance.
 */
class Consecutive(
    private val sieve: Sieve
) : Solution {
    override fun containsComposite(ints: Collection<Int>): Boolean {
        return ints.any { !sieve.isPrime(it) }
    }
}
