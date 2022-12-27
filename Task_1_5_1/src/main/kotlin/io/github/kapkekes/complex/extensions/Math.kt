package io.github.kapkekes.complex.extensions

import io.github.kapkekes.Complex
import kotlin.math.cos
import kotlin.math.sign
import kotlin.math.sin

/** Computes the square root of the value [x]. */
fun sqrt(x: Complex): Complex {
    return if (!x.isReal) {
        Complex(
            kotlin.math.sqrt((x.abs + x.real) / 2),
            sign(x.imaginary) * kotlin.math.sqrt((x.abs - x.real) / 2),
        )
    } else if (x.real >= 0) {
        Complex(kotlin.math.sqrt(x.real))
    } else {
        throw ArithmeticException("Square root of negative real argument: sqrt($x)")
    }
}

/** Computes the logarithm of the value [x] to the given [base]. */
fun log(x: Complex, base: Complex): Complex {
    return if (!x.isReal || !base.isReal) {
        throw ArithmeticException(
            "Complex logarithm isn't supported: log of $x to base $base",
        )
    } else if (x.real <= 0.0) {
        throw ArithmeticException(
            "Non-positive logarithm argument: log of $x to base $base",
        )
    } else if (base.real == 1.0) {
        throw ArithmeticException(
            "Logarithm base is 1: log of $x to base $base",
        )
    } else {
        Complex(kotlin.math.log(x.real, base.real))
    }
}

/** Computes the sine of the angle [x] given in radians. */
fun sin(x: Complex): Complex {
    return if (x.isReal) {
        Complex(sin(x.real))
    } else {
        throw ArithmeticException(
            "Complex sine isn't supported: sin($x)",
        )
    }
}

/** Computes the cosine of the angle [x] given in radians. */
fun cos(x: Complex): Complex {
    return if (x.isReal) {
        Complex(cos(x.real))
    } else {
        throw ArithmeticException(
            "Complex cosine isn't supported: cos($x)",
        )
    }
}
