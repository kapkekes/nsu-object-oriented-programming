package io.github.kapkekes.solutions

import io.github.kapkekes.Sieve
import io.github.kapkekes.Solution
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Solution, which uses [ExecutorService] to check [Int]s parallely.
 *
 * @param sieve The sieve, which will be used.
 * @param threadQuantity The quantity of threads, which [ExecutorService] will use.
 */
class Executor(
    private val sieve: Sieve,
    private val threadQuantity: Int,
) : Solution {
    override fun containsComposite(ints: Collection<Int>): Boolean {
        val threadWorkloadSize: Int = ints.count() / threadQuantity + (ints.count() % threadQuantity).compareTo(0)
        val workloads: List<List<Int>> = ints.windowed(threadWorkloadSize, threadWorkloadSize, true)

        val consecutive: Solution = Consecutive(sieve)
        val executor: ExecutorService = Executors.newFixedThreadPool(threadQuantity)
        val tasks: MutableList<Callable<Boolean>> = workloads.map {
            Callable { consecutive.containsComposite(it) }
        }.toMutableList()
        val futures: List<Future<Boolean>> = executor.invokeAll(tasks)
        executor.shutdown()

        return futures.map { it.get() }.any { it }
    }
}
