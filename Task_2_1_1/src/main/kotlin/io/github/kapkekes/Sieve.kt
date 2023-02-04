package io.github.kapkekes

/** A base interface for sieves, which find prime numbers. */
interface Sieve {
    /** Should be called before any calls to [isPrime] to initialize a sieve. */
    fun initialize(): Sieve

    /** Checks if [x] is a prime. */
    fun isPrime(x: Int): Boolean
}
