package io.github.kapkekes

import kotlin.math.sign

fun sqrt(x: Complex): Complex {
    return if (!x.isReal) {
        Complex(
            kotlin.math.sqrt((x.abs + x.real) / 2),
            sign(x.imaginary) * kotlin.math.sqrt((x.abs - x.real) / 2)
        )
    } else if (x.real >= 0) {
        Complex(kotlin.math.sqrt(x.real))
    } else {
        throw ArithmeticException("Square root of negative argument: sqrt($x)")
    }
}

fun log(x: Complex, base: Complex): Complex {
    return if (!x.isReal || !base.isReal) {
        throw ArithmeticException(
            "Complex logarithm isn't supported: log of $x to base $base"
        )
    } else if (x.real <= 0.0) {
        throw ArithmeticException(
            "Non-positive logarithm argument: log of $x to base $base"
        )
    } else if (base.real == 1.0) {
        throw ArithmeticException(
            "Logarithm base is 1: log of $x to base $base"
        )
    } else {
        Complex(kotlin.math.log(x.real, base.real))
    }
}
