package io.github.kapkekes

import io.github.kapkekes.grade.Credit
import io.github.kapkekes.grade.Grade
import kotlin.jvm.Throws


/**
 * Represents a simple credit book.
 *
 * @param termsQuantity the quantity of terms in the constructed [CreditBook].
 * @throws IllegalArgumentException if quantity of terms is non-positive.
 */
class CreditBook @Throws(IllegalArgumentException::class) constructor(termsQuantity: Int = 8) {
    init { // validate primary constructor
        require(termsQuantity > 0) { "[termsQuantity] should be positive" }
    }

    private val pages: List<MutableMap<String, Subject>> = List(termsQuantity) { HashMap() }
    private val subjectEntries: MutableMap<String, MutableSet<Int>> = HashMap()

    /** Current diploma grade, set to null by default. */
    var diplomaGrade: Grade? = null
        set(value) {
            field = value ?: throw NullPointerException("Can't set diplomaGrade to null")
        }

    /** Returns a [Boolean] value depending on if the student can get a diploma with honour. */
    val canGetHonoured: Boolean
        get() {
            val supplementReq = subjectEntries.map { (name, entries) ->
                pages[entries.max()][name]!!.grade.points
            } .count { it == 5 } > (subjectEntries.count() * 0.75)

            val bookReq = pages.all { page ->
                page.values.map { subject ->
                    subject.grade.points
                } .all { it >= 4 }
            }

            val diplomaReq = diplomaGrade?.points == 5

            return supplementReq && bookReq && diplomaReq
        }

    /** Returns a [Boolean] value depending on if the student gets an increased stipend in this semester. */
    val isStipendIncreased: Boolean
        get() {
            val current = getCurrentTerm()
            if (current == 1) {
                return false
            }

            return pages[current - 1 - 1].values.all { it.grade.points == 5 }
        }

    /** Returns the grade point average. */
    fun getGPA(): Double {
        return pages.flatMap { page: Map<String, Subject> ->
            page.values.map {subject: Subject ->
                subject.grade
            } .filter { grade: Grade ->
                grade !is Credit
            } .map { mark ->
                mark.points
            }
        } .average()
    }

    /** Returns the diploma average grade. */
    fun getDiplomaAverage(): Double {
        return subjectEntries.map { (name, entries) ->
            pages[entries.max()][name]!!.grade
        } .filter { grade: Grade ->
            grade !is Credit
        } .map { mark ->
            mark.points
        } .average()
    }

    /** Returns the current term number. */
    fun getCurrentTerm(): Int {
        return subjectEntries.map { (_, entries) -> entries.max() } .max() + 1
    }

    /**
     * Tries to add a [name] subject of [lecturer] with [grade] to the [semester].
     *
     * Throws [IllegalArgumentException] if the [name] subject already exists or the [semester] is out of range.
     */
    @Throws(IllegalArgumentException::class)
    fun addSubject(semester: Int, name: String, lecturer: Person, grade: Grade): CreditBook {
        require(semester in 1..pages.size) {
            "this book consists from ${pages.size} semesters, can't find semester #$semester"
        }

        val real = semester - 1

        require(name !in pages[real].keys) {
            "$name already exists in semester #$semester"
        }

        if (name !in subjectEntries.keys) {
            subjectEntries[name] = HashSet()
        }

        subjectEntries[name]!!.add(real)
        pages[real][name] = Subject(name, lecturer, grade)

        return this
    }

    /** Returns the [name] subject from [semester] if it exists, null otherwise. */
    fun getSubject(semester: Int, name: String): Subject? {
        if (semester - 1 !in pages.indices) {
            return null
        }

        return pages[semester - 1][name]
    }

    /** Represents a [Subject] field in [CreditBook]. */
    data class Subject(val name: String, val lecturer: Person, val grade: Grade)
}
