package io.github.kapkekes

import io.github.kapkekes.grade.Grade


data class Subject(val name: String, val lecturer: Person, var grade: Grade? = null)
