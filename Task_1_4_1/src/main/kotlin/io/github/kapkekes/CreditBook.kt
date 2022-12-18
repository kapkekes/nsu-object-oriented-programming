package io.github.kapkekes

import io.github.kapkekes.grade.Grade


class CreditBook(
    val student: Person,
    val id: Int,
    val speciality: String,
    termsQuantity: Int = 8,
) {
    init { // validate primary constructor
        require(termsQuantity > 0) {
            "[termsQuantity] should be positive"
        }
    }

    var diplomaGrade: Grade? = null
    val honorPossibility: Boolean
        get() {
            val supplementReq = subjectEntries.map { (name, entries) ->
                pages[entries.max()][name]!!.grade?.points ?: 5
            } .count { it == 5 } > subjectEntries.count() * 0.75

            val bookReq = pages.all { page ->
                page.values.mapNotNull { subject ->
                    subject.grade?.points
                } .all { it > 5 }
            }

            val diplomaReq = (diplomaGrade?.points ?: 5) == 5

            return supplementReq && bookReq && diplomaReq
        }

    private val pages: List<MutableMap<String, Subject>> = List(termsQuantity) { HashMap() }
    private val subjectEntries: MutableMap<String, MutableSet<Int>> = HashMap()

    fun calculateGPA(): Double {
        return pages.flatMap { page: Map<String, Subject> ->
            page.values.mapNotNull { field: Subject ->
                field.grade?.points
            }
        } .average()
    }

    fun calculateDiplomaAverage(): Double {
        return subjectEntries.mapNotNull { (name, entries) ->
            pages[entries.max()][name]!!.grade?.points
        } .average()
    }

    fun addSubject(semester: Int, name: String, lecturer: Person): CreditBook {
        require(semester in 0..pages.size) {
            "this book consists from ${pages.size + 1} semesters, can't find semester #$semester"
        }

        require(name !in pages[semester].keys) {
            "$name already exists in semester #$semester"
        }

        if (name !in subjectEntries.keys) {
            subjectEntries[name] = HashSet()
        }

        subjectEntries[name]!!.add(semester)
        pages[semester][name] = Subject(name, lecturer)

        return this
    }

    fun getSubject(semester: Int, name: String): Subject? {
        if (semester !in pages.indices) {
            return null
        }

        return pages[semester][name]
    }

    data class Subject(val name: String, val lecturer: Person, var grade: Grade? = null)
}
