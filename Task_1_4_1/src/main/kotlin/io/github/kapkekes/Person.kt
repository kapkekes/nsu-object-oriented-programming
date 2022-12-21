package io.github.kapkekes

/**
 * Represents a simple human card.
 *
 * @property name The name fo the human.
 * @property surname The surname or the middle name of the human.
 * @property patronymic The patronymic or the last name of the human.
 */
data class Person(val name: String, val surname: String, val patronymic: String? = null)
