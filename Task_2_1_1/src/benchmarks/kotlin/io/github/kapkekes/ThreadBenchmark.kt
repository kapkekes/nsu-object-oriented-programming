package io.github.kapkekes

import io.github.kapkekes.solutions.Threading
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/**
 * The [Threading] solution benchmark.
 *
 * @property threadQuantity The quantity of threads, which will be created by [Threading] solution.
 */
@State(Scope.Benchmark)
class ThreadBenchmark : BenchmarkStub() {
    @Param("1")
    var threadQuantity: Int = 1

    @Benchmark
    override fun routine(): Boolean {
        return Threading(sieve, threadQuantity).containsComposite(sample)
    }
}
