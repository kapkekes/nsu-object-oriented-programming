package io.github.kapkekes

import io.github.kapkekes.calculator.Calculator
import io.github.kapkekes.calculator.Operation
import io.github.kapkekes.complex.Complex
import io.github.kapkekes.operations.basic.Addition
import io.github.kapkekes.operations.basic.Division
import io.github.kapkekes.operations.basic.Logarithm
import io.github.kapkekes.operations.basic.Multiplication
import io.github.kapkekes.operations.basic.Power
import io.github.kapkekes.operations.basic.SquareRoot
import io.github.kapkekes.operations.basic.Subtraction
import io.github.kapkekes.operations.flow.Exit
import io.github.kapkekes.operations.logic.Equality
import io.github.kapkekes.operations.logic.False
import io.github.kapkekes.operations.logic.Greater
import io.github.kapkekes.operations.logic.GreaterOrEqual
import io.github.kapkekes.operations.logic.IfElse
import io.github.kapkekes.operations.logic.Inequality
import io.github.kapkekes.operations.logic.Less
import io.github.kapkekes.operations.logic.LessOrEqual
import io.github.kapkekes.operations.logic.True
import io.github.kapkekes.operations.trigonometry.Cosine
import io.github.kapkekes.operations.trigonometry.Sine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

/** Tests for [Calculator] and [Operation] implementing classes. */
class GeneralTest {
    private val calculator = Calculator(
        setOf(
            Addition, Division, Logarithm, Multiplication, Power, SquareRoot, Subtraction,
            Exit,
            Equality, False, Greater, GreaterOrEqual, IfElse, Inequality, Less, LessOrEqual, True,
            Cosine, Sine,
        ),
    )

    private fun assertEquals(expected: Complex, actual: Complex, absoluteTolerance: Double, message: String? = null) {
        assertEquals(expected.real, actual.real, absoluteTolerance, message)
        assertEquals(expected.imaginary, actual.imaginary, absoluteTolerance, message)
    }

    @Test
    fun simpleExpression() {
        assertEquals(
            Complex(2.0, 2.0),
            calculator.evaluate("+ 2 2i"),
            0.001,
        )
    }

    @Test
    fun flowControl() {
        assertFails { calculator.evaluate("exit") }
    }

    @Test
    fun incorrectExpression() {
        assertFails { calculator.evaluate("unparseable thing!") }
    }

    @Test
    fun almostBrainfuckCalculator() {
        assertEquals(
            True.value,
            calculator.evaluate("if true if = + 2 2i - 2 2i false true 2"),
            0.0,
        )
    }
}
