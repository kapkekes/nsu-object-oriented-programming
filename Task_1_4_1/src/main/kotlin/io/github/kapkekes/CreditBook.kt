package io.github.kapkekes

import io.github.kapkekes.grade.Credit
import io.github.kapkekes.grade.Grade
import kotlin.jvm.Throws

/**
 * Represents a simple credit book.
 *
 * @property diplomaGrade The diploma grade of this [CreditBook].
 * @property canGetHonoured Reflects possibility of getting a diploma with honour.
 * @property isStipendIncreased Reflects possibility of getting an increased stipend in this term.
 * @param termsQuantity The quantity of terms in the constructed [CreditBook].
 * @throws IllegalArgumentException If quantity of terms is non-positive.
 */
class CreditBook
@Throws(IllegalArgumentException::class)
constructor(termsQuantity: Int = 8) {
    init { // validate primary constructor
        require(termsQuantity > 0) { "[termsQuantity] should be positive" }
    }

    private val pages: List<MutableMap<String, Subject>> = List(termsQuantity) { HashMap() }
    private val subjectEntries: MutableMap<String, MutableSet<Int>> = HashMap()

    var diplomaGrade: Grade? = null
        set(value) {
            field = value ?: throw NullPointerException("Can't set diplomaGrade to null")
        }

    val canGetHonoured: Boolean
        get() {
            val supplementReq = subjectEntries.map { (name, entries) ->
                pages[entries.max()][name]!!.grade.points
            }.count { it == 5 } > (subjectEntries.count() * 0.75)

            val bookReq = pages.all { page ->
                page.values.map { subject ->
                    subject.grade.points
                }.all { it >= 4 }
            }

            val diplomaReq = diplomaGrade?.points == 5

            return supplementReq && bookReq && diplomaReq
        }

    val isStipendIncreased: Boolean
        get() {
            val current = getCurrentTerm()
            if (current == 1) {
                return false
            }

            return pages[current - 1 - 1].values.all { it.grade.points == 5 }
        }

    /**
     * Calculates the grade point average.
     *
     * @return The grade point average.
     */
    fun getGPA(): Double {
        return pages.flatMap { page: Map<String, Subject> ->
            page.values.map { subject: Subject ->
                subject.grade
            }.filter { grade: Grade ->
                grade !is Credit
            }.map { mark ->
                mark.points
            }
        }.average()
    }

    /**
     * Calculates the diploma average grade.
     *
     * @return The diploma average grade.
     */
    fun getDiplomaAverage(): Double {
        return subjectEntries.map { (name, entries) ->
            pages[entries.max()][name]!!.grade
        }.filter { grade: Grade ->
            grade !is Credit
        }.map { mark ->
            mark.points
        }.average()
    }

    /**
     * Calculates the current term number depending on the last term with a mark.
     *
     * @return The current term number.
     */
    fun getCurrentTerm(): Int {
        return subjectEntries.map { (_, entries) -> entries.max() }.max() + 1
    }

    /**
     * Add the [name] subject of [lecturer] with [grade] to the [term].
     *
     * @param term The target term.
     * @param name The name of the subject.
     * @param lecturer The person, who teach the subject.
     * @param grade The grade, which was got by student.
     * @return This [CreditBook].
     * @throws IllegalArgumentException If the [name] subject already exists in this.
     * @throws IndexOutOfBoundsException If the [term] is non-positive or greater than the defined quantity of terms.
     */
    @Throws(IllegalArgumentException::class)
    fun addSubject(term: Int, name: String, lecturer: Person, grade: Grade): CreditBook {
        if (term !in 1..pages.size) {
            throw IndexOutOfBoundsException(
                "this book consists from ${pages.size} semesters, can't find term #$term",
            )
        }

        val realTerm = term - 1

        require(name !in pages[realTerm].keys) {
            "$name already exists in term #$term"
        }

        if (name !in subjectEntries.keys) {
            subjectEntries[name] = HashSet()
        }

        subjectEntries[name]!!.add(realTerm)
        pages[realTerm][name] = Subject(name, lecturer, grade)

        return this
    }

    /**
     * Returns the subject from this [CreditBook] if it exists, null otherwise.
     *
     * @param name The target subject.
     * @param term The target term.
     */
    fun getSubject(term: Int, name: String): Subject? {
        if (term - 1 !in pages.indices) {
            return null
        }

        return pages[term - 1][name]
    }

    /**
     * Represents a [Subject] field in [CreditBook].
     *
     * @property name The name of the subject.
     * @property lecturer The person, who teach the subject.
     * @property grade The grade, which was got by student.
     */
    data class Subject(val name: String, val lecturer: Person, val grade: Grade)
}
