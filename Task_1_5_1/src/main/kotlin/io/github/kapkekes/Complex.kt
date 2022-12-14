package io.github.kapkekes

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(
    val real: Double = 0.0,
    val imaginary: Double = 0.0,
) {
    val isReal: Boolean by lazy { imaginary == 0.0 }
    val conjugate: Complex by lazy { Complex(real, -imaginary) }
    val abs: Double by lazy { sqrt(real * real + imaginary * imaginary) }

    fun pow(x: Complex): Complex {
        return if (!x.isReal) {
            throw ArithmeticException("Complex power isn't supported: ($this) ^ ($x)")
        } else if (this.isReal) {
            Complex(this.real.pow(x.real))
        } else if (x.real.rem(1.0) != 0.0 || x.real < 0.0) {
            throw ArithmeticException("Real power of complex number isn't supported: ($this) ^ $x")
        } else {
            Complex(1.0).apply { repeat(x.real.toInt()) { times(this) } }
        }
    }

    operator fun unaryMinus(): Complex {
        return Complex(
            -real,
            -imaginary
        )
    }

    operator fun plus(summand: Double): Complex {
        return Complex(
            real + summand,
            imaginary,
        )
    }

    operator fun plus(summand: Complex): Complex {
        return Complex(
            real + summand.real,
            imaginary + summand.imaginary,
        )
    }

    operator fun minus(subtrahend: Double): Complex {
        return Complex(
            real - subtrahend,
            imaginary,
        )
    }

    operator fun minus(subtrahend: Complex): Complex {
        return Complex(
            real - subtrahend.real,
            imaginary - subtrahend.imaginary,
        )
    }

    operator fun times(multiplier: Double): Complex {
        return Complex(
            real * multiplier,
            imaginary * multiplier,
        )
    }

    operator fun times(multiplier: Complex): Complex {
        return Complex(
            real * multiplier.real - imaginary * multiplier.imaginary,
            real * multiplier.imaginary + imaginary * multiplier.real,
        )
    }

    operator fun div(divider: Double): Complex {
        if (divider == 0.0) {
            throw ArithmeticException("Zero division: $this / $divider")
        }

        return Complex(
            real / divider,
            imaginary / divider,
        )
    }

    operator fun div(divider: Complex): Complex {
        if (isReal) {
            return this / divider
        }

        if (divider.real == 0.0 && divider.imaginary == 0.0) {
            throw ArithmeticException("Zero division:  $this / $divider")
        }

        return this * divider.conjugate / (divider.real * divider.real + divider.imaginary * divider.imaginary)
    }

    override fun toString(): String {
        return if (isReal) {
            real.toString()
        } else {
            "$real ${ if (imaginary > 0) '+' else '-' } ${imaginary.absoluteValue}i"
        }
    }
}
