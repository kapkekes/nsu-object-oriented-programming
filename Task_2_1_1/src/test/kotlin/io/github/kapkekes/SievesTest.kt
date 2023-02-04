package io.github.kapkekes

import io.github.kapkekes.sieves.EratosthenesSieve
import io.github.kapkekes.sieves.SimpleSieve
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertFalse

/** Test factory for [Sieve]-implementing classes. */
class SievesTest {
    private fun assertWithSieve(ints: Collection<Int>): (Sieve) -> Unit {
        return { sieve: Sieve -> ints.map { assert(sieve.isPrime(it)) } }
    }

    private fun assertFalseWithSieve(ints: Collection<Int>): (Sieve) -> Unit {
        return { sieve: Sieve -> ints.map { assertFalse(sieve.isPrime(it)) } }
    }

    @TestFactory
    fun sieveTestFactory(): Collection<DynamicTest> {
        val sieves = listOf(
            SimpleSieve(),
            EratosthenesSieve(100),
        ).map { it.initialize() }

        val testCases = listOf(
            Pair("Specials", assertFalseWithSieve(listOf(0, 1))),
            Pair("Primes", assertWithSieve(listOf(2, 3, 5, 97))),
            Pair("Composites", assertFalseWithSieve(listOf(4, 9, 20, 49, 63))),
        )

        return sieves.flatMap { sieve: Sieve ->
            testCases.map { test ->
                dynamicTest("${sieve.javaClass.name} with ${test.first}") {
                    test.second(sieve)
                }
            }
        }
    }
}
