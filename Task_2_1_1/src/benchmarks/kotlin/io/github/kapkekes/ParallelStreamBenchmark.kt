package io.github.kapkekes

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/** The [Solution.parallelStream] benchmark. */
@State(Scope.Benchmark)
class ParallelStreamBenchmark : BenchmarkStub() {
    @Benchmark
    override fun routine(): Boolean {
        return Solution.parallelStream(sieve, sample)
    }
}
