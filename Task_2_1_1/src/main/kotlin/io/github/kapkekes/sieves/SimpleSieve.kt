package io.github.kapkekes.sieves

import io.github.kapkekes.Sieve
import kotlin.math.sqrt

/**
 * A really straight-forward implementation of checking if a number is prime.
 *
 * Does not require [initialize] call before usage.
 */
class SimpleSieve : Sieve {
    override fun initialize(): SimpleSieve = this

    override fun isPrime(x: Int): Boolean {
        if (x <= 1) {
            return false
        }

        for (q: Int in 2..sqrt(x.toDouble()).toInt()) {
            if (x % q == 0) {
                return false
            }
        }

        return true
    }
}
