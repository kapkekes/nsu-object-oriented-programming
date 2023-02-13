package io.github.kapkekes

import io.github.kapkekes.solutions.Consecutive
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/** The [Consecutive] solution benchmark. */
@State(Scope.Benchmark)
class ConsecutiveBenchmark : BenchmarkStub() {
    @Benchmark
    override fun routine(): Boolean {
        return Consecutive(sieve).containsComposite(sample)
    }
}
