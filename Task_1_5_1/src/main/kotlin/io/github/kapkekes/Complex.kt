package io.github.kapkekes

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Represents a complex number, reflected as a pair of doubles.
 *
 * @constructor Construct a complex number.
 * @param real The real part of the constructed number.
 * @param imaginary The imaginary part of the constructed number.
 * @property isReal The boolean value, which depends on if [imaginary] is equal to `0.0`.
 * @property conjugate The conjugate complex number for `this`.
 * @property abs The absolute value of the complex number, also known as a radius.
 */
class Complex(
    val real: Double = 0.0,
    val imaginary: Double = 0.0,
) {
    val isReal: Boolean by lazy { imaginary == 0.0 }
    val conjugate: Complex by lazy { Complex(real, -imaginary) }
    val abs: Double by lazy { sqrt(real * real + imaginary * imaginary) }

    /** Raises this value to the power [x]. */
    fun pow(x: Complex): Complex {
        return if (!x.isReal) {
            throw ArithmeticException("Complex power isn't supported: ($this) ^ ($x)")
        } else if (isReal) {
            Complex(real.pow(x.real))
        } else if (x.real.rem(1.0) != 0.0 || x.real < 0.0) {
            throw ArithmeticException("Real power of complex number isn't supported: ($this) ^ $x")
        } else {
            Complex(1.0).apply { repeat(x.real.toInt()) { times(this) } }
        }
    }

    /** Returns the negative of this value. */
    operator fun unaryMinus(): Complex {
        return Complex(
            -real,
            -imaginary,
        )
    }

    /** Adds the [other] value to this value. */
    operator fun plus(other: Double): Complex {
        return Complex(
            real + other,
            imaginary,
        )
    }

    /** Adds the [other] value to this value. */
    operator fun plus(other: Complex): Complex {
        return Complex(
            real + other.real,
            imaginary + other.imaginary,
        )
    }

    /** Subtracts the [other] value from this value. */
    operator fun minus(other: Double): Complex {
        return Complex(
            real - other,
            imaginary,
        )
    }

    /** Subtracts the [other] value from this value. */
    operator fun minus(other: Complex): Complex {
        return Complex(
            real - other.real,
            imaginary - other.imaginary,
        )
    }

    /** Multiplies this value by the [other] value. */
    operator fun times(other: Double): Complex {
        return Complex(
            real * other,
            imaginary * other,
        )
    }

    /** Multiplies this value by the [other] value. */
    operator fun times(other: Complex): Complex {
        return Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real,
        )
    }

    /** Divides this value by the [other] value. */
    operator fun div(other: Double): Complex {
        if (other == 0.0) {
            throw ArithmeticException("Zero division: $this / $other")
        }

        return Complex(
            real / other,
            imaginary / other,
        )
    }

    /** Divides this value by the [other] value. */
    operator fun div(other: Complex): Complex {
        if (other.isReal) {
            return this / other.real
        }

        if (other.real == 0.0 && other.imaginary == 0.0) {
            throw ArithmeticException("Zero division: $this / $other")
        }

        return this * other.conjugate / (other.real * other.real + other.imaginary * other.imaginary)
    }

    override operator fun equals(other: Any?): Boolean {
        return when (other) {
            null -> false
            is Double -> this.real == other
            is Complex -> this.real == other.real && this.imaginary == other.imaginary
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = imaginary.hashCode()
        result = 31 * result + real.hashCode()
        return result
    }

    override fun toString(): String {
        return if (isReal) {
            real.toString()
        } else if (real == 0.0) {
            "${imaginary}i"
        } else {
            "$real ${ if (imaginary > 0) '+' else '-' } ${imaginary.absoluteValue}i"
        }
    }
}
