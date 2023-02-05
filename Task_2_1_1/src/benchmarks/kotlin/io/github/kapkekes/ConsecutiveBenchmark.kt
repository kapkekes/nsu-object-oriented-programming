package io.github.kapkekes

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/** The [Solution.consecutive] benchmark. */
@State(Scope.Benchmark)
class ConsecutiveBenchmark : BenchmarkStub() {
    @Benchmark
    override fun routine(): Boolean {
        return Solution.consecutive(sieve, sample)
    }
}
