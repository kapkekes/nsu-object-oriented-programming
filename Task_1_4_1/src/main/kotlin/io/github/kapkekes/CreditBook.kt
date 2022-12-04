package io.github.kapkekes

class CreditBook(
    val student: Person,
    val id: Int,
    val speciality: String,
    val terms: List<Map<String, String>>,
) {
    constructor(
        student: Person,
        id: Int,
        speciality: String,
        termsQuantity: Int,
    ) : this(student, id, speciality, List(termsQuantity) { HashMap() })
}
