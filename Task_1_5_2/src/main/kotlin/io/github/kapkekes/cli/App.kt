@file:OptIn(ExperimentalCli::class)

package io.github.kapkekes.cli

import io.github.kapkekes.Notebook
import io.github.kapkekes.Record
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.multiple
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import java.io.File

fun main(args: Array<String>) {
    val dump = File("./notebook.json")
    val notebook: Notebook = if (dump.exists()) {
        Json.decodeFromString(
            Notebook.serializer(),
            dump.readText(),
        )
    } else {
        Notebook()
    }

    val parser = ArgParser("notebook-cli")

    class Add : Subcommand("add", "Add a new record") {
        val recordName by argument(ArgType.String, description = "Name of a new record")
        val recordContent by argument(ArgType.String, description = "Content of a new record")

        override fun execute() {
            try {
                notebook.add(recordName, recordContent)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    class Remove : Subcommand("remove", "Remove the existing record") {
        val recordName by argument(ArgType.String, description = "Name of the target record")

        override fun execute() {
            val record: Record? = notebook.remove(recordName)
            if (record == null) {
                println("There is no record with such name.")
            } else {
                println("Deleted this record:\n$record")
            }
        }
    }

    class Show : Subcommand("show", "Show all records") {
        val from by option(
            ArgType.String,
            shortName = "f",
            fullName = "from",
            description = "Records, which were created after timestamp in YYYY-MM-DDTHH:MM:SS format",
        )
        val to by option(
            ArgType.String,
            shortName = "t",
            fullName = "to",
            description = "Records, which were created until timestamp in YYYY-MM-DDTHH:MM:SS format",
        )
        val keywords by option(
            ArgType.String,
            shortName = "k",
            fullName = "keyword",
            description = "Keyword, which should be in the name of the record",
        ).multiple()

        override fun execute() {
            val fromParsed = from?.toLocalDateTime()?.toInstant(TimeZone.currentSystemDefault()) ?: Instant.DISTANT_PAST
            val toParsed = to?.toLocalDateTime()?.toInstant(TimeZone.currentSystemDefault()) ?: Instant.DISTANT_FUTURE
            val keywordsParsed = keywords.ifEmpty { null }?.toSet()

            val header = StringBuilder("All records")
                .append(if (from != null) " after $from" else "")
                .append(if (to != null) " before $to" else "")
                .append(if (keywords.isNotEmpty()) " with keywords $keywords" else "")
                .append(":")

            println("$header\n${notebook.show(fromParsed, toParsed, keywordsParsed)}")
        }
    }

    parser.subcommands(Add(), Remove(), Show())
    parser.parse(args)

    dump.writeText(Json.encodeToString(Notebook.serializer(), notebook))
}
