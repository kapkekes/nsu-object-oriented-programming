package io.github.kapkekes

import io.github.kapkekes.sieves.SimpleSieve
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import java.io.File

/**
 * The base class for benchmarking functions from [Solution].
 *
 * @property sieve The sieve, which will be used for benchmarking; [SimpleSieve] by default.
 * @property sample The [Collection] of [Int], which will be used. Automatically loads by [loadSample].
 * @property samplePath The path to the file, which contains the sample for benchmarking.
 */
@State(Scope.Benchmark)
abstract class BenchmarkStub {
    protected val sieve: Sieve = SimpleSieve().initialize()
    protected lateinit var sample: Collection<Int>

    @Param("example.primes")
    var samplePath: String = "example.primes"

    /** Load sample to the [sample] property. */
    @Setup
    fun loadSample() {
        sample = File(samplePath)
            .readBytes()
            .toList()
            .windowed(4, 4)
            .map bytesToInt@{
                var decodedInt = 0
                it.mapIndexed { index, byte -> decodedInt += byte.toUByte().toInt() shl (index * 8) }
                return@bytesToInt decodedInt
            }
    }

    /** Actual [Benchmark] routine, which should be overriden. */
    abstract fun routine(): Boolean
}
