package io.github.kapkekes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeneralTest {
    @Test
    fun simpleScenario() {
        val notebook = Notebook()
        notebook.add("First note!", "Hello, World!")

        val allRecords = notebook.filter()

        assertEquals("First note!", allRecords[0].name)
        assertEquals("Hello, World!", allRecords[0].content)
        assertEquals("Hello, World!", notebook["First note!"]?.content)

        assertEquals(setOf("First note!"), notebook.index)

        val record = notebook.remove("First note!")

        assertTrue { notebook.index.isEmpty() }
    }
}
