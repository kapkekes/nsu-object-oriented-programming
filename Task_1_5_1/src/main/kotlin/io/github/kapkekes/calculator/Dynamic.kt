package io.github.kapkekes.calculator

import org.reflections.Reflections

const val OPERATIONS_PACKAGE = "io.github.kapkekes.operations"

fun loadDefinedOperations(): List<Operation> {
    return Reflections(OPERATIONS_PACKAGE).getSubTypesOf(Operation::class.java).map {
        it.getDeclaredConstructor().newInstance()
    }
}
