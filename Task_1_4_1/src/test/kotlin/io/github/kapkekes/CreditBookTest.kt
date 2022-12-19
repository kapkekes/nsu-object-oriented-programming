package io.github.kapkekes

import io.github.kapkekes.grade.Credit
import io.github.kapkekes.grade.Grade
import io.github.kapkekes.grade.Mark
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

/** Tests for [CreditBook]. */
class CreditBookTest {
    lateinit var creditBook: CreditBook

    @BeforeTest
    fun initializeEnvironment() {
        val vaskevich = Person("Vladimir", "Vaskevich", "Leontevich")
        val vlasovModern = Person("Dmitriy", "Vlasov", "Yurievich")
        val vlasovPostModern = Person("Vladimir", "Vlasov", "Vladimirovich")
        val gatilov = Person("Stepan", "Gatilov", "Yurievich")
        val khotskina = Person("Olga", "Khotskina", "Valerievna")
        val oplakanskaya = Person("Renata", "Oplakanskaya", "Valerievna")
        val zavorina = Person("Tatyana", "Zavorina", "Ivanovna")
        val oparin = Person("Grigoriy", "Oparin", "Andreevich")
        val irtegov = Person("Dmitriy", "Irtegov", "Valentinovich")
        val belousov = Person("Roman", "Belousov", "Gennadyevich")
        val apanovich = Person("Zinaida", "Apanovich", "Vladimirovna")

        creditBook = CreditBook()

        listOf<Triple<String, Person, Grade>>(
            Triple("Introduction to Calculus",                       vaskevich,        Mark.GOOD        ),
            Triple("Introduction to Discrete Mathematics and Logic", vlasovModern,     Mark.GOOD        ),
            Triple("Imperative Programming",                         gatilov,          Mark.GOOD        ),
            Triple("Basics of Speech Culture",                       zavorina,         Mark.EXCELLENT   ),
            Triple("Declarative Programming",                        vlasovPostModern, Mark.EXCELLENT   ),
            Triple("History",                                        oplakanskaya,     Mark.EXCELLENT   ),
            Triple("Digital Platforms",                              irtegov,          Credit.PASS      ),
            Triple("Foreign Language",                               khotskina,        Credit.PASS      ),
            Triple("Physical Education",                             oparin,           Credit.PASS      ),
        ).forEach {
            creditBook.addSubject(1, it.first, it.second, it.third)
        }

        listOf<Triple<String, Person, Grade>>(
            Triple("Introduction to Calculus",                       vaskevich,        Mark.SATISFACTORY),
            Triple("Digital Platforms",                              irtegov,          Mark.GOOD        ),
            Triple("Foreign Language",                               khotskina,        Mark.GOOD        ),
            Triple("Imperative Programming",                         gatilov,          Mark.GOOD        ),
            Triple("Declarative Programming",                        vlasovPostModern, Mark.EXCELLENT   ),
            Triple("Introduction to Discrete Mathematics and Logic", apanovich,        Mark.EXCELLENT   ),
            Triple("Measuring Practicum",                            belousov,         Credit.PASS      ),
            Triple("Physical Education",                             oparin,           Credit.PASS      ),
        ).forEach {
            creditBook.addSubject(2, it.first, it.second, it.third)
        }
    }

    @Test
    fun simpleTest() {
        assertEquals(4.3, creditBook.getGPA(), 0.1)
        assertEquals(4.4, creditBook.getDiplomaAverage(), 0.1)
        assertEquals(2, creditBook.getCurrentTerm())
        assertEquals(false, creditBook.canGetHonoured)
        assertEquals(null, creditBook.diplomaGrade)
        assertEquals(false, creditBook.isStipendIncreased)

        val getterTest = creditBook.getSubject(1, "Declarative Programming")
        assertEquals(Mark.EXCELLENT, getterTest?.grade)
    }

    @Test
    fun subjectRepeatTest() {
        assertFails {
            creditBook.addSubject(
                1,
                "Introduction to Calculus",
                Person("Vladimir", "Vaskevich", "Leontevich"),
                Mark.GOOD
            )
        }
    }

    @Test
    fun illegalValuesTest() {
        assertFails { creditBook.addSubject(0, "", Person("", ""), Mark.EXCELLENT) }
        assertFails { CreditBook(-1) }
        assertFails { creditBook.diplomaGrade = null }
    }
}
