package io.github.kapkekes

import io.github.kapkekes.solutions.Executor
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import java.util.concurrent.ExecutorService

/**
 * The [Executor] solution benchmark.
 *
 * @property threadQuantity The quantity of threads, which [ExecutorService] will use.
 */
@State(Scope.Benchmark)
class ExecutorBenchmark : BenchmarkStub() {
    @Param("1")
    var threadQuantity: Int = 1

    @Benchmark
    override fun routine(): Boolean {
        return Executor(sieve, threadQuantity).containsComposite(sample)
    }
}
