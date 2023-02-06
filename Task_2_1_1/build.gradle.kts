import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.allopen") version "1.7.20"
    id("jacoco")
    id("org.jetbrains.dokka") version "1.7.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.4"
}

group = "io.github.kapkekes"
version = "1.0"

repositories {
    mavenCentral()
}

val benchmarks = "benchmarks"
sourceSets {
    create(benchmarks)
}
val benchmarksImplementation by configurations

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    benchmarksImplementation(project(mapOf("path" to ":")))
    benchmarksImplementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

configure<AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    targets {
        register(benchmarks) {
            this as JvmBenchmarkTarget
            jmhVersion = "1.21"
        }
    }

    val samplePaths: Array<String> = File("./src/$benchmarks/resources/").walk().filter {
        it.isFile
    }.map {
        it.path
    }.toList().toTypedArray()
    val threadQuantities: Array<Int> = Array(16) { it + 1 }

    configurations {
        named("main") {
            mode = "avgt"
            reportFormat = "csv"

            warmups = 5
            iterations = 10
            iterationTime = 5
            iterationTimeUnit = "sec"
            outputTimeUnit = "MICROSECONDS"

            param("samplePath", *samplePaths)
            param("threadQuantity", *threadQuantities)
        }
        register("smoke") {
            mode = "avgt"
            reportFormat = "csv"

            warmups = 2
            iterations = 2
            iterationTime = 2
            iterationTimeUnit = "sec"
            outputTimeUnit = "MICROSECONDS"

            param("samplePath", *samplePaths)
            param("threadQuantity", *threadQuantities)
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
