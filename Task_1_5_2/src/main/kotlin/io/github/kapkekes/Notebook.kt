package io.github.kapkekes

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * Collection, which is purposed to store [Record] and maintain convenient access possibility to them.
 *
 * @property index The names of all records in `this` notebook.
 * @constructor Construct a new notebook.
 * @throws IllegalArgumentException If there are any two records with the same name.
 */
@Serializable
class Notebook(
    private val records: MutableMap<String, Record>,
) {
    /**
     * Creates a new notebook.
     *
     * @constructor Construct a new notebook from the given [existingRecords].
     * @param existingRecords Records, which should be added to the notebook at the construction.
     */
    constructor(
        existingRecords: Iterable<Record>? = null,
    ) : this(
        if (existingRecords == null) {
            mutableMapOf<String, Record>()
        } else {
            val temp = mutableMapOf<String, Record>()

            for (record in existingRecords) {
                if (record.name !in temp) {
                    temp[record.name] = record
                } else {
                    throw IllegalArgumentException(
                        "There are at least two records with the same name: ${temp[record.name]} and $record",
                    )
                }
            }

            temp
        },
    )

    val index: Set<String>
        get() = records.keys

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
                "The record with such name already exists.",
            )
        }

        records[name] = Record(name, content, Clock.System.now())

        return this
    }

    /** Removes and returns the record with the [name], or `null` if such a name is not present in the notebook. */
    fun remove(name: String): Record? {
        return records.remove(name)
    }

    /** Takes records in range from [from] to [to] with [keywords] in [Record.name] and returns them as a list. */
    fun filter(
        from: Instant = Instant.DISTANT_PAST,
        to: Instant = Instant.DISTANT_FUTURE,
        keywords: Set<String>? = null,
    ): List<Record> {
        return records.values.filter { record ->
            record.timestamp in from..to
        }.filter { record ->
            keywords?.any { keyword ->
                keyword in record.name
            } ?: true
        }.sortedBy { record ->
            record.timestamp
        }
    }

    /** Same as [Notebook.filter], while converting filtered records to a string. */
    fun show(
        from: Instant = Instant.DISTANT_PAST,
        to: Instant = Instant.DISTANT_FUTURE,
        keywords: Set<String>? = null,
    ): String {
        return filter(from, to, keywords).joinToString(separator = "\n") {
            it.toString()
        }
    }
}
