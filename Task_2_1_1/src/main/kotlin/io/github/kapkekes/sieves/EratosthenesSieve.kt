package io.github.kapkekes.sieves

import io.github.kapkekes.Sieve
import kotlin.math.sqrt

/**
 * A classical implementation of the sieve of Eratosthenes.
 *
 * @param upperBound The largest integer which can be checked by this sieve.
 * @throws IllegalArgumentException If [upperBound] is less than 2.
 */
class EratosthenesSieve(
    private val upperBound: Int,
) : Sieve {
    init { require(upperBound >= 2) { "upperBound of sieve can't be lower than two" } }
    private val primes: Array<Boolean> = Array(upperBound + 1) { true }

    override fun initialize(): EratosthenesSieve {
        for (prime in 2..sqrt(upperBound.toDouble()).toInt()) {
            if (!primes[prime]) {
                continue
            }

            for (composite in (prime * 2)..upperBound step prime) {
                primes[composite] = false
            }
        }

        return this
    }

    override fun isPrime(x: Int): Boolean {
        return if (x in 2..upperBound) {
            primes[x]
        } else {
            false
        }
    }
}
