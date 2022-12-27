package io.github.kapkekes.cli

import io.github.kapkekes.calculator.Calculator
import io.github.kapkekes.calculator.Operation
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

/** Main routine of the application. */
fun main() {
    val operations: Set<Operation> = setOf(
        Addition, Division, Logarithm, Multiplication, Power, SquareRoot, Subtraction,
        Exit,
        Equality, False, Greater, GreaterOrEqual, IfElse, Inequality, Less, LessOrEqual, True,
        Cosine, Sine
    )

    val calculator = Calculator(operations)

    routine@while (true) {
        try {
            println(calculator.evaluate(readln()))
        } catch (e: ArithmeticException) {
            println(e.message)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        } catch (e: RuntimeException) {
            println(e.message)
            break@routine
        }
    }
}
