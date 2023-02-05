package io.github.kapkekes

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/**
 * The [Solution.thread] benchmark.
 *
 * @property threadQuantity The quantity of threads, which will be created by [Solution.thread].
 */
@State(Scope.Benchmark)
class ThreadBenchmark : BenchmarkStub() {
    @Param("1")
    var threadQuantity: Int = 1

    @Benchmark
    override fun routine(): Boolean {
        return Solution.thread(sieve, sample, threadQuantity)
    }
}
