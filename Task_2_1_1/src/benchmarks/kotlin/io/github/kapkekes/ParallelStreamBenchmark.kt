package io.github.kapkekes

import io.github.kapkekes.solutions.ParallelStream
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State

/** The [ParallelStream] solution benchmark. */
@State(Scope.Benchmark)
class ParallelStreamBenchmark : BenchmarkStub() {
    @Benchmark
    override fun routine(): Boolean {
        return ParallelStream(sieve).containsComposite(sample)
    }
}
