package com.artishevskym.codinginterviews.solutions.algoexpert.strings

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Write a function that takes in a string of lowercase English alphabet letters and returns
 * the index of the string's first non-repeating character.
 * @see <a href="https://www.algoexpert.io/questions/First%20Non-Repeating%20Character">First Non-Repeating Character</a>
 */
class FirstNonRepeatingCharacter {

    @Test
    fun testBruteForceSolution() {
        firstNonRepeatingCharacterBruteForce(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n^2) time | O(1) space
    private fun firstNonRepeatingCharacterBruteForce(string: String): Int {
        for (i in string.indices) {
            var duplicateFound = false

            for (j in string.indices) {
                if (i != j && string[i] == string[j]) {
                    duplicateFound = true
                }
            }

            if (!duplicateFound) return i
        }

        return -1
    }

    @Test
    fun testOptimalSolution() {
        firstNonRepeatingCharacterOptimal(INPUT) shouldBeEqualTo OUTPUT
    }

    // O(n) time | O(1) space
    private fun firstNonRepeatingCharacterOptimal(string: String): Int {
        val charCounts = mutableMapOf<Char, Int>()

        for (c in string) {
            charCounts[c] = charCounts.getOrDefault(c, 0) + 1
        }

        for (i in string.indices) {
            val char = string[i]
            if (charCounts[char] == 1) return i
        }

        return -1
    }

    private companion object {
        private const val INPUT = "abcdcaf"
        private const val OUTPUT = 1
    }
}