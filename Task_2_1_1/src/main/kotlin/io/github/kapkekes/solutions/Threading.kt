package io.github.kapkekes.solutions

import io.github.kapkekes.Sieve
import io.github.kapkekes.Solution
import java.lang.Thread
import kotlin.concurrent.thread

/**
 * Solution, which uses multiple [Thread] instances to check [Int]s parallely.
 *
 * @param sieve The sieve, which will be used.
 * @param threadQuantity The quantity of [Thread] instances, which will be created.
 */
class Threading(
    private val sieve: Sieve,
    private val threadQuantity: Int,
) : Solution {
    override fun containsComposite(ints: Collection<Int>): Boolean {
        val threadWorkloadSize: Int = ints.count() / threadQuantity + (ints.count() % threadQuantity).compareTo(0)
        val workloads: List<List<Int>> = ints.windowed(threadWorkloadSize, threadWorkloadSize, true)
        val results: MutableList<Boolean> = mutableListOf()
        val consecutive: Solution = Consecutive(sieve)

        workloads.map { workload: List<Int> ->
            thread {
                val result = consecutive.containsComposite(workload)
                synchronized(results) {
                    results.add(result)
                }
            }
        }.forEach { thread: Thread ->
            thread.join()
        }

        return results.any { it }
    }
}
