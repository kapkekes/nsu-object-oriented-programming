package io.github.kapkekes

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

/**
 * Simple record data class for [Notebook].
 *
 * @param name The name of the record.
 * @param content The content of the record.
 * @param timestamp The time, when the record was created.
 */
@Serializable
data class Record(
    val name: String,
    val content: String,
    val timestamp: Instant,
) {
    override fun toString(): String {
        return "$name, ${timestamp.toLocalDateTime(TimeZone.currentSystemDefault())}: $content"
    }
}
