package io.github.kapkekes

import io.github.kapkekes.sieves.SimpleSieve
import io.github.kapkekes.solutions.Consecutive
import io.github.kapkekes.solutions.Executor
import io.github.kapkekes.solutions.ParallelStream
import io.github.kapkekes.solutions.Threading
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertFalse

/** Test factory for Solution functions. */
class SolutionsTest {
    @TestFactory
    fun solutionTestFactory(): Collection<DynamicTest> {
        val sieve: Sieve = SimpleSieve().initialize()
        val threads = 4

        val solutions = mapOf(
            Pair("Consecutive", Consecutive(sieve)),
            Pair("Executor", Executor(sieve, threads)),
            Pair("ParallelStream", ParallelStream(sieve)),
            Pair("Thread", Threading(sieve, threads)),
        )

        return solutions.map { (name, solution) ->
            dynamicTest("$name solution") {
                assertFalse(solution.containsComposite(listOf(2, 3, 5, 7, 11)))
                assert(solution.containsComposite(listOf(2, 3, 5, 7, 9, 11)))
            }
        }
    }
}
