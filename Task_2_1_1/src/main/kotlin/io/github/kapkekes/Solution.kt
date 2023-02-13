package io.github.kapkekes

/** Wrapper, which can determine if the [Collection] of [Int]s contains composite. */
interface Solution {
    /**
     * Checks if [ints] contains at least a single composite number.
     *
     * @param ints The collection, which will be checked.
     * @return ``true`` if at least one member of [ints] is composite, ``false`` otherwise.
     */
    fun containsComposite(ints: Collection<Int>): Boolean
}
