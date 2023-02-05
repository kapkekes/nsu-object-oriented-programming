package io.github.kapkekes

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import java.util.concurrent.ExecutorService

/**
 * The [Solution.executor] benchmark.
 *
 * @property threadQuantity The quantity of threads, which [ExecutorService] will use.
 */
@State(Scope.Benchmark)
class ExecutorBenchmark : BenchmarkStub() {
    @Param("1")
    var threadQuantity: Int = 1

    @Benchmark
    override fun routine(): Boolean {
        return Solution.executor(sieve, sample, threadQuantity)
    }
}
