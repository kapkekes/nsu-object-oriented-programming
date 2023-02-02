package io.github.kapkekes

/**
 * A base class for finding prime numbers
 *
 * @property upperBound The largest integer which can be checked by this sieve.
 */
abstract class Sieve(
    private val upperBound: Int,
) {
    /** Should be called before any calls to [isPrime] to initialize a sieve. */
    abstract fun initialize()

    /** Checks if [x] is a prime. */
    abstract fun isPrime(x: Int): Boolean
}
