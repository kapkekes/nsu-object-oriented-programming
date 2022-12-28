package io.github.kapkekes

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Collection, which is purposed to store [Record] and maintain convenient access possibility to them.
 *
 * @property index The names of all records in `this` notebook.
 * @constructor Construct a new notebook.
 * @param existingRecords Records, which should be added to the notebook at the construction.
 * @throws IllegalArgumentException If there are any two records with the same name.
 */
class Notebook
@Throws(IllegalArgumentException::class)
constructor(
    existingRecords: Iterable<Record>? = null,
) {
    private val records: MutableMap<String, Record> = mutableMapOf()
    val index: Set<String>
        get() = records.keys

    init {
        if (existingRecords != null) {
            for (record in existingRecords) {
                if (record.name !in records) {
                    records[record.name] = record
                } else {
                    throw IllegalArgumentException(
                        "There are at least two records with the same name: ${records[record.name]} and $record"
                    )
                }
            }
        }
    }

    /**
     * Returns the [Record] corresponding to the given [name], or `null` if such a name is not present in the notebook.
     */
    operator fun get(name: String): Record? {
        return records[name]
    }

    /**
     * Stores the [content] as a [Record] with the [name].
     *
     * @param name The name of a new record.
     * @param content The content of a new record.
     * @throws IllegalArgumentException If the record with such name already exists.
     */
    @Throws(IllegalArgumentException::class)
    fun add(name: String, content: String): Notebook {
        if (name in records) {
            throw IllegalArgumentException(
                "The record with such name already exists."
            )
        }

        records[name] = Record(name, content, Clock.System.now())

        return this
    }

    /** Removes and returns the record with the [name], or `null` if such a name is not present in the notebook. */
    fun remove(name: String): Record? {
        return records.remove(name)
    }

    /** Takes records in range from [from] to [to] and formats them to a string. */
    fun show(from: Instant = Instant.DISTANT_PAST, to: Instant = Instant.DISTANT_FUTURE): String {
        return records.values.filter {
            it.timestamp in from..to
        }.sortedBy { it.timestamp }.joinToString(separator = "\n") { it.toString() }
    }
}
