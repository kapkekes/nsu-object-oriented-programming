package io.github.kapkekes.solutions

import io.github.kapkekes.Sieve
import io.github.kapkekes.Solution

/**
 * Solution, which uses [Collection.parallelStream] to check [Int]s parallely.
 *
 * @param sieve The sieve, which will be used.
 */
class ParallelStream(
    private val sieve: Sieve,
) : Solution {
    override fun containsComposite(ints: Collection<Int>): Boolean {
        return ints.parallelStream().anyMatch { !sieve.isPrime(it) }
    }
}
