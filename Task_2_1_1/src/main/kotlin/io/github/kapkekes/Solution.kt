package io.github.kapkekes

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.concurrent.thread

/** Collection of different solutions, which search composite number in [Collection] of [Int]. */
object Solution {
    /**
     * Simple consecutive solution.
     *
     * @param sieve The sieve, which will be used.
     * @param ints The collection, which will be checked.
     * @return ``true`` if all [ints] are prime, ``false`` otherwise.
     */
    fun consecutive(sieve: Sieve, ints: Collection<Int>): Boolean {
        return ints.all { sieve.isPrime(it) }
    }

    /**
     * Solution, which uses [ExecutorService] to check [Int]s parallely.
     *
     * @param sieve The sieve, which will be used.
     * @param ints The collection, which will be checked.
     * @param threadQuantity The quantity of threads, which [ExecutorService] will use.
     * @return ``true`` if all [ints] are prime, ``false`` otherwise.
     */
    fun executor(sieve: Sieve, ints: Collection<Int>, threadQuantity: Int): Boolean {
        val threadWorkloadSize: Int = ints.count() / threadQuantity + (ints.count() % threadQuantity).compareTo(0)
        val workloads: List<List<Int>> = ints.windowed(threadWorkloadSize, threadWorkloadSize, true)

        val executor: ExecutorService = Executors.newFixedThreadPool(threadQuantity)
        val tasks: MutableList<Callable<Boolean>> = workloads.map {
            Callable { consecutive(sieve, it) }
        }.toMutableList()
        val futures: List<Future<Boolean>> = executor.invokeAll(tasks)
        executor.shutdown()

        return futures.map { it.get() }.all { it }
    }

    /**
     * Solution, which uses [Collection.parallelStream] to check [Int]s parallely.
     *
     * @param sieve The sieve, which will be used.
     * @param ints The collection, which will be checked.
     * @return ``true`` if all [ints] are prime, ``false`` otherwise.
     */
    fun parallelStream(sieve: Sieve, ints: Collection<Int>): Boolean {
        return ints.parallelStream().allMatch { sieve.isPrime(it) }
    }

    /**
     * Solution, which uses multiple [Thread] instances to check [Int]s parallely.
     *
     * @param sieve The sieve, which will be used.
     * @param ints The collection, which will be checked.
     *@param threadQuantity The quantity of [Thread] instances, which will be created.
     * @return ``true`` if all [ints] are prime, ``false`` otherwise.
     */
    fun thread(sieve: Sieve, ints: Collection<Int>, threadQuantity: Int): Boolean {
        val threadWorkloadSize: Int = ints.count() / threadQuantity + (ints.count() % threadQuantity).compareTo(0)
        val workloads: List<List<Int>> = ints.windowed(threadWorkloadSize, threadWorkloadSize, true)
        val results: MutableList<Boolean> = mutableListOf()

        workloads.map { workload: List<Int> ->
            thread {
                val result = consecutive(sieve, workload)
                synchronized(results) {
                    results.add(result)
                }
            }
        }.forEach { thread: Thread ->
            thread.join()
        }

        return results.all { it }
    }
}
